package test.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.guestbook.domain.Movie;
import test.guestbook.domain.MovieMember;
import test.guestbook.domain.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long) (Math.random() * 100) + 1;
            Long mid = (long) (Math.random() * 100) + 1;
            MovieMember movieMember = MovieMember.builder().mid(mid).build();

            Review review = Review.builder()
                    .movieMember(movieMember)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int) (Math.random() * 5) + 1)
                    .text("context" + i)
                    .build();
            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetMovieReviews() {
        Movie movie = Movie.builder().mno(94L).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        result.forEach(movieReview -> {
            System.out.println("getReviewnum = " + movieReview.getReviewnum());
            System.out.println("getGrade = " + movieReview.getGrade());
            System.out.println("getText = " + movieReview.getText());
            System.out.println("getMovieMember getEmail = " + movieReview.getMovieMember().getEmail());
            System.out.println("------------------------");
        });
    }
}