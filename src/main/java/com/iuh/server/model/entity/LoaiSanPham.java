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
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_loai_san_pham")
    private long maLoaiSanPham;

    private String tenLoaiSanPham;

    @OneToMany(mappedBy = "loaiSanPham")
    private List<SanPham> sanPhams;

}
