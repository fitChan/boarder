package com.sparta.board3.controller;

import com.sparta.board3.model.Board;
import com.sparta.board3.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;


    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false)Long id ){
        if(id== null) {
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElseThrow(
                    ()-> new IllegalArgumentException("오류!")
            );
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}