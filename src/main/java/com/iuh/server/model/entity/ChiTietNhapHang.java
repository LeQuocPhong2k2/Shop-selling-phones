package com.iuh.server.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chi_tiet_nhap_hang")
public class ChiTietNhapHang {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_nha_cung_cap")
    private NhaCungCap nhaCungCap;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    private int donGiaNhap;

    private double chiPhiLuuKho;

    private double chiPhiQuanLy;

    private double phanTramLoiNhuan;

}
