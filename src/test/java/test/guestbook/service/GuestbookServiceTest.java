package test.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.guestbook.domain.Guestbook;
import test.guestbook.dto.GuestbookDTO;
import test.guestbook.dto.PageRequestDTO;
import test.guestbook.dto.PageResultDTO;
import test.guestbook.repository.GuestbookRepository;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService guestbookService;
    @Autowired
    private GuestbookRepository guestbookRepository;

    public void setup() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer("user" + (i % 10))
                    .build();
            guestbookRepository.save(guestbook);
        });
    }

    @Test
    public void testRegister() {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title")
                .content("sample content")
                .writer("sample writer")
                .build();
        System.out.println(guestbookService.register(guestbookDTO));
    }

    @Test
    public void testList() {
        setup();
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDto = guestbookService.getList(pageRequestDTO);

        System.out.println("resultDto.isPrev() = " + resultDto.isPrev());
        System.out.println("resultDto.isNext() = " + resultDto.isNext());
        System.out.println("resultDto.getTotalPage() = " + resultDto.getTotalPage());

        System.out.println("--------------------------------------------");
        for (GuestbookDTO guestbookDTO : resultDto.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }

        System.out.println("--------------------------------------------");
        resultDto.getPageList().forEach(i -> System.out.println(i));
    }
}