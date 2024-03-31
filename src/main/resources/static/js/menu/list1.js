// alert("안녕하세요 마인유어스 마씸");
// console.log("js script");
var categoryFilter = document.querySelector(".category-filter");
var li1 = categoryFilter.querySelector("ul>li:nth-child(2)");

var queryForm = document.getElementById("query-form");
var queryButton = queryForm.getElementsByClassName("icon-find")[0];

var queryInput = queryForm.getElementsByClassName("query-input")[0];
var menuCardList = document.getElementById("menu-card-list");
var menuContent = menuCardList.getElementsByClassName("content")[0];


categoryFilter.onclick = function (e){
    if (e.target.tagName !== "A")
        return;
    let xhr = extracted(e);

    var q = queryInput.value;

    xhr.open("GET", `http://localhost:8080/api/menus?c=${e.target.dataset.id}&p=1`);
    xhr.send();
};




queryButton.onclick = function (e) {

    let xhr = extracted(e);

    var q = queryInput.value;
    xhr.open("GET", `http://localhost:8080/api/menus?q=${q}&p=1`);
    xhr.send();
};


function extracted(e) {
    e.preventDefault();
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.onload = function () {//로드 될 때 실행되는 녀석
        var list = JSON.parse(this.responseText);
        console.log(list)
        bind(list);
    };
    return xhr;
}

function bind(list){
    // var menu = list[0];
    menuContent.innerHTML = "";


    list.forEach(menu => {


        var sectionHtml =
            `
                <section class="menu-card" id="menu-card" >
                        <h1><a class="heading-3" href="detail.html">${menu.kor_NAME}</a></h1>
                        <h2 class="heading-2 font-weight:normal color:base-5" >Cafe Latte</h2>
                        <div class="price-block d:flex align-items:flex-end"><span class="font-weight:bold">${menu.price}</span>원<span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
                        <div class="img-block">
                            <a href="detail.html?id=1"}"><img src="/image/menu/8.jpg"></a>
                        </div>
                        <div class="like-block d:flex justify-content:flex-end">
                            <a class="icon icon-heart-fill icon-color:base-4" href="">좋아요</a>
                            <span class="font-weight:bold ml:1" >2</span>
                        </div>
                        <div class="pay-block d:flex">
                            <form action="/cart/add-menu" method="post">
                                <input type="hidden" name="id">
                                <button class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text">
                                    담기입니다
                                </button>
                            </form>
                            <a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text"
                               href="">주문하기</a>
                        </div>
                    </section>
                `;

        menuContent.insertAdjacentHTML("beforeend", sectionHtml);
    });
}