package com.iuh.server.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maChiTietNhapHang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nha_cung_cap")
    @JsonIgnoreProperties("chiTietNhapHangs")
    private NhaCungCap nhaCungCap;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_san_pham")
    @JsonBackReference
    private SanPham sanPham;

    private double donGiaNhap;

    private double chiPhiLuuKho;

    private double chiPhiQuanLy;

    private double phanTramLoiNhuan;

}
