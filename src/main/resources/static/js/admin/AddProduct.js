let jsonNcc = {
   tenNhaCungCap: 'NCC1',
   diaChi: 'Dia chi 1',
   email: '',
   dongia: '',
};
$('#selectNcc').change(function () {
   var idNcc = $(this).val();
   if (idNcc == 'addNcc') {
      $('#modalNCC').modal('show');
   }
   if (idNcc !== 'Chọn nhà cung cấp' || idNcc !== 'addNcc') {
      $('#seeInfoNcc').css('display', 'block');
   }
   if (idNcc == 'Chọn nhà cung cấp' || idNcc == 'addNcc') {
      $('#seeInfoNcc').css('display', 'none');
   }
});
// event xem thong tin nha cung cap
$('#seeInfoNcc').click(function () {
   let nameNcc = $('#selectNcc').val();
   $.ajax({
      url: '/admin/sanpham/nhacungcap?nameNcc=' + nameNcc,
      type: 'POST',
      success: function (data) {
         if (data.message == 'null') {
            createModalInforNCC(jsonNcc);
         } else {
            createModalInforNCC(data);
         }
      },
   });
});

function createModalInforNCC(data) {
   $('#modalInforNCC').html(`
      <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">Thông tin nhà cung cấp</h5>
            <button
               type="button"
               class="btn-close"
               data-bs-dismiss="modal"
               aria-label="Close"></button>
         </div>
         <div class="modal-body">
            <div class="container justify-content-center m-0">
               <div class="row mt-2">
                  <div class="col-6">
                     <span>Tên nhà cung cấp:</span>
                  </div>
                  <div class="col-6">
                     <input
                        disabled
                        value="${data.tenNhaCungCap}"
                        id="inputTenNCC"
                        placeholder="Tên nhà cung cấp"
                        class="p-1 border-0 bg-light"
                        type="text" />
                  </div>
               </div>
               <div class="row mt-2">
                  <div class="col-6">
                     <span>Địa chỉ:</span>
                  </div>
                  <div class="col-6">
                     <input
                        disabled
                        value="${data.diaChi}"
                        id="inputDiaChiNCC"
                        placeholder="Địa chỉ"
                        class="p-1 border-0 bg-light"
                        type="text" />
                  </div>
               </div>
               <div class="row mt-2">
                  <div class="col-6">
                     <span>Email liên hệ:</span>
                  </div>
                  <div class="col-6">
                     <input
                        disabled
                        value="${data.email}"
                        id="inputEmailNCC"
                        placeholder="Email liên hệ"
                        class="p-1 border-0 bg-light"
                        type="text" />
                  </div>
               </div>
               <div class="row mt-2">
                  <div class="col-6">
                     <span>Đơn giá nhập:</span>
                  </div>
                  <div class="col-6">
                     <input
                        disabled
                        value="${data.dongia}"
                        id="inputDonGiaNhapNCC"
                        placeholder="Email liên hệ"
                        class="p-1 border-0 bg-light"
                        type="text" />
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
         </div>
      </div>
      </div>`);
   $('#modalInforNCC').modal('show');
}

function closeModalNCC() {
   $('#modalNCC').modal('hide');
   $('#selectNcc').val('Chọn nhà cung cấp');
}

$('#selectThuongHieu').change(function () {
   var idNcc = $(this).val();
   if (idNcc == 'addThuongHieu') {
      $('#modalThuongHieu').modal('show');
   }
});

function closeModalThuongHieu() {
   $('#modalThuongHieu').modal('hide');
   $('#selectThuongHieu').val(0);
}

$('#selectHangMuc').change(function () {
   var idNcc = $(this).val();
   if (idNcc == 'addHangMuc') {
      $('#modalHangMuc').modal('show');
   }
});

function closeModalHangMuc() {
   $('#modalHangMuc').modal('hide');
   $('#selectHangMuc').val(0);
}

