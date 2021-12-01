package test.guestbook.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.guestbook.dto.MovieDTO;
import test.guestbook.dto.PageRequestDTO;
import test.guestbook.service.MovieService;

@Controller
@RequestMapping("/movie")
@Slf4j
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public String register() {
        return "/movie/register";
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO: {}", movieDTO);
        Long mno = movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("pageRequestDTO: {}", pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));
        return "/movie/list";
    }

    @GetMapping("/read")
    public String read(Long mno,
                       @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                       Model model) {
        log.info("mno: {}", mno);

        MovieDTO movieDTO = movieService.getMovie(mno);
        model.addAttribute("dto", movieDTO);

        return "/movie/read";
    }
}
