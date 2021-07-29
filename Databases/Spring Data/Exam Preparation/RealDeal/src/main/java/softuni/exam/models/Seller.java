package softuni.exam.models;

import softuni.enums.Rating;

import javax.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "VARCHAR(20)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(20)")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "rating", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Column(name = "town", nullable = false)
    private String town;

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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
