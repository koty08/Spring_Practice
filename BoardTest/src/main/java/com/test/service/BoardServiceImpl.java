package com.test.service;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        log.info("register......." + board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get......" + bno);

        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        log.info("remove......" + bno);

        return mapper.delete(bno) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList.........");
//
//        return mapper.getList();
//    }
    @Override
    public List<BoardVO> getList(Criteria cri){
        log.info("get list with criteria: " + cri);

        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return mapper.getTotalCount(cri);
    }
}
