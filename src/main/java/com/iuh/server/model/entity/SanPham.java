package com.iuh.server.model.entity;

import java.util.List;

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
    private LoaiSanPham loaiSanPham;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_thuong_hieu")
    private ThuongHieu thuongHieu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_hinh_anh")
    private HinhAnh hinhAnh;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_dien_thoai")
    private DienThoai dienThoai;

    @OneToMany(mappedBy = "sanPham")
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
}
