package lt.akademija.itacademymanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer studentId;
    private Integer lecturerId;
    private int overallGrade;
    private String overallComment;
    private int abilityToLearnGrade;
    private String abilityToLearnComment;
    private int motivationGrade;
    private String motivationComment;
    private int extraMileGrade;
    private String extraMileComment;
    private int communicationGrade;
    private String communicationComment;

    public Review(int overallGrade, String overallComment,
                  int abilityToLearnGrade, String abilityToLearnComment,
                  int motivationGrade, String motivationComment,
                  int extraMileGrade, String extraMileComment,
                  int communicationGrade, String communicationComment) {
        this.overallComment = overallComment;
        this.overallGrade = overallGrade;
        this.abilityToLearnComment = abilityToLearnComment;
        this.abilityToLearnGrade = abilityToLearnGrade;
        this.motivationComment = motivationComment;
        this.motivationGrade = motivationGrade;
        this.extraMileComment = extraMileComment;
        this.extraMileGrade = extraMileGrade;
        this.communicationComment = communicationComment;
        this.communicationGrade = communicationGrade;
    }
}
