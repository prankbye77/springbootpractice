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
import test.guestbook.dto.GuestbookDTO;
import test.guestbook.dto.PageRequestDTO;
import test.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Slf4j
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list");
        model.addAttribute("result", guestbookService.getList(pageRequestDTO));
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        return "/guestbook/list";
    }

    @GetMapping("/register")
    public String registerForm() {
        log.info("registerForm");
        return "/guestbook/register";
    }

    @PostMapping("/register")
    public String register(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto : {}", dto);
        Long gno = guestbookService.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public String read(long gno, @ModelAttribute("requestDto") PageRequestDTO requestDTO, Model model) {
        GuestbookDTO dto = guestbookService.read(gno);
        model.addAttribute("dto", dto);
        model.addAttribute("requestDTO", requestDTO);

        return "/guestbook/read";
    }

    @GetMapping("/modify")
    public String modifyList(long gno, @ModelAttribute("requestDto") PageRequestDTO requestDTO, Model model) {
        GuestbookDTO dto = guestbookService.read(gno);
        model.addAttribute("dto", dto);
        model.addAttribute("requestDTO", requestDTO);

        return "/guestbook/modify";
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        guestbookService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes) {
        guestbookService.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }
}
