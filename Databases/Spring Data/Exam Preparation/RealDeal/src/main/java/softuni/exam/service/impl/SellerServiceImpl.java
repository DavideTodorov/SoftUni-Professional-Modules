package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Seller;
import softuni.exam.models.dtos.SellerInputDto;
import softuni.exam.models.dtos.SellerInputRootDto;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";
    private final XmlParser xmlParser;
    private final SellerRepository sellerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public SellerServiceImpl(XmlParser xmlParser, SellerRepository sellerRepository,
                             ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.xmlParser = xmlParser;
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files
                .readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        SellerInputRootDto sellerInputRootDto =
                xmlParser.fromFile(SELLERS_FILE_PATH, SellerInputRootDto.class);

        List<String> emails = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        List<Seller> sellers = sellerInputRootDto
                .getSellers()
                .stream()
                .filter(sellerInputDto -> {
                    boolean valid = validationUtil.isValid(sellerInputDto);

                    if (emails.contains(sellerInputDto.getEmail())) {
                        valid = false;
                    } else {
                        emails.add(sellerInputDto.getEmail());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully import seller %s - %s",
                                sellerInputDto.getLastName(), sellerInputDto.getEmail()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid seller")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(sellerInputDto -> modelMapper.map(sellerInputDto, Seller.class))
                .collect(Collectors.toList());

        sellerRepository.saveAll(sellers);

        return sb.toString().trim();
    }

    @Override
    public Seller findById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }
}
