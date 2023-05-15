let card = document.querySelectorAll('.card');
card.forEach((item) => {
   item.addEventListener('mouseover', function () {
      item.classList.add('shadow', 'bg-body', 'rounded', 'animationhover');
      $(this).find('.img').addClass('hovercardimg');
      $(this).find('.img').removeClass('unhovercardimg');
   });
   item.addEventListener('mouseout', function () {
      item.classList.remove('shadow', 'bg-body', 'rounded', 'animationhover');
      $(this).find('.img').addClass('unhovercardimg');
      $(this).find('.img').removeClass('hovercardimg');
   });
});
let cardwrapper = document.querySelectorAll('.card-wrapper');
cardwrapper.forEach((item) => {
   item.addEventListener('click', function () {
      let name = item.getAttribute('value');
      name = name.replace(/\s/g, '-');
      console.log(name);
      let url = '/san-pham/dien-thoai/show?id=' + name;
      $.ajax({
         url: url,
         type: 'GET',
         success: function (data) {
            window.location.href = url;
         },
         error: function (data) {
            console.log(data);
         },
      });
   });
});
let btnview = document.querySelectorAll('.btn-view');
btnview.forEach(function (item) {
   item.addEventListener('click', function () {
      let name = item.getAttribute('value');
      // let url = '/san-pham/dien-thoai/show?id=' + name;
      // $.ajax({
      //    url: url,
      //    type: 'GET',
      //    success: function (data) {
      //       window.location.href = url;
      //    },
      //    error: function (data) {
      //       console.log(data);
      //    },
      // });
      alert(name);
   });
});

// filter
let cbitemhsx = document.querySelectorAll('.cb-item-hsx');
let cbhsxall = document.getElementById('cb-hsx-all');
let iconslide = document.querySelectorAll('.icon-slide');
let titemnavhoverhsx = document.querySelectorAll('.item-navhover-hsx');

// filler by brand
let brand = new Set();
cbitemhsx.forEach((item) => {
   item.addEventListener('click', (e) => {
      if (item.checked) {
         brand.add(item.getAttribute('data-brand'));
         cbhsxall.checked = false;
      } else {
         brand.delete(item.getAttribute('data-brand'));
      }
      checkBoxHsx();
      AjaxFilterPhone();
   });
});

iconslide.forEach((item) => {
   item.addEventListener('click', (e) => {
      brand.add(item.getAttribute('data-icon'));
      checkBoxHsx();
      AjaxFilterPhone();
   });
});
titemnavhoverhsx.forEach((item) => {
   item.addEventListener('click', (e) => {
      brand.add(item.getAttribute('value'));
      checkBoxHsx();
      $('.hover-dienthoai').hide();
      AjaxFilterPhone();
   });
});

function checkBoxHsx() {
   if (brand.size == 0) {
      cbhsxall.checked = true;
   } else {
      cbhsxall.checked = false;
      brand.forEach((item) => {
         cbitemhsx.forEach((item1) => {
            if (item == item1.getAttribute('data-brand')) {
               item1.checked = true;
            }
         });
      });
   }
}

cbhsxall.addEventListener('click', () => {
   if (cbhsxall.checked) {
      cbitemhsx.forEach((item) => {
         item.checked = false;
      });
      brand.clear();
   } else {
      cbitemhsx.forEach((item) => {
         item.checked = true;
         brand.add(item.getAttribute('data-brand'));
      });
   }
   AjaxFilterPhone();
});

// filler by price
let cbitemprice = document.querySelectorAll('.cb-item-price');
let itemnavhoverprice = document.querySelectorAll('.item-navhover-price');
let cbpriceall = document.getElementById('cb-price-all');

let price = new Set();
itemnavhoverprice.forEach((item) => {
   item.addEventListener('click', (e) => {
      $('.hover-dienthoai').hide();
      price.add(item.getAttribute('data-price'));
      checkBoxPrice();
      AjaxFilterPhone();
   });
});

cbitemprice.forEach((item) => {
   item.addEventListener('click', (e) => {
      if (item.checked) {
         price.add(item.getAttribute('data-price'));
         cbpriceall.checked = false;
      } else {
         price.delete(item.getAttribute('data-price'));
      }
      checkBoxPrice();
      AjaxFilterPhone();
   });
});
function checkBoxPrice() {
   if (price.size == 0) {
      cbpriceall.checked = true;
   } else {
      cbpriceall.checked = false;
      price.forEach((item) => {
         cbitemprice.forEach((item1) => {
            if (item == item1.getAttribute('data-price')) {
               item1.checked = true;
            }
         });
      });
   }
}

cbpriceall.addEventListener('click', () => {
   if (cbpriceall.checked) {
      cbitemprice.forEach((item) => {
         item.checked = false;
      });
      price.clear();
   } else {
      cbitemprice.forEach((item) => {
         item.checked = true;
         price.add(item.getAttribute('data-price'));
      });
   }
   AjaxFilterPhone();
});

function maxPriceFilter() {
   // tim max price tư set price
   let max = 0;
   price.forEach((item) => {
      let split = item.split('-');
      item = parseInt(split[1]);
      if (item > max) {
         max = item;
      }
   });
   return max;
}

