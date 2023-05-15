package com.iuh.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iuh.server.model.entity.Account;
import com.iuh.server.service.impl.SanPhamServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SanPhamServiceImpl sanPhamServiceImpl;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public ModelAndView requestMethodName(HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("public/Search");
        String searchVal = request.getParameter("searchVal");
        if (sanPhamServiceImpl.findByTenSanPhamContaining(searchVal).size() == 0) {
            modelAndView.addObject("ds", "null");
        } else {
            modelAndView.addObject("ds", sanPhamServiceImpl.findByTenSanPhamContaining(searchVal));
        }
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
