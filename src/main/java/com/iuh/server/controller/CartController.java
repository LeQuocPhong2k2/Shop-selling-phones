package com.iuh.server.controller;

import com.iuh.server.model.entity.Account;
import com.iuh.server.model.entity.ChiTietGioHang;
import com.iuh.server.model.entity.GioHang;
import com.iuh.server.model.entity.SanPham;
import com.iuh.server.repository.GioHangRepository;
import com.iuh.server.repository.SanPhamRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam("id") Long productId, HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        System.out.println("Kaiwin Add TO Cart");
        System.out.println(cart);

        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                // Nếu sản phẩm đã tồn tại, tăng số lượng
                cartItem.setSoLuong(cartItem.getSoLuong() + 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới
            Optional<SanPham> product = sanPhamRepository.findById(productId);
            if (product.isPresent()) {
                SanPham sanPham = product.get();
                ChiTietGioHang cartItem = new ChiTietGioHang();
                cartItem.setSanPham(sanPham);
                cartItem.setSoLuong(1);
                cartItem.setDiscount(sanPham.getChiTietNhapHangs().get(0).getDonGiaNhap()
                        + (sanPham.getChiTietNhapHangs().get(0).getChiPhiLuuKho()
                                + sanPham.getChiTietNhapHangs().get(0).getChiPhiQuanLy())
                                * sanPham.getChiTietNhapHangs().get(0).getPhanTramLoiNhuan());
                cart.getChiTietGioHangs().add(cartItem);
            }
        }

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/checkout")
    public String checkoutCart(HttpSession session) {
        // Lấy giỏ hàng từ session
        GioHang cart = (GioHang) session.getAttribute("cart");
        System.out.println("Kaii CheckkOUT ");
        System.out.println(cart);

        // Thiết lập giá trị 'ma_gio_hang' trong ChiTietGioHang thành 1
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            cartItem.setGioHang(cart);
        }

        // Lưu giỏ hàng vào cơ sở dữ liệu
        gioHangRepository.save(cart);

        // Xóa giỏ hàng khỏi session
        session.removeAttribute("cart");

        // Chuyển hướng đến trang hoàn thành thanh toán
        return "public/Home";
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        GioHang cart = (GioHang) session.getAttribute("cart");
        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo mới một đối tượng Cart
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }
        double totalAmount = 0.0;
        for (ChiTietGioHang item : cart.getChiTietGioHangs()) {
            double itemPrice = (item.getSanPham().getChiTietNhapHangs().get(0).getDonGiaNhap()
                    + item.getSanPham().getChiTietNhapHangs().get(0).getChiPhiLuuKho()
                    + item.getSanPham().getChiTietNhapHangs().get(0).getChiPhiQuanLy())
                    * item.getSanPham().getChiTietNhapHangs().get(0).getPhanTramLoiNhuan();
            totalAmount += itemPrice;
        }
        String userName = null;
        if (principal != null) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("account", account);
            return "user/cart";
        }
        model.addAttribute("username", userName);
        return "user/cart";
    }

    @PostMapping("/increase")
    public ResponseEntity<?> increase(@RequestParam("id") Long productId, HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        System.out.println(cart);

        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                // Nếu sản phẩm đã tồn tại, tăng số lượng
                cartItem.setSoLuong(cartItem.getSoLuong() + 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
        }

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/decrease")
    public ResponseEntity<?> decrease(@RequestParam("id") Long productId, HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("cart");
        System.out.println(cart);

        if (cart == null) {
            // Nếu giỏ hàng chưa tồn tại, tạo một giỏ hàng mới
            cart = new GioHang();
            session.setAttribute("cart", cart);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean isProductExists = false;
        for (ChiTietGioHang cartItem : cart.getChiTietGioHangs()) {
            if (cartItem.getSanPham().getMaSanPham() == productId) {
                // Nếu sản phẩm đã tồn tại, tăng số lượng
                cartItem.setSoLuong(cartItem.getSoLuong() - 1);
                isProductExists = true;
                break;
            }
        }

        if (!isProductExists) {
        }

        return ResponseEntity.ok("ok");
    }

}