package com.iuh.server.model.entity;

import java.util.List;

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
@Table(name = "dien_thoai")
public class DienThoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dien_thoai")
    private long maDienThoai;

    private String manHinh;

    private String heDieuHanh;

    private int ram;

    private int rom;

    @OneToMany(mappedBy = "dienThoai")
    @JsonManagedReference
    private List<SanPham> sanPhams;

}
