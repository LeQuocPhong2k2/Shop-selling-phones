package com.iuh.server.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chi_tiet_gio_hang")
public class ChiTietGioHang {

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_gio_hang")
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    private int soLuong;

    private double discount;
}
