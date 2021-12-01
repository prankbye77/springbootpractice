package test.guestbook.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.guestbook.dto.ReviewDTO;
import test.guestbook.service.ReviewService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("List mno: {}", mno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("Add reviewDTO: {}", reviewDTO);
        Long reviewNum = reviewService.register(reviewDTO);
        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable("reviewnum") Long reviewNum,
                                             @RequestBody ReviewDTO reviewDTO) {
        log.info("Modify reviewDTO: {}", reviewDTO);
        reviewService.modify(reviewDTO);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable("reviewnum") Long reviewNum) {
        log.info("Delete reviewNum: {}", reviewNum);
        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}
