package com.iuh.server.service;

import java.util.List;

import com.iuh.server.model.entity.SanPham;
import com.iuh.server.model.entity.ThuongHieu;

public interface ThuongHieuService {
    List<ThuongHieu> findAll();

    ThuongHieu findByTenThuongHieu(String tenThuongHieu);

    ThuongHieu saveThuongHieu(ThuongHieu thuongHieu);

    List<SanPham> findByThuongHieu(ThuongHieu thuongHieu);
}
