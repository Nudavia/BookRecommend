var gArr = ["#g0", "#g1", "#g2", "#g3", "#g4", "#g5","#g6","#g7"];
var bArr = ["#b0", "#b1", "#b2", "#b3", "#b4", "#b5","#b6","#b7"];
var index = 0;
var n = 8

$(function () {
    change(index);
});

/*点击按钮*/
$(".little").click(function () {
    index = $(this).index();
    change(index);
});

/*业务执行*/
function change(val) {
    if (val === n) {
        index = 0;
    } else if (val === -1) {
        index = n - 1;
    }
    changeButton();
    changeImg();
}

/*向右切换*/
function changeRight() {
    index++;
    change(index);
}

/*向左切换*/
function changeLeft() {
    index--;
    change(index);
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

