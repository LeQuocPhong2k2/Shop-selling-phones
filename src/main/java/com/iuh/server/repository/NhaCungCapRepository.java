package com.iuh.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuh.server.model.entity.NhaCungCap;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Long> {
    // exists tenNhaCungCap
    boolean existsByTenNhaCungCap(String tenNhaCungCap);

    // find by tenNhaCungCap
    NhaCungCap findByTenNhaCungCap(String tenNhaCungCap);

}
