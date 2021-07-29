package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerInputRootDto {

    @XmlElement(name = "seller")
    List<SellerInputDto> sellers;

    public List<SellerInputDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerInputDto> sellers) {
        this.sellers = sellers;
    }
}
