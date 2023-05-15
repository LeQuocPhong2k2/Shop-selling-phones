let btndeleteproducts = document.querySelectorAll('#btn-delete-product');
let idDel = 0;
btndeleteproducts.forEach(function (item) {
   item.addEventListener('click', function (e) {
      e.preventDefault();
      $('#modalConfirmDelete').modal('show');
      idDel = item.getAttribute('value');
   });
});

let btnconfirmdelete = document.getElementById('btn-confirm-delete');
btnconfirmdelete.addEventListener('click', function (e) {
   $.ajax({
      url: '/admin/sanpham/delete?id=' + idDel,
      type: 'GET',
      success: function (result) {
         window.location.href = '/admin/sanpham/danh-sach-san-pham';
      },
   });
});

let btnviewproducts = document.querySelectorAll('#btn-view-product');
btnviewproducts.forEach(function (item) {
   item.addEventListener('click', function (e) {
      e.preventDefault();
      let id_tenncc = item.getAttribute('value').split(',');
      $.ajax({
         url: '/admin/sanpham/view?' + 'id_sp=' + id_tenncc[0] + '&ten_ncc=' + id_tenncc[1],
         type: 'GET',
         success: function (result) {
            window.location.href = '/admin/sanpham/view?id_sp=' + id_tenncc[0] + '&ten_ncc=' + id_tenncc[1];
         },
      });
   });
});

let id_tenncc;
let btnupdateproducts = document.querySelectorAll('#btn-update-product');
btnupdateproducts.forEach(function (item) {
   item.addEventListener('click', function (e) {
      id_tenncc = item.getAttribute('value').split(',');
      $.ajax({
         url: '/admin/sanpham/view',
         type: 'POST',
         data: {
            id_sp: id_tenncc[0],
            ten_ncc: id_tenncc[1],
         },
         success: function (data) {
            createModalInforNCC(data);
         },
      });
   });
});

