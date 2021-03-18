package lt.akademija.itacademymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    public Student(String firstName, String lastName, String pictureUrl, String occupation, String direction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.occupation = occupation;
        this.direction = direction;
    }

    public int extractPictureId() {
        return pictureUrl != null ? Integer.parseInt(pictureUrl.replaceFirst(".*/", "")) : 0;
    }
}
