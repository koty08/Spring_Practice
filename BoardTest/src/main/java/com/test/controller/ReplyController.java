package com.test.controller;

import com.test.domain.Criteria;
import com.test.domain.ReplyPageDTO;
import com.test.domain.ReplyVO;
import com.test.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
    private ReplyService service;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo){
        log.info("ReplyVO: " + vo);
        int insertCount = service.register(vo);
        log.info("Reply insert Count : " + insertCount);

        return insertCount == 1 ?
                new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}" , produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") String page, @PathVariable("bno") Long bno){
        //.json이면 json으로 return하게 설정
        Criteria cri;
        HttpHeaders responseHeaders = new HttpHeaders();

        if(page.contains(".")){
            int tmp = Integer.parseInt(page.substring(0,page.indexOf(".")));
            cri = new Criteria(tmp, 10);
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        }
        else{
            cri = new Criteria(Integer.parseInt(page), 10);
            responseHeaders.setContentType(MediaType.APPLICATION_XML);
        }
        log.info("getList............");
        log.info(cri);

        return new ResponseEntity<>(service.getListPage(cri, bno), responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno")String rno){
        log.info("get: " + rno);
        HttpHeaders responseHeaders = new HttpHeaders();

        //.json이면 json으로 return하게 설정
        if(rno.contains(".")){
            rno = rno.substring(0, rno.indexOf("."));
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        }
        else{
            responseHeaders.setContentType(MediaType.APPLICATION_XML);
        }
        return new ResponseEntity<>(service.get(Long.parseLong(rno)), responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}",produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno")Long rno){
        log.info("remove : " + rno);

        return service.remove(rno) == 1 ?
                new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", consumes = "application/json",
    produces =  {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
        vo.setRno(rno);

        log.info("rno: " + rno);
        log.info("modify: " + vo);

        return service.modify(vo) == 1?
                new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
