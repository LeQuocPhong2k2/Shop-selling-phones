package com.iuh.server.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveSanPhamRequest {
    private String tenSanPham;
    private String heDieuHanh;
    private String manHinh;
    private String hinhAnh;
    private String ram;
    private String rom;
    private String tenNCC;
    private String tenThuongHieu;
    private String tenHangMuc;
    private String diaChiNCC;
    private String emailNCC;
    private String donGiaNhapNCC;
}
