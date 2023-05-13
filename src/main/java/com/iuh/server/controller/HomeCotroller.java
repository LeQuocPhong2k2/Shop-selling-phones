package com.iuh.server.controller;

import com.iuh.server.model.entity.Account;
import com.iuh.server.repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeCotroller {

    private final SanPhamRepository repository;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("public/Home.html");
        modelAndView.addObject("sanphams", null);
        String userName = null;
        if (principal != null) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelAndView.addObject("account", account);
            return modelAndView;
        }
        modelAndView.addObject("username", userName);
        return modelAndView;
    }
}
