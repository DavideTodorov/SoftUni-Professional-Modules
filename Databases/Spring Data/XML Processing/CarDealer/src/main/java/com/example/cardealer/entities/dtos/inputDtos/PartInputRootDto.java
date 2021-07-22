package com.example.cardealer.entities.dtos.inputDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartInputRootDto {

    @XmlElement(name = "part")
    private List<PartInputDto> parts;

    public List<PartInputDto> getParts() {
        return parts;
    }

    public void setParts(List<PartInputDto> parts) {
        this.parts = parts;
    }
}
