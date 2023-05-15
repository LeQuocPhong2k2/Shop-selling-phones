package com.iuh.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuh.server.model.entity.DienThoai;
import com.iuh.server.repository.DienThoaiRepository;
import com.iuh.server.service.DienThoaiService;

@Service
public class DienThoaiServiceImpl implements DienThoaiService {
    @Autowired
    DienThoaiRepository repository;

    @Override
    public DienThoai findByMaDienThoai(long maDienThoai) {
        return repository.findByMaDienThoai(maDienThoai);
    }

}
