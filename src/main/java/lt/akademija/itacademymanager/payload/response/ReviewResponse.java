package lt.akademija.itacademymanager.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.akademija.itacademymanager.model.Review;

@Getter
@AllArgsConstructor
public class ReviewResponse {

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
    private Integer studentId;
    private Integer streamId;

    public static ReviewResponse toReviewResponse(Review review) {
        return new ReviewResponse(review.getOverallGrade(), review.getOverallComment(),
                review.getAbilityToLearnGrade(), review.getAbilityToLearnComment(),
                review.getMotivationGrade(), review.getMotivationComment(),
                review.getExtraMileGrade(), review.getExtraMileComment(),
                review.getCommunicationGrade(), review.getCommunicationComment(),
                review.getStudent().getId(), review.getStream().getId());
    }
}
