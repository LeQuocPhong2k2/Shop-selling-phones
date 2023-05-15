package com.iuh.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.DienThoai;
import com.iuh.server.model.entity.HinhAnh;
import com.iuh.server.model.entity.LoaiSanPham;
import com.iuh.server.model.entity.NhaCungCap;
import com.iuh.server.model.entity.SanPham;
import com.iuh.server.model.entity.ThuongHieu;
import com.iuh.server.model.request.SaveSanPhamRequest;
import com.iuh.server.service.impl.ChiTietDatHangServiceimpl;
import com.iuh.server.service.impl.LoaiSanPhamServiceImpl;
import com.iuh.server.service.impl.NhaCungCapServiceImpl;
import com.iuh.server.service.impl.SanPhamServiceImpl;
import com.iuh.server.service.impl.ThuongHieuServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    NhaCungCapServiceImpl nhaCungCapService;

    @Autowired
    ChiTietDatHangServiceimpl chiTietDatHangService;

    @Autowired
    ThuongHieuServiceImpl thuongHieuCapService;

    @Autowired
    SanPhamServiceImpl sanPhamServiceImpl;

    @Autowired
    LoaiSanPhamServiceImpl loaiSanPhamServiceImpl;

    @Autowired
    private Environment environment;

    @RequestMapping(value = { "", "/sanpham/danh-sach-san-pham" }, method = RequestMethod.GET)
    public ModelAndView admin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/ListProduct");

        List<SanPham> sanPhams = sanPhamServiceImpl.findAll();
        modelAndView.addObject("sanPhams", sanPhams);

        return modelAndView;
    }

    @RequestMapping(value = {
            "/sanpham/them-san-pham" }, method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView getPageThemSanPham() {
        ModelAndView modelAndView = new ModelAndView("admin/AddProduct");
        modelAndView.addObject("nccs", nhaCungCapService.findAll());
        modelAndView.addObject("thuongHieus", thuongHieuCapService.findAll());
        return modelAndView;
    }

    @PostMapping("/sanpham/them-san-pham")
    public String themSanPham(@RequestBody SaveSanPhamRequest request) {

        NhaCungCap nhaCungCap = new NhaCungCap();
        if (nhaCungCapService.findByTenNhaCungCap(request.getTenNCC()) != null) {
            nhaCungCap = nhaCungCapService.findByTenNhaCungCap(request.getTenNCC());
        } else {
            nhaCungCap.setTenNhaCungCap(request.getTenNCC());
            nhaCungCap.setDiaChi(request.getDiaChiNCC());
            nhaCungCap.setEmail(request.getEmailNCC());
            nhaCungCapService.save(nhaCungCap);
        }

        ThuongHieu thuongHieu = new ThuongHieu();
        if (thuongHieuCapService.findByTenThuongHieu(request.getTenThuongHieu()) != null) {
            thuongHieu = thuongHieuCapService.findByTenThuongHieu(request.getTenThuongHieu());
        } else {
            thuongHieu.setTenThuongHieu(request.getTenThuongHieu());
            thuongHieuCapService.saveThuongHieu(thuongHieu);
        }
        thuongHieu.setTenThuongHieu(request.getTenThuongHieu());

        LoaiSanPham loaiSanPham = new LoaiSanPham();
        if (loaiSanPhamServiceImpl.findLoaiSanPhamByTenLoaiSanPham(request.getTenHangMuc()) != null) {
            loaiSanPham = loaiSanPhamServiceImpl.findLoaiSanPhamByTenLoaiSanPham(request.getTenHangMuc());
        } else {
            loaiSanPham.setTenLoaiSanPham(request.getTenHangMuc());
            loaiSanPhamServiceImpl.saveLoaiSanPham(loaiSanPham);
        }

        HinhAnh image = new HinhAnh();
        image.setUrl(request.getHinhAnh());

        DienThoai dienThoai = new DienThoai();
        dienThoai.setHeDieuHanh(request.getHeDieuHanh());
        dienThoai.setManHinh(request.getManHinh());
        dienThoai.setRam(Integer.parseInt(request.getRam()));
        dienThoai.setRom(Integer.parseInt(request.getRom()));

        SanPham sanPham = new SanPham();
        sanPham.setTenSanPham(request.getTenSanPham());
        sanPham.setThuongHieu(thuongHieu);
        sanPham.setDienThoai(dienThoai);
        sanPham.setLoaiSanPham(loaiSanPham);
        sanPham.setHinhAnh(image);

        ChiTietNhapHang chiTietNhapHang = new ChiTietNhapHang();
        chiTietNhapHang.setNhaCungCap(nhaCungCap);
        chiTietNhapHang.setDonGiaNhap(Double.parseDouble(request.getDonGiaNhapNCC()));
        chiTietNhapHang.setChiPhiLuuKho(Double.parseDouble(environment.getProperty("chiPhiLuuKho")));
        chiTietNhapHang.setChiPhiQuanLy(Double.parseDouble(environment.getProperty("chiPhiQuanLy")));
        chiTietNhapHang.setPhanTramLoiNhuan(Double.parseDouble(environment.getProperty("phanTramLoiNhuan")));
        chiTietNhapHang.setSanPham(sanPham);
        chiTietDatHangService.save(chiTietNhapHang);

        List<ChiTietNhapHang> chiTietNhapHangs = new ArrayList<ChiTietNhapHang>();

        sanPham.setChiTietNhapHangs(chiTietNhapHangs);
        sanPhamServiceImpl.save(sanPham);

        return "redirect:/admin/sanpham/danh-sach-san-pham";
    }

    @RequestMapping(value = { "/sanpham/nhacungcap" }, method = RequestMethod.POST)
    public ResponseEntity<?> getNhaCungCap(HttpServletRequest request) {
        String nameNcc = request.getParameter("nameNcc").trim();

        NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(nameNcc);

        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        if (nhaCungCap == null) {
            objectNode.put("message", "null");
        } else {
            nhaCungCap.setChiTietNhapHangs(chiTietDatHangService.findByNhaCungCap(nhaCungCap));
            objectNode.put("tenNhaCungCap", nhaCungCap.getTenNhaCungCap());
            objectNode.put("diaChi", nhaCungCap.getDiaChi());
            objectNode.put("email", nhaCungCap.getEmail());
            objectNode.put("dongia", nhaCungCap.getChiTietNhapHangs().get(0).getDonGiaNhap());
        }

        return ResponseEntity.ok(objectNode);
    }

    @RequestMapping(value = { "/sanpham/delete" }, method = RequestMethod.GET)
    public String deleteSanpham(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));

        chiTietDatHangService.deleteBymaSanPham(id);
        sanPhamServiceImpl.deleteBymaSanPham(id);

        return "redirect:/admin/sanpham/danh-sach-san-pham";
    }

    @RequestMapping(value = { "/sanpham/view" }, method = RequestMethod.GET)
    public ModelAndView getSanPhamById(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("admin/ViewProduct");

        long id_sp = request.getParameter("id_sp") == null ? 0 : Long.parseLong(request.getParameter("id_sp"));
        String ten_ncc = request.getParameter("ten_ncc") == null ? "" : request.getParameter("ten_ncc");

        if (id_sp == 0 && ten_ncc.equals("")) {
            modelAndView.addObject("message", "null");
        } else {
            SanPham sanPham = sanPhamServiceImpl.findBymaSanPham(id_sp);
            NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(ten_ncc);

            ChiTietNhapHang chiTietNhapHang = chiTietDatHangService.findByMaSanPhamAndMaNhaCungCap(
                    sanPham.getMaSanPham(),
                    nhaCungCap.getMaNhaCungCap());

            modelAndView.addObject("message", "success");

            modelAndView.addObject("chiTietNhapHang", chiTietNhapHang);
        }

        return modelAndView;
    }

    @RequestMapping(value = { "/sanpham/view" }, method = RequestMethod.POST)
    public ResponseEntity<?> inforSanPham(HttpServletRequest request) {
        long id_sp = request.getParameter("id_sp") == null ? 0 : Long.parseLong(request.getParameter("id_sp"));
        String ten_ncc = request.getParameter("ten_ncc") == null ? "" : request.getParameter("ten_ncc");

        if (id_sp != 0 && !ten_ncc.equals("")) {
            SanPham sanPham = sanPhamServiceImpl.findBymaSanPham(id_sp);
            NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(ten_ncc);

            ChiTietNhapHang chiTietNhapHang = chiTietDatHangService.findByMaSanPhamAndMaNhaCungCap(
                    sanPham.getMaSanPham(),
                    nhaCungCap.getMaNhaCungCap());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sanPham", sanPham);
            map.put("nhaCungCaps", nhaCungCapService.findAll());
            map.put("nhaCungCap", chiTietNhapHang.getNhaCungCap());
            map.put("chiTietNhapHang", chiTietNhapHang);
            map.put("hinhAnh", sanPham.getHinhAnh());
            map.put("dienThoai", sanPham.getDienThoai());
            map.put("thuongHieus", thuongHieuCapService.findAll());
            map.put("thuongHieu", sanPham.getThuongHieu());

            return ResponseEntity.ok(map);

        }
        return ResponseEntity.ok("bad");
    }

    @RequestMapping(value = { "/sanpham/update" }, method = RequestMethod.POST)
    public String capNhatSanPham(HttpServletRequest request) {
        long maSanPham = Long.parseLong(request.getParameter("maSanPham"));
        String tenSanPham = request.getParameter("tenSanPham");
        String url = request.getParameter("hinhAnh");
        int ram = Integer.parseInt(request.getParameter("ram"));
        int rom = Integer.parseInt(request.getParameter("rom"));
        String manHinh = request.getParameter("manHinh");
        String tenThuongHieu = request.getParameter("tenThuongHieu");
        String tenNhaCungCap = request.getParameter("tenNhaCungCap");
        String cpu = request.getParameter("cpu");
        Double donGiaNhap = Double.parseDouble(request.getParameter("donGiaNhap"));

        ThuongHieu thuongHieu = thuongHieuCapService.findByTenThuongHieu(tenThuongHieu);

        SanPham sanPham = sanPhamServiceImpl.findBymaSanPham(maSanPham);
        sanPham.setTenSanPham(tenSanPham);
        sanPham.setThuongHieu(thuongHieu);

        NhaCungCap nhaCungCap = nhaCungCapService.findByTenNhaCungCap(tenNhaCungCap);

        HinhAnh hinhAnh = sanPham.getHinhAnh();
        hinhAnh.setUrl(url);

        DienThoai dienThoai = sanPham.getDienThoai();
        dienThoai.setRam(ram);
        dienThoai.setRom(rom);
        dienThoai.setManHinh(manHinh);
        dienThoai.setHeDieuHanh(cpu);

        ChiTietNhapHang chiTietNhapHang = sanPham.getChiTietNhapHangs().get(0);
        chiTietNhapHang.setNhaCungCap(nhaCungCap);
        chiTietNhapHang.setDonGiaNhap(donGiaNhap);
        chiTietNhapHang.setSanPham(sanPham);

        chiTietDatHangService.save(chiTietNhapHang);
        sanPhamServiceImpl.save(sanPham);

        return "redirect:/admin/sanpham/danh-sach-san-pham";
    }

    // search
    @RequestMapping(value = { "/sanpham/search" }, method = RequestMethod.POST)
    public ResponseEntity<?> searchSanPham(HttpServletRequest request) {
        String val = request.getParameter("val");
        return ResponseEntity.ok(sanPhamServiceImpl.findByTenSanPhamContaining(val));
    }
}