// event save
// save ncc
let btnSaveNCC = document.getElementById('btnSaveNCC');
btnSaveNCC.addEventListener('click', function () {
   // lay gia tri input
   let tenNCC = document.getElementById('inputTenNCC').value;
   let diaChiNCC = document.getElementById('inputDiaChiNCC').value;
   let emailNCC = document.getElementById('inputEmailNCC').value;
   let donGiaNhapNCC = document.getElementById('inputDonGiaNhapNCC').value;
   // lay text button
   let textBtn = document.getElementById('btnSaveNCC').innerText;
   if (textBtn == 'Save changes') {
      if (tenNCC == '' || diaChiNCC == '' || emailNCC == '') {
         alert('Vui lòng nhập đầy đủ thông tin');
         return;
      } else {
         // them vao JSON
         jsonNcc.tenNhaCungCap = tenNCC;
         jsonNcc.diaChi = diaChiNCC;
         jsonNcc.email = emailNCC;
         jsonNcc.dongia = donGiaNhapNCC;

         let elementOption = document.createElement('option');
         elementOption.value = tenNCC;
         elementOption.innerText = tenNCC;
         // them vao truoc option add
         document.getElementById('selectNcc').insertBefore(elementOption, document.getElementById('selectNcc').childNodes[0]);
         $('#modalNCC').modal('hide');
         $('#selectNcc').val(tenNCC);
         document.getElementById('btnSaveNCC').innerText = 'Cập nhật';
      }
   } else if (textBtn == 'Cập nhật') {
      // cap nhat lai value cua select luc them moi
      let option = document.getElementById('selectNcc').childNodes[0];
      console.log(option);
      // cap nhat lai gia tri JSON
      jsonNcc.tenNhaCungCap = tenNCC;
      option.value = tenNCC;
      option.innerText = tenNCC;
      $('#modalNCC').modal('hide');
      $('#selectNcc').val(tenNCC);
   }
   let nameNcc = $('#selectNcc').val();
   if (nameNcc != 'Chọn nhà cung cấp' || nameNcc != 'addNcc') {
      $('#seeInfoNcc').css('display', 'block');
   }
});

// save thuong hieu
let btnSaveThuongHieu = document.getElementById('btnSaveThuongHieu');
btnSaveThuongHieu.addEventListener('click', function () {
   // lay gia tri input
   let tenThuongHieu = document.getElementById('inputTenThuongHieu').value;
   // lay text button
   let textBtn = document.getElementById('btnSaveThuongHieu').innerText;
   if (textBtn == 'Save changes') {
      if (tenThuongHieu == '') {
         alert('Vui lòng nhập đầy đủ thông tin');
         return;
      } else {
         let elementOption = document.createElement('option');
         elementOption.value = tenThuongHieu;
         elementOption.innerText = tenThuongHieu;
         // them vao truoc option add
         document.getElementById('selectThuongHieu').insertBefore(elementOption, document.getElementById('selectThuongHieu').childNodes[0]);
         $('#modalThuongHieu').modal('hide');
         $('#selectThuongHieu').val(tenThuongHieu);

         document.getElementById('btnSaveThuongHieu').innerText = 'Cập nhật';
      }
   } else if (textBtn == 'Cập nhật') {
      // cap nhat lai value cua select luc them moi
      let option = document.getElementById('selectThuongHieu').childNodes[0];
      console.log(option);
      // cap nhat lai gia tri jsNCC
      option.value = tenThuongHieu;
      option.innerText = tenThuongHieu;
      $('#modalThuongHieu').modal('hide');
      $('#selectThuongHieu').val(tenThuongHieu);
   }
});

// save hang muc
let btnSaveHangMuc = document.getElementById('btnSaveHangMuc');
btnSaveHangMuc.addEventListener('click', function () {
   // lay gia tri input
   let tenHangMuc = document.getElementById('inputTenHangMuc').value;
   // lay text button
   let textBtn = document.getElementById('btnSaveHangMuc').innerText;
   if (textBtn == 'Save changes') {
      if (tenHangMuc == '') {
         alert('Vui lòng nhập đầy đủ thông tin');
         return;
      } else {
         let elementOption = document.createElement('option');
         elementOption.value = tenHangMuc;
         elementOption.innerText = tenHangMuc;
         // them vao truoc option add
         document.getElementById('selectHangMuc').insertBefore(elementOption, document.getElementById('selectHangMuc').childNodes[0]);
         $('#modalHangMuc').modal('hide');
         $('#selectHangMuc').val(tenHangMuc);

         document.getElementById('btnSaveHangMuc').innerText = 'Cập nhật';
      }
   } else if (textBtn == 'Cập nhật') {
      // cap nhat lai value cua select luc them moi
      let option = document.getElementById('selectHangMuc').childNodes[0];
      console.log(option);
      // cap nhat lai gia tri jsNCC
      option.value = tenHangMuc;
      option.innerText = tenHangMuc;
      $('#modalHangMuc').modal('hide');
      $('#selectHangMuc').val(tenHangMuc);
   }
});

