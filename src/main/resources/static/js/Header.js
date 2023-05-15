$(document).ready(function () {
   let ipeditinfors = document.querySelectorAll('.ip-edit-infor');
   ipeditinfors.forEach((e) => {
      e.style.display = 'none';
   });

   // let faeditinfors = document.querySelectorAll('.fa-edit-infor');
   // faeditinfors.forEach(e => {
   //     e.addEventListener('click', (ee) => {
   //         ee.preventDefault();
   //         let id = e.getAttribute('id');
   //         let ip = document.querySelector('#ip-' + id);
   //         let fa = document.querySelector('#fa-' + id);
   //         if (ip.style.display === 'none') {
   //             ip.style.display = 'block';
   //             fa.classList.remove('fa-edit');
   //             fa.classList.add('fa-check');
   //         } else {
   //             ip.style.display = 'none';
   //             fa.classList.remove('fa-check');
   //             fa.classList.add('fa-edit');
   //         }
   //     });
   // });
});

let navs = document.querySelectorAll('.nav-menu');
navs.forEach((e) => {
   e.addEventListener('mouseover', (ee) => {
      ee.preventDefault();
      $('.hover-dienthoai').show();
      $('.hover-dienthoai').addClass('animation');
   });
});

let fatherHoverdt = document.querySelector('.hover-dienthoai');
fatherHoverdt.addEventListener('mouseleave', (ee) => {
   ee.preventDefault();
   $('.hover-dienthoai').hide();
});

let navbar = document.querySelector('.navbar');
navbar.addEventListener('mouseover', (ee) => {
   ee.preventDefault();
   $('.hover-dienthoai').hide();
});

let content = document.querySelector('.content');
content.addEventListener('mouseover', (ee) => {
   ee.preventDefault();
   $('.hover-dienthoai').hide();
});

// let dropdownMenuButton = document.getElementById('dropdownMenuButton');
// dropdownMenuButton.addEventListener('click', (ee) => {
//    ee.preventDefault();
//    $('.dropdown-menu').show();
// });

$('.dropdown-menu').mouseleave(function () {
   $('.dropdown-menu').fadeOut('slow');
});

// let slide = document.querySelector('.slide');
// slide.addEventListener('mouseover', (ee) => {
//     ee.preventDefault();
//     $(".hover-dienthoai").hide();
// });

// function fillterNavHoverHangDT(value){
//     brands.add(value);
//     ShowClickIconPhone();
//     AjaxFillterBrandPhone();
// }

let searchVal = document.getElementById('searchVal');
searchVal.addEventListener('keyup', (e) => {
   $.ajax({
      url: '/search',
      type: 'GET',
      data: {
         searchVal: e.target.value,
      },
      success: function (data) {
         // window.location.href = '/search?searchVal=' + e.target.value;
      },
   });
});

// key enter search
let searchValEnter = document.getElementById('searchVal');
searchValEnter.addEventListener('keyup', (e) => {
   if (e.keyCode === 13) {
      let searchVal = document.getElementById('searchVal').value;
      window.location.href = '/search?searchVal=' + searchVal;
   }
});
