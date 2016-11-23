function reload(url) {
	window.location.href = url;
}
$(document).ready(function() {
	// $("#m1").children("p").css("color", "#BDBDBD");
	var url = window.location.href;
	if (url.indexOf("cpbd") != -1) {
		$("#navtable tr:eq(0) td:eq(0)").css({
			background : "#614A14"
		})
	} else if (url.indexOf("gsgk") != -1) {
		$("#navtable tr:eq(0) td:eq(1)").css({
			background : "#614A14"
		})
	} else if (url.indexOf("cwfx") != -1) {
		$("#navtable tr:eq(0) td:eq(2)").css({
			background : "#614A14"
		})
	} else if (url.indexOf("gdyj") != -1) {
		$("#navtable tr:eq(0) td:eq(3)").css({
			background : "#614A14"
		})
	}

	// 导航固定到顶部
	// var elm = $('#quickNavigation');
	// var startPos = $(elm).offset().top;
	// $.event.add(window, "scroll", function() {
	// var p = $(window).scrollTop();
	// $(elm).css('position', ((p) > startPos) ? 'fixed' : 'static');
	// $(elm).css('top', ((p) > startPos) ? '0px' : '');
	// });
});
var content1 = false;
function show1() {
	if (content1) {
		$("#content1").show();
		$("#button1").removeClass("glyphicon-circle-arrow-right");
		$("#button1").addClass("glyphicon-circle-arrow-down");
		content1 = !content1;
	} else {
		$("#content1").hide();
		$("#button1").removeClass("glyphicon-circle-arrow-down");
		$("#button1").addClass("glyphicon-circle-arrow-right");
		content1 = !content1;
	}
}
var content2 = false;
function show2() {
	if (content2) {
		$("#content2").show();
		$("#button2").removeClass("glyphicon-circle-arrow-right");
		$("#button2").addClass("glyphicon-circle-arrow-down");
		content2 = !content2;
	} else {
		$("#content2").hide();
		$("#button2").removeClass("glyphicon-circle-arrow-down");
		$("#button2").addClass("glyphicon-circle-arrow-right");
		content2 = !content2;
	}
}
var content3 = false;
function show3() {
	if (content3) {
		$("#content3").show();
		$("#button3").removeClass("glyphicon-circle-arrow-right");
		$("#button3").addClass("glyphicon-circle-arrow-down");
		content3 = !content3;
	} else {
		$("#content3").hide();
		$("#button3").removeClass("glyphicon-circle-arrow-down");
		$("#button3").addClass("glyphicon-circle-arrow-right");
		content3 = !content3;
	}
}
var content4 = false;
function show4() {
	if (content4) {
		$("#content4").show();
		$("#button4").removeClass("glyphicon-circle-arrow-right");
		$("#button4").addClass("glyphicon-circle-arrow-down");
		content4 = !content4;
	} else {
		$("#content4").hide();
		$("#button4").removeClass("glyphicon-circle-arrow-down");
		$("#button4").addClass("glyphicon-circle-arrow-right");
		content4 = !content4;
	}
}
var content5 = false;
function show5() {
	if (content5) {
		$("#content5").show();
		$("#button5").removeClass("glyphicon-circle-arrow-right");
		$("#button5").addClass("glyphicon-circle-arrow-down");
		content5 = !content5;
	} else {
		$("#content5").hide();
		$("#button5").removeClass("glyphicon-circle-arrow-down");
		$("#button5").addClass("glyphicon-circle-arrow-right");
		content5 = !content5;
	}
}
// 滚动到顶部
function scrollHref(key, obj) {
	//console.log(obj);
	//$(obj).css({background : "#614A14"});
	var ntop = $('#content').offset().top;
	//console.log(ntop);
	var ntop1 = $('#' + key).offset().top - 75;// 65固定导航的高度
	//console.log(ntop1);
	$('html, body').animate({
		scrollTop : ntop1
	}, 1000);
}

function backtoMain() {
	$("detail").html("");
	$("#detail").hide();
	if ($("#dragonDetail")) {
		$("#dragonDetail").hide();
	}
	$("#goback_li").show();
	$("#content").show();
	$("#jump").show();
}