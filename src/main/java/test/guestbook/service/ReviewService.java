package test.guestbook.service;

import test.guestbook.domain.Movie;
import test.guestbook.domain.MovieMember;
import test.guestbook.domain.Review;
import test.guestbook.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno);

    Long register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewnum())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .movieMember(MovieMember.builder().mid(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();
        return review;
    }

    default ReviewDTO entityToDTO(Review review) {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewnum(review.getReviewnum())
                .mno(review.getMovie().getMno())
                .mid(review.getMovieMember().getMid())
                .nickname(review.getMovieMember().getNickname())
                .email(review.getMovieMember().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();
        return reviewDTO;
    }
}
