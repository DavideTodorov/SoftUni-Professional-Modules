package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.Offer;
import softuni.exam.models.dtos.OffersInputRootDto;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final CarService carService;
    private final SellerService sellerService;

    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser,
                            ValidationUtil validationUtil, ModelMapper modelMapper,
                            CarService carService, SellerService sellerService) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files
                .readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OffersInputRootDto offersInputRootDto =
                xmlParser.fromFile(OFFERS_FILE_PATH, OffersInputRootDto.class);

        StringBuilder sb = new StringBuilder();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        List<Offer> offers = offersInputRootDto
                .getOffers()
                .stream()
                .filter(offerInputDto -> {
                    boolean valid = validationUtil.isValid(offerInputDto);

                    if (valid) {
                        sb.append(String.format("Successfully import offer %s - %s",
                                offerInputDto.getAddedOn(), offerInputDto.getHasGoldStatus()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid offer")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(offerInputDto -> {
                    Offer offer = modelMapper.map(offerInputDto, Offer.class);

                    Car car = carService.findCarById(offerInputDto.getCarId().getId());

                    offer.setCar(car);
                    offer.setSeller(sellerService.findById(offerInputDto.getSellerId().getId()));
                    offer.setPictures(car.getPictures());

                    return offer;
                })
                .collect(Collectors.toList());

        offerRepository.saveAll(offers);

        return sb.toString().trim();
    }
}
