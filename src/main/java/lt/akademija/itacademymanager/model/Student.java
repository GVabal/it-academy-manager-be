package lt.akademija.itacademymanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    private Integer id;

    private String firstName;
    private String lastName;
}
