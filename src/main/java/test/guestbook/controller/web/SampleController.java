package test.guestbook.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.guestbook.security.dto.ClubAuthMemberDTO;

@Controller
@Slf4j
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/all")
    public String exAll() {
        log.info("exAll");
        return "/sample/all";
    }

    @GetMapping("/member")
    public String exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {
        log.info("exMember");
        log.info("clubAuthMemberDTO: {}", clubAuthMemberDTO);
        return "/sample/member";
    }

    @GetMapping("/admin")
    public String exAdmin() {
        log.info("exAdmin");
        return "/sample/admin";
    }
}
