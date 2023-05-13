package com.iuh.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/ListProduct");

        // Page<Product> page = productService.findAll(PageRequest.of(0, 4));
        List<Integer> listPage = new ArrayList<Integer>();
        for (int i = 0; i < 2; i++) {
            listPage.add(i);
        }
        modelAndView.addObject("pages", listPage);
        modelAndView.addObject("products", null);

        return modelAndView;
    }

    @RequestMapping(value = { "/sanpham/them-san-pham" }, method = RequestMethod.GET)
    public ModelAndView getPageThemSanPham() {
        ModelAndView modelAndView = new ModelAndView("admin/AddProduct");
        return modelAndView;
    }
}
