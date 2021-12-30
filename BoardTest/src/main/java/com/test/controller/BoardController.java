package com.test.controller;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.PageDTO;
import com.test.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model){
//        log.info("controller-list");
//        model.addAttribute("list", service.getList());
//    }
    @GetMapping("/list")
    public void list(Criteria cri, Model model){
        log.info("controller-list : " +cri);
        model.addAttribute("list", service.getList(cri));
//        model.addAttribute("pageMaker", new PageDTO(cri, 123));

        int total = service.getTotal(cri);
        log.info("total: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes redirectAttributes){
        log.info("register: " + board);

        service.register(board);
        redirectAttributes.addFlashAttribute("result", board.getBno());

        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public void register() {

    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes redirectAttributes){
        log.info("modify: "+ board);

        if(service.modify(board)){
            redirectAttributes.addFlashAttribute("result", "success");
        }

        // standard
        redirectAttributes.addAttribute("pageNum", cri.getPageNum());
        redirectAttributes.addAttribute("amount", cri.getAmount());
        redirectAttributes.addAttribute("type", cri.getType());
        redirectAttributes.addAttribute("keyword", cri.getKeyword());

        //     using UriComponentsBuilder
        // return "redirect:/board/list" + cri.getListLink();
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes redirectAttributes){
        log.info("remove..." + bno);
        if(service.remove(bno)){
            redirectAttributes.addFlashAttribute("result", "success");
        }

        // standard
        redirectAttributes.addAttribute("pageNum", cri.getPageNum());
        redirectAttributes.addAttribute("amount", cri.getAmount());
        redirectAttributes.addAttribute("type", cri.getType());
        redirectAttributes.addAttribute("keyword", cri.getKeyword());

        //     using UriComponentsBuilder
        // return "redirect:/board/list" + cri.getListLink();
        return "redirect:/board/list";
    }
}