function minPriceFilter() {
   // tim min price tư set price
   let min = 1000000000;
   price.forEach((item) => {
      let split = item.split('-');
      item = parseInt(split[0]);
      if (item < min) {
         min = item;
      }
   });
   return min;
}

// ajax filter
function AjaxFilterPhone() {
   let url = '/san-pham/fillter?brandAscii=dienthoai';

   if (brand.size == 0) {
      url += '&brand=all';
   } else {
      url += '&brand=';
      brand.forEach((item) => {
         url += item + ',';
      });
      url = url.substring(0, url.length - 1);
   }

   $.ajax({
      url: url,
      type: 'POST',
      contentType: 'application/json; charset=utf-8',
      success: function (data) {
         window.history.pushState('', '', url);
         console.log(data);
         GenerateHtmlPhone(data);
      },
   });
}
function GenerateHtmlPhone(data) {
   let html = '';
   $('.product').html(html);
   if (data.length == 0) {
      html += `<h1>Không còn sản phẩm này</h1>`;
   } else {
      data.forEach(function (item) {
         const money = item.price;
         const config = { style: 'currency', currency: 'VND', maximumFractionDigits: 9 };
         const formated = new Intl.NumberFormat('vi-VN', config).format(money);
         html += `
         <div
               class="card m-0 animation-card"
               style="max-width: 17rem; cursor: pointer; border: none">
               <div
                  class="card-wrapper m-0"
                  value="${item.maSanPham}">
                  <img
                     src="${item.hinhAnh.url}"
                     style="max-width: 80%"
                     class="rounded mx-auto d-block mt-2 img"
                     alt="..." />
                  <div class="card-body">
                     <h5
                        class="card-title fw-bold lh-1 font-monospace text-break"
                        style="font-size: 1.2vw; color: #474c51">${item.tenSanPham}</h5>
                     <h5
                        class="card-title rounded-pill p-1 price"
                        style="max-width: 20vh; font-size: 1.2vw; color: rgba(255, 255, 255, 0.925); background-color: #cb1c22; text-align: center">
                        ${item.chiTietNhapHangs[0].donGiaNhap + item.chiTietNhapHangs[0].chiPhiLuuKho + item.chiTietNhapHangs[0].chiPhiQuanLy + item.chiTietNhapHangs[0].donGiaNhap * item.chiTietNhapHangs[0].phanTramLoiNhuan}
                        </h5>
                     <div
                        class="row shadow-none p-3 mb-5 bg-light rounded"
                        style="margin: 0px 0px !important; padding: 1rem 0.5rem !important">
                        <div class="row pb-2">
                           <i
                              style="color: #6c757d; cursor: help; font-size: 1vw"
                              class="fas fa-microchip">
                              <i
                                 class="fw-normal fst-normal lh-1"
                                 >${item.dienThoai.heDieuHanh}</i>
                           </i>
                        </div>
                        <div class="row pb-2">
                           <i
                              style="color: #6c757d; cursor: help; font-size: 1vw"
                              class="fas fa-memory">
                              <i
                                 class="fw-normal fst-normal lh-1"
                                >${item.dienThoai.ram}</i>
                           </i>
                        </div>
                        <div class="row pb-2">
                           <i
                              style="color: #6c757d; cursor: help; font-size: 1vw"
                              class="fas fa-mobile-alt">
                              <i
                                 class="fw-normal fst-normal lh-1"
                                 >${item.dienThoai.manHinh}</i>
                           </i>
                        </div>
                        <div class="row pb-2">
                           <i
                              style="color: #6c757d; cursor: help; font-size: 1vw"
                              class="fas fa-hdd">
                              <i
                                 class="fw-normal fst-normal lh-1"
                                 >${item.dienThoai.rom}</i>
                           </i>
                        </div>
                     </div>
                  </div>
                  <div class="d-flex justify-content-center p-0 m-0">
                     <div class="col-6 align-items-center text-center justify-content-center">
                        <button
                           style="font-size: 1vw"
                           type="button"
                           class="btn btn-danger">
                           Mua ngay
                        </button>
                     </div>
                     <div class="col-6">
                        <span
                           class="d-inline-block"
                           tabindex="0"
                           data-bs-toggle="popover"
                           data-bs-trigger="hover focus"
                           data-bs-content="Xem thông tin sản phẩm tại đây">
                           <button
                              value="${item.maSanPham}"
                              style="font-size: 1vw"
                              type="button"
                              class="btn btn-dark btn-view">
                              Xem chi tiết
                           </button>
                        </span>
                     </div>
                  </div>
               </div>
            </div>
			 `;
      });
   }
   $('.product').html(html);
   let btnview = document.querySelectorAll('.btn-view');
   btnview.forEach(function (item) {
      item.addEventListener('click', function () {
         let name = item.getAttribute('value');
         let url = '/san-pham/dien-thoai/show?id=' + name;
         $.ajax({
            url: url,
            type: 'GET',
            success: function (data) {
               window.location.href = url;
            },
            error: function (data) {
               console.log(data);
            },
         });
      });
   });
}
