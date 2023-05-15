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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoaiSanPham{");
        sb.append("maLoaiSanPham=").append(maLoaiSanPham);
        sb.append(", tenLoaiSanPham='").append(tenLoaiSanPham).append('\'');
        sb.append(", sanPhams=");
        if (sanPhams != null) {
            sb.append(sanPhams.size());
        } else {
            sb.append("null");
        }
        sb.append('}');
        return sb.toString();
    }
}