function createModalInforNCC(data) {
   $('#modalUpdateProduct').html(
      `
   <div class="modal-dialog modal-lg">
   <div class="modal-content">
      <div class="modal-header">
         <h5
            class="modal-title"
            id="exampleModalLabel">
            <i class="fa-solid fa-circle-info"></i>Thông tin sản phẩm
         </h5>
         <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
      </div>
      <div class="modal-body">
         <div class="col">
            <div class="row">
               <table class="table p-0 m-0 p-2 fs-6">
                  <tbody>
                     <tr>
                     <td class="border-0">
                        <span class="fw-normal p-0 m-0">Tên sản phẩm:</span>
                        <input id="inputtenSanPham" type="text" class="form-control border-0 bg-light" value="${data.sanPham.tenSanPham}" placeholder="Tên sản phẩm">
                     </td>
                     </tr>
                     <tr>
                        <td class="d-flex justify-content-center align-items-center">
                           <img
                              id="img-hinhAnh"
                              style="max-width: 40%"
                              src="${data.hinhAnh.url}"
                              alt="" />
                              <i id="change-img" style="margin-left: 4rem;" class="fa-solid fa-right-left"></i>
                        </td>
                        <td>
                           <div class="mt-4 d-flex align-items-center justify-content-center">
                              <div class="mt-4">
                                 <span class="fw-normal p-0 m-0">Hình ảnh:</span>
                                 <div class="d-flex align-items-center bg-light rounded-pill border p-1">
                                    <input id="inputhinhAnh" type="text" class="border-0 bg-light" value="${data.hinhAnh.url}" placeholder="Link ảnh">
                                    <i id="btn-clear-img" style="cursor: pointer;" class="fa-solid fa-circle-xmark"></i>
                                 </div>
                              </div>
                           </div>
                        </td>
                     </tr>
                     <tr>
                        <td>
                           <span class="fw-normal p-0 m-0">Đơn giá nhập:</span>
                           <input id="inputdonGiaNhap" type="number" class="form-control border-0 bg-light" value="${data.chiTietNhapHang.donGiaNhap}" placeholder="Đơn giá bán">
                        </td>
                        <td>
                           <span class="fw-normal p-0 m-0">Đơn giá bán (Bao gồm VAT):</span>
                           <input  disabled id="inputdonGiaBan" type="text" class="form-control border-0 bg-light" value="${data.chiTietNhapHang.donGiaNhap + data.chiTietNhapHang.chiPhiLuuKho + data.chiTietNhapHang.chiPhiQuanLy + data.chiTietNhapHang.donGiaNhap * data.chiTietNhapHang.phanTramLoiNhuan}" placeholder="Đơn giá bán">
                        </td>
                     </tr>
                  </tbody>
               </table>
            </div>
            <div class="row">
               <div class="col">
                  <div class="row">
                     <span class="fw-bolder p-0 m-0 p-2">Thông tin hàng hóa:</span>
                     <table class="table p-0 m-0 p-2 fs-6">
                        <tbody>
                           <tr>
                              <td>Thương hiệu:
                                 <select
                                    id="selectThuongHieu"
                                    class="form-select"
                                    aria-label="Default select example">
                                    <option
                                       selected
                                       value="Chọn thương hiệu">
                                       Chọn thương hiệu
                                    </option>
                                    ` +
         data.thuongHieus
            .map(function (item) {
               return `
                                       <option value="${item.tenThuongHieu}">${item.tenThuongHieu}</option>
                                       `;
            })
            .join('') +
         `
                                 </select>
                              </td>
                              <td>Xuất xứ:Việt Nam/ Trung Quốc</td>
                           </tr>
                           <tr>
                              <td>Nhà cung cấp:
                              <select
                                 id="selectNcc"
                                 class="form-select"
                                 aria-label="Default select example">
                                 <option
                                    selected
                                    value="Chọn nhà cung cấp">
                                    Chọn nhà cung cấp
                                 </option>
                                 ` +
         data.nhaCungCaps
            .map(function (item) {
               return `
                                    <option value="${item.tenNhaCungCap}">${item.tenNhaCungCap}</option>
                                    `;
            })
            .join('') +
         `
                              </select>
                              </td>
                              <td>Địa chỉ ncc: 11/47 NVB GV VN</td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
                  <div class="row">
                     <span class="fw-bolder p-0 m-0 p-2">Thông tin kỹ thuật:</span>
                     <table class="table p-0 m-0 p-2 fs-6">
                        <tbody>
                           <tr>
                              <td>CPU:
                                 <input id="inputcpu" type="text" class="form-control" value="${data.dienThoai.heDieuHanh}" placeholder="CPU">
                              </td>
                              <td>Màn hình:  
                                 <input id="inputmanHinh" type="text" class="form-control" value="${data.dienThoai.manHinh}" placeholder="CPU">
                              </td>
                           </tr>
                           <tr>
                              <td>Ram: 
                                 <input id="inputram" type="number" class="form-control" value="${data.dienThoai.ram}" placeholder="CPU">
                              </td>
                              <td>Rom: 
                                 <input id="inputrom" type="number" class="form-control" value="${data.dienThoai.rom}" placeholder="CPU">
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="modal-footer">
         <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Close
         </button>
         <button
            id="btnCapNhat"
            type="button"
            class="btn btn-primary">
            Cập nhật
         </button>
      </div>
      </div>
      </div>
   `,
   );
   $('#selectNcc').val(data.nhaCungCap.tenNhaCungCap);
   $('#selectThuongHieu').val(data.thuongHieu.tenThuongHieu);
   $('#modalUpdateProduct').modal('show');
   let btnclearimg = document.getElementById('btn-clear-img');
   btnclearimg.addEventListener('click', function () {
      $('#inputhinhAnh').val('');
   });

   let changeimg = document.getElementById('change-img');
   changeimg.addEventListener('click', function () {
      let src = $('#img-hinhAnh').attr('src');
      $('#inputhinhAnh').val(src);
   });

   let btnCapNhat = document.getElementById('btnCapNhat') == null ? null : document.getElementById('btnCapNhat');
   if (btnCapNhat != null) {
      btnCapNhat.addEventListener('click', function () {
         let tenThuongHieu = $('#selectThuongHieu').val();
         let tenNhaCungCap = $('#selectNcc').val();
         let tenSanPham = $('#inputtenSanPham').val();
         let hinhAnh = $('#inputhinhAnh').val();
         let ram = $('#inputram').val();
         let rom = $('#inputrom').val();
         let cpu = $('#inputcpu').val();
         let manHinh = $('#inputmanHinh').val();
         let donGiaNhap = $('#inputdonGiaNhap').val();

         // check rong
         if (tenThuongHieu == '' || tenNhaCungCap == '' || tenSanPham == '' || hinhAnh == '' || ram == '' || rom == '' || cpu == '' || manHinh == '' || donGiaNhap == '') {
            alert('Vui lòng nhập đầy đủ thông tin');
         } else {
            let req = {
               maSanPham: id_tenncc[0],
               tenSanPham: tenSanPham,
               hinhAnh: hinhAnh,
               ram: ram,
               rom: rom,
               cpu: cpu,
               manHinh: manHinh,
               tenThuongHieu: tenThuongHieu,
               tenNhaCungCap: tenNhaCungCap,
               donGiaNhap: donGiaNhap,
            };

            $.ajax({
               url: '/admin/sanpham/update',
               type: 'POST',
               data: {
                  maSanPham: id_tenncc[0],
                  tenSanPham: tenSanPham,
                  hinhAnh: hinhAnh,
                  ram: ram,
                  rom: rom,
                  cpu: cpu,
                  manHinh: manHinh,
                  tenThuongHieu: tenThuongHieu,
                  tenNhaCungCap: tenNhaCungCap,
                  donGiaNhap: donGiaNhap,
               },
               success: function (data) {
                  window.location.href = '/admin/sanpham/danh-sach-san-pham';
               },
            });
         }
      });
   }
}