// save san pham

let btnSaveSanPham = document.getElementById('btnSaveSanPham');
btnSaveSanPham.addEventListener('click', function () {
   let tenSanPham = document.getElementById('inputTenSanPham').value;
   let heDieuHanh = document.getElementById('inputHeDieuHanh').value;
   let hinhAnh = document.getElementById('inputHinhAnh').value;
   let manHinh = document.getElementById('inputManHinh').value;
   let ram = document.getElementById('inputRam').value;
   let boNhoTrong = document.getElementById('inputRom').value;
   let tenNCC = document.getElementById('selectNcc').value;
   let tenThuongHieu = document.getElementById('selectThuongHieu').value;
   let tenHangMuc = document.getElementById('selectHangMuc').value;
   let diaChiNCC = document.getElementById('inputDiaChiNCC').value;
   let emailNCC = document.getElementById('inputEmailNCC').value;
   let inputDonGiaNhapNCC = document.getElementById('inputDonGiaNhapNCC').value == '' ? 0 : document.getElementById('inputDonGiaNhapNCC').value;

   if (tenSanPham == '' || heDieuHanh == '' || hinhAnh == '' || tenNCC == '' || tenThuongHieu == '' || tenHangMuc == '') {
      alert('Vui lòng nhập đầy đủ thông tin');
      return;
   } else {
      let req = {
         tenSanPham: tenSanPham,
         heDieuHanh: heDieuHanh,
         hinhAnh: hinhAnh,
         manHinh: manHinh,
         ram: ram,
         rom: boNhoTrong,
         tenNCC: tenNCC,
         tenThuongHieu: tenThuongHieu,
         tenHangMuc: tenHangMuc,
         diaChiNCC: diaChiNCC,
         emailNCC: emailNCC,
         donGiaNhapNCC: inputDonGiaNhapNCC,
      };
      let myJSON = JSON.stringify(req);
      $.ajax({
         url: '/admin/sanpham/them-san-pham?action=addSanPham',
         type: 'POST',
         contentType: 'application/json',
         data: myJSON,
         success: function (data) {
            window.location.href = '/admin/sanpham/danh-sach-san-pham';
         },
      });
   }
});

// push data vao template
let tenSanPham = document.getElementById('inputTenSanPham');
let heDieuHanh = document.getElementById('inputHeDieuHanh');
let hinhAnh = document.getElementById('inputHinhAnh');
let manHinh = document.getElementById('inputManHinh');
let ram = document.getElementById('inputRam');
let boNhoTrong = document.getElementById('inputRom');

let cardname = document.getElementById('card-name');
let cardcpu = document.getElementById('card-cpu');
let cardscreen = document.getElementById('card-screen');
let cardram = document.getElementById('card-ram');
let cardrom = document.getElementById('card-rom');
let cardimg = document.getElementById('card-img');

tenSanPham.addEventListener('keyup', function () {
   console.log(tenSanPham.value);
   cardname.innerText = tenSanPham.value;
});

heDieuHanh.addEventListener('keyup', function () {
   cardcpu.innerText = heDieuHanh.value;
});

manHinh.addEventListener('keyup', function () {
   cardscreen.innerText = manHinh.value + ' inch';
});

ram.addEventListener('change', function () {
   cardram.innerText = ram.value + ' GB';
});

boNhoTrong.addEventListener('change', function () {
   cardrom.innerText = boNhoTrong.value + ' GB';
});

hinhAnh.addEventListener('blur', function () {
   cardimg.src = hinhAnh.value;
});
