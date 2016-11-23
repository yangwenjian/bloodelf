function shareDetail() {
	$
			.ajax({
				url : basePath + '/f10/stock/sharecon/detail.html?stockcode='
						+ stockcode,
				dataType : 'json',
				type : 'get',
				success : function(data) {
					if (data) {
						// console.log(data);
						var shareCons = data;
						var html = [];
						// console.log(data);
						html.push('<div class="content">');
						html.push('<div class="headline">股份构成</div>');
						html.push('<div class="table-responsive  content">');
						html
								.push('<table class="table table-bordered table-condensed line">');
						html.push('<thead class="headline">');
						html.push('<tr>');
						html.push('<td>股份构成</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>' + shareCons[i].changedate
									+ '</td>');
						}
						html.push('</tr>');
						html.push('</thead>');
						html.push('<tbody>');
						html.push('<tr>');
						html.push('<td class="headline">总股本</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>' + shareCons[i].total + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">流通股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>' + shareCons[i].flshr + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">流通A股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>' + shareCons[i].flashr + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">已上市流通A股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>' + shareCons[i].listFlashr
									+ '</td>');
						}
						html.push('</tr>');

						html.push('<tr>');
						html.push('<td class="headline">受限流通股份</td>');
						for (var i = 0; i < shareCons.length; i++) {
							console.log("hello");
							html.push('<td>'
									+ (shareCons[i].totLtdfl == null ? 0
											: shareCons[i].totLtdfl) + '</td>');
						}
						html.push('</tr>');

						html.push('<tr>');
						html.push('<td class="headline">境内法人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].domleg == null ? 0
											: shareCons[i].domleg) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">高管股(已流通)</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].mngfl == null ? 0
											: shareCons[i].mngfl) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">战略投资者配售股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].strapla == null ? 0
											: shareCons[i].strapla) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">基金配售股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].fundpla == null ? 0
											: shareCons[i].fundpla) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">法人配售股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].legpla == null ? 0
											: shareCons[i].legpla) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">B股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].bshr == null ? 0
											: shareCons[i].bshr) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">H股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].hshr == null ? 0
											: shareCons[i].hshr) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">S股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].sshr == null ? 0
											: shareCons[i].sshr) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">N股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].nshr == null ? 0
											: shareCons[i].nshr) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">境外上市其他股份</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html
									.push('<td>'
											+ (shareCons[i].othAbroad == null ? 0
													: shareCons[i].othAbroad)
											+ '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其他已流通股份</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].othfl == null ? 0
											: shareCons[i].othfl) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">非流通股合计</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].totNonfl == null ? 0
											: shareCons[i].totNonfl) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">国家及国有法人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].stateLegshr == null ? 0
											: shareCons[i].stateLegshr)
									+ '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其中:国家拥有股份</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].state == null ? 0
											: shareCons[i].state) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其中:国有法人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].stateleg == null ? 0
											: shareCons[i].stateleg) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">境内自然人持股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].domNatural == null ? 0
											: shareCons[i].domNatural)
									+ '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其他发起人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].othspon == null ? 0
											: shareCons[i].othspon) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">外资持股<</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].frgnshr == null ? 0
											: shareCons[i].frgnshr) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其中:外资法人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].frgnleg == null ? 0
											: shareCons[i].frgnleg) + '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">其中：境外自然人持股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].frgnNatural == null ? 0
											: shareCons[i].frgnNatural)
									+ '</td>');
						}
						html.push('</tr>');
						html.push('<tr>');
						html.push('<td class="headline">募集法人股</td>');
						for (var i = 0; i < shareCons.length; i++) {
							html.push('<td>'
									+ (shareCons[i].raisleg == null ? 0
											: shareCons[i].raisleg) + '</td>');
						}
						html.push('</tr>');

						html.push('</tbody>');
						html.push('</table>');
						html.push('</div>');
						$("#content").hide();
						$("#jump").hide();
						$("#goback_li").show();
						$("#detail").html(html.join(""));
						$("#detail").show();
					} else {

					}
				},
				error : function(xhr, status) {

				}
			});
}
