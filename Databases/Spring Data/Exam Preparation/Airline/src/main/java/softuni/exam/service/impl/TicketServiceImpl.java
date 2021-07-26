package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.TicketInputRootDto;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final TownService townService;
    private final PassengerService passengerService;
    private final PlaneService planeService;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper,
                             ValidationUtil validationUtil, XmlParser xmlParser,
                             TownService townService, PassengerService passengerService,
                             PlaneService planeService) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.passengerService = passengerService;
        this.planeService = planeService;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files
                .readString(Path.of(TICKETS_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        TicketInputRootDto ticketInputRootDto = xmlParser.fromFile(TICKETS_FILE_PATH, TicketInputRootDto.class);
        StringBuilder sb = new StringBuilder();

        List<String> serialNumbers = new ArrayList<>();

        List<Ticket> tickets = ticketInputRootDto
                .getTickets()
                .stream()
                .filter(ticketInputDto -> {
                    boolean valid = validationUtil.isValid(ticketInputDto);

                    if (serialNumbers.contains(ticketInputDto.getSerialNumber())) {
                        valid = false;
                    } else {
                        serialNumbers.add(ticketInputDto.getSerialNumber());
                    }

                    if (valid) {
                        sb.append(String.format("Successfully imported Ticket %s - %s",
                                ticketInputDto.getFromTown().getName(), ticketInputDto.getToTown().getName()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append("Invalid Ticket")
                                .append(System.lineSeparator());
                    }

                    return valid;
                })
                .map(ticketInputDto -> {
                    Ticket ticket = modelMapper.map(ticketInputDto, Ticket.class);

                    ticket.setFromTown(townService.findTownByName(ticketInputDto.getFromTown().getName()));
                    ticket.setToTown(townService.findTownByName(ticketInputDto.getToTown().getName()));
                    ticket.setPassenger(passengerService.findByEmail(ticketInputDto.getPassenger().getEmail()));
                    ticket.setPlane(planeService.findByRegisterNumber(ticketInputDto.getPlane().getRegisterNumber()));

                    return ticket;
                })
                .collect(Collectors.toList());

        ticketRepository.saveAll(tickets);

        return sb.toString().trim();
    }
}
