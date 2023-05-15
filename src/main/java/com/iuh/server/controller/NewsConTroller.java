package com.iuh.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsConTroller {

    @GetMapping("/news")
    public String news() {
        return "public/News";
    }
}
