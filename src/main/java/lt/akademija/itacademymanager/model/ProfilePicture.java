package lt.akademija.itacademymanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile_picture")
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private byte[] bytes;

    public ProfilePicture(byte[] bytes) {
        this.bytes = bytes;
    }
}
