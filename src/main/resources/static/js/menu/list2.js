// WARNING: For GET requests, body is set to null by browsers.

// xhr.addEventListener("readystatechange", function() {
//   if(this.readyState === 4) {
//     var list = JSON.parse(this.responseText);
//     alert(list[0].korName);
//   }
// });

function Cookie(){
    this.map = {};

    var cookieDecoded = decodeURIComponent(document.cookie);
    var cookieTokens = cookieDecoded.split(";");

    for(c of cookieTokens){
        var temp = c.split("=");
        var key = temp[0];
        var value = JSON.parse(temp[1]);

        this.map[key] = value;
    }

    console.log(this.map);
}

Cookie.prototype = {
    get: function(name){
        return this.map[name];
    },
    save: function(){

        var list = this.map["menus"];
        var size = list.length;
        var lastIndex = size - 1;

        var str = "[";
        for(var a of this.map["menus"]){
            str += JSON.stringify(a);
            if(a !== list[lastIndex])
                str += ",";
        }
        str += "]";

        var encoded = encodeURIComponent(str);
        document.cookie = `menus=${encoded}; path=/;`;
    },
    remove(name){

    },
    add(name,value){

    },
    addItem(name,item){
        var list = this.map[name];
        list.push(item);
    },
    set(name,value){

    }
};

window.addEventListener("load",function(){

    var cookie = new Cookie();
    console.log(cookie.get("menus"));
    // var val = decodeURIComponent(document.cookie.split("=")[1]);
    // console.log(JSON.parse(val)[0].korName);


    var categoryFilter = this.document.querySelector(".category-filter");
    var li1 = categoryFilter.querySelector("ul>li:nth-child(2)");

    console.log(categoryFilter, li1);

    var queryForm = this.document.getElementById("query-form");
    var queryButton = queryForm.getElementsByClassName("icon-find")[0];
    var queryInput = queryForm.getElementsByClassName("query-input")[0];

    var menuCardList = this.document.getElementById("menu-card-list");
    var menuContent = menuCardList.getElementsByClassName("content")[0];

    // var cartButton = menuCardList.getElementsByTagName("button")[0];

    // cartButton.onclick = function(e) {

    //     e.preventDefault();

    //     alert("asd");
    // }

    menuContent.onclick = function(e) {

        if(!e.target.classList.contains("btn-cart"))
            return;

        var item = {};
        item.id = e.target.dataset.id;
        item.korName = e.target.dataset.korname;
        item.engName = e.target.dataset.engname;
        item.price = e.target.dataset.price;
        item.regDate = e.target.dataset.regdate;
        item.img = e.target.dataset.img;
        item.categoryId = e.target.dataset.categoryid;

        cookie.addItem("menus",item);

        // cookie.remove("menus");
        // cookie.add("new cookie name",/*value*/);
        // cookie.addItem(/*name*/"menus",/*item*/);
        // cookie.set("menus", ?);
        cookie.save();
        e.preventDefault();


        // alert("asd");

    }

    //event의 줄임말 e
    categoryFilter.onclick = function(e) {

        if(e.target.tagName != "A")
            return;

        e.preventDefault();

        //view에서 data 로 넘긴 데이터
        var categoryId = e.target.dataset.id;

        var url = `http://localhost:8080/api/menus?c=${categoryId}`;
        // var method = "GET";

        request(url, function(list){
            bind(list);
            console.log("캐태고리 목록 재로드");
        });
    }

    queryButton.onclick = function(e){

        e.preventDefault();

        var q = queryInput.value;

        var url = `http://localhost:8080/api/menus?q=${q}&p=1`;
        // var method = "GET";

        request(url, function(list){
            bind(list);
            console.log("검색어 목록 재로드");
        });
    }
    function request(url,callback,method){

        //method의 디폴트 값을 GET으로
        method = method || "GET";

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.onload = function() {
            var list = JSON.parse(this.responseText);
            // bind(list);
            callback(list);
            // alert(list[0].korName);
            // queryInput.value = list[0].korName;

            // var menu = list[0];
            // })
            // var h1Text = document.createTextNode(list[0].korName);
            // var menuSection = document.createElement("section");
            // menuSection.className = "menu-card";
            // var h1 = document.createElement("h1");
            // h1.appendChild(h1Text);
            // menuSection.appendChild(h1);
            // menuContent.appendChild(menuSection);
        }

        var q = queryInput.value;

        // xhr.open("GET", "http://localhost:8080/api/menus?p=2");
        xhr.open(method, url);

        xhr.send();
    }

    function bind(list){

        menuContent.classList.add("fade-out");
        // setTimeout(function(){
        menuContent.ontransitionend = function(){
            menuContent.ontransitionend = null;
            //내용 다 지우는 꼼수
            menuContent.classList.remove("fade-out");
            menuContent.innerHTML="";
            // forEach문과 for of 문
            for(var menu of list){


                // list.forEach(menu => {

                var sectionHTML =
                    `<section class="menu-card">
                    <h1>
                        <a class="heading-3" href="detail.html">${menu.korName}</a></h1>
                    <h2 class="heading-2 font-weight:normal color:base-5">Cafe Latte</h2>
                    <div class="price-block d:flex align-items:flex-end"><span class="font-weight:bold">${menu.price}</span>원<span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
                    <div class="img-block">
                        <a href="detail.html?id=1"><img src="/image/menu/8.jpg"></a>
                    </div>
                    <div class="like-block d:flex justify-content:flex-end">
                        <a class="icon icon-heart-fill icon-color:base-4" href="">좋아요</a>
                        <span class="font-weight:bold ml:1">2</span>
                    </div>
                    <div class="pay-block d:flex">
                        <!-- <a class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text" href="">담기</a> -->
                        <form action="/cart/add-menu" method="post">
                            <input type="hidden" name="id">
                            <button class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text">담기</button>
                        </form>
                        <a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text" href="">주문하기</a>
                    </div>
                </section>`;

                menuContent.insertAdjacentHTML("beforeend", sectionHTML);
            }
            // }, 1000);
        }

    }

})

/// 콜백함수인데 언제 콜백하냐? load될때 콜백한다. onload
// xhr.onload = function() {
//     var queryInput = document.getElementById("query-input");
//     var list = JSON.parse(this.responseText);
//     // alert(list[0].korName);
//     queryInput.setAttribute("value",list[0].korName);
// }

// xhr.open("GET", "http://localhost:8080/api/menus?p=2");

// xhr.send();

// alert(xhr.responseText);

// alert("아이고~")