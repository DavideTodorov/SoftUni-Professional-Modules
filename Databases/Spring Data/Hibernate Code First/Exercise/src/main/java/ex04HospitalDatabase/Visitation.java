package ex04HospitalDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "visitation_date")
    private LocalDateTime visitationDate;

    @Column
    private String comments;

    @ManyToOne()
    private Patient patient;
}
