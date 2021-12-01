package test.guestbook.service;

import test.guestbook.domain.Board;
import test.guestbook.domain.Member;
import test.guestbook.dto.BoardDTO;
import test.guestbook.dto.PageRequestDTO;
import test.guestbook.dto.PageResultDTO;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void modify(BoardDTO boardDTO);

    void removeWithReplies(Long bno);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
