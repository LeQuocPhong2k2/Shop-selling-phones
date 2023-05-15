package com.iuh.server.service;

import java.util.List;

import com.iuh.server.model.entity.SanPham;

public interface SanPhamService {
    SanPham save(SanPham sanPham);

    List<SanPham> findAll();

    void deleteBymaSanPham(Long id);

    SanPham findBymaSanPham(Long id);

    SanPham update(SanPham sanPham);

    List<SanPham> findByTenSanPhamContaining(String tenSanPham);
}
