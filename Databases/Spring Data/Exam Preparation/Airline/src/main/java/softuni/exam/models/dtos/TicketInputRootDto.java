package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketInputRootDto {

    @XmlElement(name = "ticket")
    private List<TicketInputDto> tickets;

    public List<TicketInputDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketInputDto> tickets) {
        this.tickets = tickets;
    }
}
