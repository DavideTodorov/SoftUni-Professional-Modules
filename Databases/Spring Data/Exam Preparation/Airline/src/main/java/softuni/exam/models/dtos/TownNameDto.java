package softuni.exam.models.dtos;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fromTown")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameDto {

    @XmlElement(name = "name")
    @Size(min = 2)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
