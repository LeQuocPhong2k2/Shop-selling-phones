package com.iuh.server.service;

import com.iuh.server.model.entity.LoaiSanPham;

public interface LoaiSanPhamService {

    LoaiSanPham saveLoaiSanPham(LoaiSanPham loaiSanPham);

    LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham);
}
