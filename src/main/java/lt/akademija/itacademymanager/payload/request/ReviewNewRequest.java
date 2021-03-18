package lt.akademija.itacademymanager.payload.request;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ReviewNewRequest {

    @NotNull(message = "Grades are mandatory.")
    @Min(value = 1, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    private int overallGrade;

    @NotBlank(message = "Overall comment is mandatory.")
    @Size(max = 255, message = "Comment is too long")
    private String overallComment;

    @NotNull(message = "Grades are mandatory.")
    @Min(value = 1, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    private int abilityToLearnGrade;

    @Size(max = 255, message = "Comment is too long")
    private String abilityToLearnComment;

    @NotNull(message = "Grades are mandatory.")
    @Min(value = 1, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    private int motivationGrade;

    @Size(max = 255, message = "Comment is too long")
    private String motivationComment;

    @NotNull(message = "Grades are mandatory.")
    @Min(value = 1, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    private int extraMileGrade;

    @Size(max = 255, message = "Comment is too long")
    private String extraMileComment;

    @NotNull(message = "Grades are mandatory.")
    @Min(value = 1, message = "grade must be between 1 and 10")
    @Max(value = 10, message = "grade must be between 1 and 10")
    private int communicationGrade;

    @Size(max = 255, message = "Comment is too long")
    private String communicationComment;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer streamId;


}
