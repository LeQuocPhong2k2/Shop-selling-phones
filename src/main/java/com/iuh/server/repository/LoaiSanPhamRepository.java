package com.iuh.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.server.model.entity.LoaiSanPham;

public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham);
}
