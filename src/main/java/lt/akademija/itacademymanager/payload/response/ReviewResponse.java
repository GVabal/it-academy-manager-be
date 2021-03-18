package lt.akademija.itacademymanager.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lt.akademija.itacademymanager.model.Review;

@Data
@AllArgsConstructor
public class ReviewResponse {

    private final int id;
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

    public static ReviewResponse toReviewResponse(Review review) {
        return new ReviewResponse(review.getId(),
                review.getOverallGrade(), review.getOverallComment(),
                review.getAbilityToLearnGrade(), review.getAbilityToLearnComment(),
                review.getMotivationGrade(), review.getMotivationComment(),
                review.getExtraMileGrade(), review.getExtraMileComment(),
                review.getCommunicationGrade(), review.getCommunicationComment()
        );
    }
}
