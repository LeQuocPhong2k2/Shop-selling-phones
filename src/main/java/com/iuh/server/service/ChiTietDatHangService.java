package com.iuh.server.service;

import java.util.List;

import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.NhaCungCap;

public interface ChiTietDatHangService {
    ChiTietNhapHang save(ChiTietNhapHang chiTietNhapHang);

    List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap);

    void deleteBymaSanPham(Long id);

    boolean deletingByMaSanPham_thenDeletingSanPham(Long id);

    ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc);

    ChiTietNhapHang update(ChiTietNhapHang chiTietNhapHang);

    List<ChiTietNhapHang> findByGiaNhapBetween(double max, double min);
}
