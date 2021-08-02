package com.example.football.models.dto;

import com.example.football.models.enums.Position;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerInputDto {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 3)
    private String lastName;

    @XmlElement(name = "email")
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    @NotNull
    private String birthDate;

    @XmlElement(name = "position")
    @NotNull
    private Position position;

    @XmlElement(name = "town")
    @NotNull
    private TownNameDto townName;

    @XmlElement(name = "team")
    @NotNull
    private TeamNameDto teamName;

    @XmlElement(name = "stat")
    @NotNull
    private StatIdDto statId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TownNameDto getTownName() {
        return townName;
    }

    public void setTownName(TownNameDto townName) {
        this.townName = townName;
    }

    public TeamNameDto getTeamName() {
        return teamName;
    }

    public void setTeamName(TeamNameDto teamName) {
        this.teamName = teamName;
    }

    public StatIdDto getStatId() {
        return statId;
    }

    public void setStatId(StatIdDto statId) {
        this.statId = statId;
    }
}
