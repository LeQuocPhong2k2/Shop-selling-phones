package com.iuh.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.server.model.entity.SanPham;
import com.iuh.server.model.entity.ThuongHieu;
import com.iuh.server.repository.ThuongHieuRepository;
import com.iuh.server.service.ThuongHieuService;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> findAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu findByTenThuongHieu(String tenThuongHieu) {
        return thuongHieuRepository.findByTenThuongHieu(tenThuongHieu);
    }

    @Override
    public ThuongHieu saveThuongHieu(ThuongHieu thuongHieu) {
        return thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public List<SanPham> findByThuongHieu(ThuongHieu thuongHieu) {
        return thuongHieuRepository.findByTenThuongHieu(thuongHieu);
    }

}
