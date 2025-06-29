package com.example.springsecurity.controller;

import com.example.springsecurity.dto.JoinDTO;
import com.example.springsecurity.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired  // 필드 주입 지양...  꼭 생성자 주입으로 사용할 것
    private JoinService joinService;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }

    @GetMapping("/login")
    public String loginP(){
        return "login";
    }

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcP(JoinDTO joinDTO){
        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }
}
