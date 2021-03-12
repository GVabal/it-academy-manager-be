package lt.akademija.itacademymanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String pictureUrl;
    private String occupation;
    private String direction;

    public Student(String firstName, String lastName, String pictureUrl, String occupation, String direction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.occupation = occupation;
        this.direction = direction;
    }
}
