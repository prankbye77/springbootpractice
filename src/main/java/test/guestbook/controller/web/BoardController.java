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
import test.guestbook.dto.BoardDTO;
import test.guestbook.dto.PageRequestDTO;
import test.guestbook.dto.PageResultDTO;
import test.guestbook.service.BoardService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        log.info("result : {}", result);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("result", result);

        return "/board/list";
    }

    @GetMapping("/register")
    public String register() {
        return "/board/register";
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        Long bno = boardService.register(dto);
        log.info("bno : {}", bno);

        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Long bno, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("bno : {}", bno);
        BoardDTO boardDTO = boardService.get(bno);
        model.addAttribute("dto", boardDTO);
        return "/board/read";
    }

    @GetMapping("/modify")
    public String modify(Long bno, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("bno : {}", bno);
        BoardDTO boardDTO = boardService.get(bno);
        model.addAttribute("dto", boardDTO);
        return "/board/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("dto : {}", dto);
        boardService.modify(dto);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("bno : {}", bno);
        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }
}
