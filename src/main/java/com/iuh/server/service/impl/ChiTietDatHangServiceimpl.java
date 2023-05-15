package com.iuh.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.NhaCungCap;
import com.iuh.server.model.entity.SanPham;
import com.iuh.server.repository.ChiTietDatHangRepository;
import com.iuh.server.repository.SanPhamRepository;
import com.iuh.server.service.ChiTietDatHangService;

@Service
public class ChiTietDatHangServiceimpl implements ChiTietDatHangService {
    @Autowired
    ChiTietDatHangRepository repository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Override
    public ChiTietNhapHang save(ChiTietNhapHang chiTietNhapHang) {
        return repository.save(chiTietNhapHang);
    }

    @Override
    public List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap) {
        return repository.findByNhaCungCap(nhaCungCap);
    }

    @Override
    public void deleteBymaSanPham(Long id) {
        repository.deleteBymaSanPham(id);
    }

    @Override
    public boolean deletingByMaSanPham_thenDeletingSanPham(Long id) {

        return false;
    }

    @Override
    public ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc) {
        return repository.findByMaSanPhamAndMaNhaCungCap(idsp, idncc);
    }

    @Override
    public ChiTietNhapHang update(ChiTietNhapHang chiTietNhapHang) {
        return repository.save(chiTietNhapHang);
    }

    @Override
    public List<ChiTietNhapHang> findByGiaNhapBetween(double max, double min) {
        return repository.findByDonGiaNhapBetween(max, min);
    }

}