package softuni.exam.models.dtos;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferInputDto {

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;

    @XmlElement(name = "added-on")
    private String addedOn;

    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;

    @XmlElement(name = "car")
    private CarIdDto carId;

    @XmlElement(name = "seller")
    private SellerIdDto sellerId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarIdDto getCarId() {
        return carId;
    }

    public void setCarId(CarIdDto carId) {
        this.carId = carId;
    }

    public SellerIdDto getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerIdDto sellerId) {
        this.sellerId = sellerId;
    }
}
