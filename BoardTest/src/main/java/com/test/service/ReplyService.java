package com.test.service;

import com.test.domain.Criteria;
import com.test.domain.ReplyPageDTO;
import com.test.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

  public int register(ReplyVO vo);

  public ReplyVO get(Long rno);

  public int modify(ReplyVO vo);

  public int remove(Long rno);

  public List<ReplyVO> getList(Criteria cri, Long bno);

  public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
