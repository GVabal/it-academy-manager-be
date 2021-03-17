package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Review;
import lt.akademija.itacademymanager.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review toReview) {
        return reviewRepository.save(toReview);
    }
}
