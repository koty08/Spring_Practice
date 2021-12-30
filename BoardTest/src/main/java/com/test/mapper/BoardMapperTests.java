package com.test.mapper;


import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.test.config.RootConfig.class})
@Log4j
public class BoardMapperTests {
    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setPageNum(2);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board.getBno()));
    }

    @Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성 글");
        board.setContent("새로 작성 내용");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성 글 select key");
        board.setContent("새로 작성 내용 select key");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testRead(){
        BoardVO board= mapper.read(23L);
        log.info(board);
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT: " + mapper.delete(23L));
    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        board.setBno(21L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user01");

        int count = mapper.update(board);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setKeyword("새로");
        cri.setType("TC");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board-> log.info(board));
    }
}
