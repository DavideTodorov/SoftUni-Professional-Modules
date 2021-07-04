package ex04HospitalDatabase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "diagnosis")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;
}
