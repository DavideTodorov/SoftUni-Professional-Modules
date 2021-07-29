package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersInputRootDto {

    @XmlElement(name = "offer")
    private List<OfferInputDto> offers;

    public List<OfferInputDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferInputDto> offers) {
        this.offers = offers;
    }
}
