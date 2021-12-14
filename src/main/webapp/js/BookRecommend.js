window.onload = function () {

    n = 8
    n2 = 5
    index = 0;
    keyword = null
    gArr = ["#g0", "#g1", "#g2", "#g3", "#g4", "#g5", "#g6", "#g7"];
    bArr = ["#b0", "#b1", "#b2", "#b3", "#b4", "#b5", "#b6", "#b7"];
    searchButton = document.getElementById("search");
    keywordInput = document.getElementById("keyword");
    leftButton = document.getElementsByClassName("leftButton")[0]
    rightButton = document.getElementsByClassName("rightButton")[0]
    bigImgs = document.getElementsByClassName("bigImg")
    smallImgs = document.getElementsByClassName("smallImg")
    buttons = document.getElementsByClassName("little")
    bookName = document.getElementById("bookName")
    bookList = null
    recommendBookList = null
    //这里必须同步，因为要初始化bookList
    requestData(null, "InitSLT?limit=" + n, showBooks, false,)
    requestData(null, "RecommendSLT?code=" + bigImgs[0].code, showRecommendBooks, false)
    change(index);


    searchButton.onclick = function () {
        keyword = keywordInput.value
        if (keyword.trim().length > 0) {
            var href = "SearchSLT?keyword=" + keyword;
            requestData(null, href, showBooks, false)
        }
    }


    //获取后台数据
    function requestData(data, href, after, async = true, method = "post", dataType = "json") {
        $.ajax({
            url: `http://localhost:8080/BookRecommend/${href}`,
            type: method,
            dataType: dataType,
            data: JSON.stringify(data),
            async: async,
            success: function (result) {
                after(result);//我们仅做数据展示
            },
            error: function (msg) {
                alert("ajax连接异常：" + msg);
            }
        });
    }

    function showBooks(jsonData) {
        if (bookList == null)
            bookList = jsonData
        var len
        if (n > jsonData.length)
            len = jsonData.length
        else
            len = n
        for (let i = 0; i < len; i++) {
            var book = jsonData[i]
            bigImgs[i].src = book.imageURL
            bookList[i] = book
        }
        index = 0
        change(index)
    }

    function showRecommendBooks(jsonData) {
        recommendBookList = jsonData
        var len = n2 > recommendBookList.length ? recommendBookList.length : n2;
        for (let i = 0; i < len; i++) {
            var book = jsonData[i]
            smallImgs[i].src = book.imageURL
        }
    }

    keywordInput.onkeydown = function (event) {
        if (event.keyCode === 13) {
            searchButton.click()
        }
    }

    leftButton.onclick = function changeLeft() {
        index--;
        change(index);
    }

    rightButton.onclick = function changeRight() {
        index++;
        change(index);
    }

    timer = null
    dbclick = true

    for (let i = 0; i < n; i++) {
        bigImgs[i].onclick = function () {
            dbclick = !dbclick
            timer = window.setTimeout(function () {
                index = i
                change(index)
                if (dbclick) {
                    code = bookList[i].code
                    var href = "/RecommendSLT?code=" + code
                    requestData(code, href, showRecommendBooks)
                }
                window.clearTimeout(timer)
                dbclick = true
            }, 200)
        }

        buttons[i].onclick = function () {
            index = i
            change(index)
        }
    }

    /*业务执行*/
    function change() {
        if (index === n) {
            index = 0;
        } else if (index === -1) {
            index = n - 1;
        }
        changeButton();
        changeImg();
        changeTitle()
    }

    function changeTitle() {
        var title = bookList[index].title
        if (title.length > 80)
            title = title.substr(0, 90) + "......"
        bookName.innerText = title
    }


    /*切换按钮*/
    function changeButton() {
        $(".little").removeClass("blue");
        var bStyle = bArr[index];
        $(bStyle).addClass("blue");
    }

    /*切换图片*/
    function changeImg() {
        // 首先全置为hide
        $("li").removeClass().addClass("hidden");
        // 确定左中右
        var center = gArr[index];
        var right = gArr[(index + 1) % n];
        var right2 = gArr[(index + 2) % n];
        var left = gArr[(index + n - 1) % n];
        var left2 = gArr[(index + n - 2) % n];
        // 更改对应左中右
        $(center).removeClass().addClass("center");
        $(left).removeClass().addClass("left");
        $(right).removeClass().addClass("right");
        $(left2).removeClass().addClass("left2");
        $(right2).removeClass().addClass("right2");
    }
}
