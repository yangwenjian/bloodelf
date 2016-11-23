
// 公司公告 明细
function announceDetail(discId) {
	var urlPath = basePath + '/f10/stock/announce.html?discId=' + discId;
	_detail(urlPath);
}
// 媒体报道 明细
function reportDetail(discId) {
	var urlPath = basePath + '/f10/stock/report.html?guid=' + discId;
	_detail(urlPath);
}
// 市场评论 明细
function reviewDetail(discId) {
	var urlPath = basePath + '/f10/stock/review.html?guid=' + discId;
	_detail(urlPath);
}
// 内容明细页
function _detail(urlPath) {
	$("#jump").hide();
	$("#content").hide();
	$("#goback_li").show();
	$("#detail").show();

	$.ajax({
		url : urlPath,
		dataType : 'json',
		type : 'get',
		success : function(data) {
			if (data) {
				console.log(data);
				var html = data.content;
				// html = '<div class="headline">详情<div class="right"
				// onclick="backtoMain()">返回</div></div>' + html;
				$("#detail").html(html);
			} else {
			}
		},
		error : function(xhr, status) {

		}
	});
}

