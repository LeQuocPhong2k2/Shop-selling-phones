package com.iuh.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.server.model.entity.LoaiSanPham;
import com.iuh.server.repository.LoaiSanPhamRepository;
import com.iuh.server.service.LoaiSanPhamService;

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
    @Autowired
    LoaiSanPhamRepository repository;

    @Override
    public LoaiSanPham findLoaiSanPhamByTenLoaiSanPham(String tenLoaiSanPham) {
        return repository.findLoaiSanPhamByTenLoaiSanPham(tenLoaiSanPham);
    }

    @Override
    public LoaiSanPham saveLoaiSanPham(LoaiSanPham loaiSanPham) {
        return repository.save(loaiSanPham);
    }
}
