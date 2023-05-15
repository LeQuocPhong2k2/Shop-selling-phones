package com.iuh.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.server.model.entity.SanPham;
import com.iuh.server.model.entity.ThuongHieu;

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {
    // find by tenThuongHieu
    ThuongHieu findByTenThuongHieu(String tenThuongHieu);

    // tim kiem san pham theo ten thuong hieu
    List<SanPham> findByTenThuongHieu(ThuongHieu thuongHieu);
}
