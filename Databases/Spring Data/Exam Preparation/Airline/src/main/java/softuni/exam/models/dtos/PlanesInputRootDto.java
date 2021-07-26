package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanesInputRootDto {

    @XmlElement(name = "plane")
    private List<PlaneInputDto> planes;

    public List<PlaneInputDto> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlaneInputDto> planes) {
        this.planes = planes;
    }
}
