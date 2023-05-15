package com.iuh.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iuh.server.model.entity.DienThoai;

public interface DienThoaiRepository extends JpaRepository<DienThoai, Long> {

    @Query(value = "select * from dien_thoai where ma_san_pham=?1", nativeQuery = true)
    DienThoai findByMaDienThoai(long maDienThoai);
}
