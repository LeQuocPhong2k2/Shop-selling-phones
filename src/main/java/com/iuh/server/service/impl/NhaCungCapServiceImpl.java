package com.iuh.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.NhaCungCap;
import com.iuh.server.repository.NhaCungCapRepository;
import com.iuh.server.service.NhaCungCapService;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    @Override
    public List<NhaCungCap> findAll() {
        return nhaCungCapRepository.findAll();
    }

    @Override
    public boolean existsByTenNhaCungCap(String tenNhaCungCap) {
        return nhaCungCapRepository.existsByTenNhaCungCap(tenNhaCungCap);
    }

    @Override
    public NhaCungCap save(NhaCungCap nhaCungCap) {
        return nhaCungCapRepository.save(nhaCungCap);
    }

    @Override
    public NhaCungCap findByTenNhaCungCap(String tenNhaCungCap) {
        return nhaCungCapRepository.findByTenNhaCungCap(tenNhaCungCap);
    }

}
