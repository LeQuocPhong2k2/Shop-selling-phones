package com.iuh.server.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_san_pham")
    private long maSanPham;

    private String tenSanPham;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_loai_san_pham")
    @JsonIgnoreProperties("sanPhams")
    private LoaiSanPham loaiSanPham;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_thuong_hieu")
    @JsonIgnoreProperties("sanPhams")
    private ThuongHieu thuongHieu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_hinh_anh")
    @JsonIgnoreProperties("sanPhams")
    private HinhAnh hinhAnh;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_dien_thoai")
    @JsonIgnoreProperties("sanPhams")
    private DienThoai dienThoai;

    @OneToMany(mappedBy = "sanPham")
    @JsonManagedReference
    private List<ChiTietNhapHang> chiTietNhapHangs;

    public double tinhGiaBan() {
        double giaBan = 0;
        if (chiTietNhapHangs != null && !chiTietNhapHangs.isEmpty()) {
            ChiTietNhapHang chiTietNhapHang = chiTietNhapHangs.get(0);
            giaBan = chiTietNhapHang.getDonGiaNhap() + chiTietNhapHang.getChiPhiLuuKho()
                    + chiTietNhapHang.getChiPhiQuanLy()
                    + chiTietNhapHang.getDonGiaNhap() * chiTietNhapHang.getPhanTramLoiNhuan();
        }
        return giaBan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SanPham{");
        sb.append("maSanPham=").append(maSanPham);
        sb.append(", tenSanPham='").append(tenSanPham).append('\'');
        sb.append(", loaiSanPham=");
        if (loaiSanPham != null) {
            sb.append(loaiSanPham.getMaLoaiSanPham());
        } else {
            sb.append("null");
        }
        sb.append(", thuongHieu=");
        if (thuongHieu != null) {
            sb.append(thuongHieu.getMaThuongHieu());
        } else {
            sb.append("null");
        }
        sb.append(", hinhAnh=");
        if (hinhAnh != null) {
            sb.append(hinhAnh.getMaHinhAnh());
        } else {
            sb.append("null");
        }
        sb.append(", dienThoai=");
        if (dienThoai != null) {
            sb.append(dienThoai.getMaDienThoai());
        } else {
            sb.append("null");
        }
        sb.append(", chiTietNhapHangs=");
        if (chiTietNhapHangs != null) {
            sb.append(chiTietNhapHangs.size());
        } else {
            sb.append("null");
        }
        sb.append('}');
        return sb.toString();
    }
}
