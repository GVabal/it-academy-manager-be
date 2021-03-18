package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Review;
import lt.akademija.itacademymanager.model.Stream;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.request.ReviewNewRequest;
import lt.akademija.itacademymanager.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

    private final StudentService studentService;
    private final StreamService streamService;
    private final ReviewRepository reviewRepository;

    public Review addReview(ReviewNewRequest request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Stream stream = streamService.getStreamById(request.getStreamId());
        Review review = new Review.Builder()
                .withStudent(student)
                .withStream(stream)
                .withAbilityToLearnComment(request.getAbilityToLearnComment())
                .withAbilityToLearnGrade(request.getAbilityToLearnGrade())
                .withOverallComment(request.getOverallComment())
                .withOverallGrade(request.getOverallGrade())
                .withMotivationComment(request.getMotivationComment())
                .withMotivationGrade(request.getMotivationGrade())
                .withExtraMileComment(request.getExtraMileComment())
                .withExtraMileGrade(request.getExtraMileGrade())
                .withCommunicationGrade(request.getCommunicationGrade())
                .withCommunicationComment(request.getCommunicationComment())
                .build();

        return reviewRepository.save(review);
    }
}