// event tim kiem san pham
let inputsearch = document.getElementById('input-search');

inputsearch.addEventListener('keyup', function () {
   let val = inputsearch.value;
   $.ajax({
      url: '/admin/sanpham/search',
      type: 'POST',
      data: {
         val: val,
      },
      success: function (data) {
         genarateTable(data);
      },
   });
});

function genarateTable(data) {
   let html = '';
   data.forEach(function (item) {
      html += `
      <tr>
         <td>${item.maSanPham}</td>
         <td>${item.loaiSanPham.tenLoaiSanPham}</td>
         <td>${item.thuongHieu.tenThuongHieu}</td>
         <td>${item.tenSanPham}</td>
         <td>${item.chiTietNhapHangs[0].nhaCungCap.tenNhaCungCap}</td>
         <td
            class="text-center"
           >${item.chiTietNhapHangs[0].donGiaNhap}</td>
         <td
            class="text-center"
           >${item.chiTietNhapHangs[0].donGiaNhap + item.chiTietNhapHangs[0].chiPhiLuuKho + item.chiTietNhapHangs[0].chiPhiQuanLy + item.chiTietNhapHangs[0].donGiaNhap * item.chiTietNhapHangs[0].phanTramLoiNhuan}</td>
         <!-- <td
            value="${item.maSanPham}+','+${item.chiTietNhapHangs[0].nhaCungCap.tenNhaCungCap}"
            id="btn-view-product"
            style="cursor: pointer"
            class="text-primary p-2 action-table">
            <i class="p-1 fa-solid fa-eye"></i>
         </td> -->
         <td
            id="btn-delete-product"
            style="cursor: pointer"
            value="${item.maSanPham}"
            class="text-danger p-2 action-table">
            <i class="p-1 fa-solid fa-trash"></i>
         </td>
         <td
            id="btn-update-product"
            value="${item.maSanPham}+','+${item.chiTietNhapHangs[0].nhaCungCap.tenNhaCungCap}"
            style="cursor: pointer"
            class="text-warning p-2 action-table">
            <i class="p-1 fa-solid fa-pen"></i>
         </td>
      </tr>
      `;
   });
   $('#tableBody').html(html);
}
