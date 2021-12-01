package test.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.guestbook.dto.ReplyDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceImplTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList() {
        Long bno = 2L;
        List<ReplyDTO> replyDTOList = replyService.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println("replyDTO = " + replyDTO));
    }
}