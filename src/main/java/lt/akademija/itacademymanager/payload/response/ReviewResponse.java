package lt.akademija.itacademymanager.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponse {

    private final int id;
    private final int studentId;
    private final int overallGrade;
    private final String overallComment;
    private final int abilityToLearnGrade;
    private final String abilityToLearnComment;
    private final int motivationGrade;
    private final String motivationComment;
    private final int extraMileGrade;
    private final String extraMileComment;
    private final int communicationGrade;
    private final String communicationComment;
}
