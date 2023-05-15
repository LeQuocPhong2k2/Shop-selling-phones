package com.iuh.server.model.entity;

import java.util.*;

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
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_gio_hang")
    private long maGioHang;

    private Date ngayTao;

    private boolean trangThaiThanhToan;

    @ManyToOne
    @JoinColumn(name = "id")
    private Account account;

    @OneToMany(mappedBy = "gioHang", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ChiTietGioHang> chiTietGioHangs = new ArrayList<>();

}
