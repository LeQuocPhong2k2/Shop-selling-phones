package com.iuh.server.controller;

import com.iuh.server.model.entity.Account;
import com.iuh.server.model.entity.Breadcrumb;
import com.iuh.server.model.entity.SanPham;
import com.iuh.server.repository.SanPhamRepository;
import com.iuh.server.service.impl.ChiTietDatHangServiceimpl;
import com.iuh.server.service.impl.SanPhamServiceImpl;
import com.iuh.server.service.impl.ThuongHieuServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/san-pham")
public class ProductController {
    @Autowired
    private SanPhamServiceImpl repository;

    @Autowired
    ChiTietDatHangServiceimpl chiTietDatHangService;

    @Autowired
    ThuongHieuServiceImpl thuongHieuService;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/dien-thoai", method = RequestMethod.GET)
    public String list(Principal principal, Model model) {
        try {
            String userName = null;
            model.addAttribute("sanphams", repository.findAll());
            model.addAttribute("breadcrumb", "Điện thoại");
            model.addAttribute("href", "/san-pham/dien-thoai");
            if (principal != null) {
                Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                model.addAttribute("account", account);
                return "public/Product";
            }
            model.addAttribute("username", userName);
            return "public/Product";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/error";
        }

    }

    @RequestMapping(value = "/dien-thoai/show", method = RequestMethod.GET)
    public String searchProductPost(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");

        SanPham product = repository.findBymaSanPham(Long.parseLong(id));

        model.addAttribute("sanphams", repository.findAll());

        model.addAttribute("product", product);

        Breadcrumb breadcrumb = new Breadcrumb("Điện thoại", "/san-pham/dien-thoai");
        Breadcrumb breadcrumb1 = new Breadcrumb(product.getTenSanPham(),
                "/san-pham/dien-thoai/show?id=" + product.getMaSanPham());
        List<Breadcrumb> breadcrumblink = new ArrayList<>();
        breadcrumblink.add(breadcrumb);
        breadcrumblink.add(breadcrumb1);
        model.addAttribute("breadcrumb", breadcrumblink);

        return "public/ProductDetail";
    }

    // filter
    @RequestMapping(value = "/fillter", method = RequestMethod.POST)
    public ResponseEntity<?> filter(HttpServletRequest request) {
        String brand = request.getParameter("brand");
        Set<SanPham> set = new HashSet<>();
        if (brand.equals("all")) {
            return ResponseEntity.ok(repository.findAll());
        }
        String[] arrBand = brand.split(",");
        for (int i = 0; i < arrBand.length; i++) {
            arrBand[i] = arrBand[i].trim();
            if (!arrBand[i].equals(" ")) {
                set.addAll(
                        thuongHieuService.findByTenThuongHieu(arrBand[i]).getSanPhams());
            }
        }

        return ResponseEntity.ok(set);
    }

    @RequestMapping(value = "/fillter", method = RequestMethod.GET)
    public String refreshFillter() {
        return "redirect:/san-pham/dien-thoai";
    }
}
