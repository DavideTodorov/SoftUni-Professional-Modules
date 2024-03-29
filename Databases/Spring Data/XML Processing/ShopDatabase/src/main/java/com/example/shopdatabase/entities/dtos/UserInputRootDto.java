package com.example.shopdatabase.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserInputRootDto {

    @XmlElement(name = "user")
    List<UserInputDto> users;

    public List<UserInputDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserInputDto> users) {
        this.users = users;
    }
}
