package com.iuh.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.iuh.server.model.entity.ChiTietNhapHang;
import com.iuh.server.model.entity.NhaCungCap;

import jakarta.transaction.Transactional;

public interface ChiTietDatHangRepository extends JpaRepository<ChiTietNhapHang, Long> {
    // lấy thông tin đơn giá nhập where mã nhà cung cấp
    List<ChiTietNhapHang> findByNhaCungCap(NhaCungCap nhaCungCap);

    // delete where mã sản phẩm
    @Transactional
    @Modifying
    @Query(value = "delete chi_tiet_nhap_hang where ma_san_pham=?1", nativeQuery = true)
    void deleteBymaSanPham(Long id);

    // find where ma san pham va ma nha cung cap
    @Query(value = "select * from chi_tiet_nhap_hang where ma_san_pham=?1 and ma_nha_cung_cap=?2", nativeQuery = true)
    ChiTietNhapHang findByMaSanPhamAndMaNhaCungCap(long idsp, long idncc);

    // tim san pham co gia nhap < max va > min
    List<ChiTietNhapHang> findByDonGiaNhapBetween(double max, double min);

}
