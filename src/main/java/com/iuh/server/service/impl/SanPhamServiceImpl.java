package com.iuh.server.service.impl;

import com.iuh.server.model.entity.SanPham;
import com.iuh.server.repository.SanPhamRepository;
import com.iuh.server.service.SanPhamService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository repository;

    @Override
    public SanPham save(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public List<SanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteBymaSanPham(Long id) {
        repository.deleteBymaSanPham(id);
    }

    @Override
    public SanPham findBymaSanPham(Long id) {
        return repository.findBymaSanPham(id);
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public List<SanPham> findByTenSanPhamContaining(String tenSanPham) {
        return repository.findByTenSanPhamContaining(tenSanPham);
    }

}
