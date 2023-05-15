package com.iuh.server.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "nha_cung_cap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nha_cung_cap")
    private long maNhaCungCap;

    @Column(name = "ten_nha_cung_cap", columnDefinition = "NVARCHAR(255)")
    private String tenNhaCungCap;

    private String diaChi;

    private String email;

    @OneToMany(mappedBy = "nhaCungCap")
    @JsonIgnore
    private List<ChiTietNhapHang> chiTietNhapHangs;

    @Override
    public String toString() {
        return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", diaChi=" + diaChi
                + ", email=" + email + "]";
    }

}
