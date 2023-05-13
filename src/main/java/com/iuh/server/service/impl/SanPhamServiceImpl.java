package com.iuh.server.service.impl;

import com.iuh.server.repository.SanPhamRepository;
import com.iuh.server.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository repository;

}
