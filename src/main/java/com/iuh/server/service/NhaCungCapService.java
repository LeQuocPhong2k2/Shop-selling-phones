package com.iuh.server.service;

import java.util.List;

import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.NhaCungCap;

public interface NhaCungCapService {

    List<NhaCungCap> findAll();

    boolean existsByTenNhaCungCap(String tenNhaCungCap);

    NhaCungCap save(NhaCungCap nhaCungCap);

    NhaCungCap findByTenNhaCungCap(String tenNhaCungCap);

}
