package me.sung.security.controller;

import me.sung.security.dto.JoinDTO;
import me.sung.security.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinForm(JoinDTO joinDTO) {

        joinService.joinFrom(joinDTO);

        return "redirect:/login";
    }
}
