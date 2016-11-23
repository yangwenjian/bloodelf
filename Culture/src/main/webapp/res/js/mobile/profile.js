
$(function() {
	$
			.ajax({
				url : basePath + '/f10/stock/business/main.html?stockcode='
						+ stockcode,
				dataType : 'json',
				type : 'get',
				success : function(data) {
					if (data) {
						// 基于准备好的dom，初始化echarts实例
						var myChart = echarts.init(document
								.getElementById('mainbus'));
						var mainBusStructs = data.info;
						var seriesData = new Array();
						for (var i = 0; i < mainBusStructs.length; i++) {
							// console.log(mainBusStructs[i]);
							var item = {};
							item.name = mainBusStructs[i].itemName + '\n'
									+ mainBusStructs[i].income.toFixed(2) + '亿';
							item.value = mainBusStructs[i].income;
							seriesData[i] = item;
						}
						// console.log(seriesData);
						// 指定图表的配置项和数据
						var option = {
							title : {
								text : '公司主营构成',
								subtext : '主营构成',
								x : 'center'
							},
							tooltip : {
								trigger : 'item',
								formatter : "{a} <br/>{b} : {c} ({d}%)"
							},
							legend : {
								orient : 'vertical',
								left : 'left',
								data : [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎' ]
							},
							series : [ {
								name : '主营构成',
								type : 'pie',
								radius : '55%',
								center : [ '50%', '60%' ],
								data : seriesData,
								itemStyle : {
									emphasis : {
										shadowBlur : 10,
										shadowOffsetX : 0,
										shadowColor : 'rgba(0, 0, 0, 0.5)'
									}
								}
							} ]
						};
						// 使用刚指定的配置项和数据显示图表。
						myChart.setOption(option);
					} else {

					}
				},
				error : function(xhr, status) {

				}
			});
});

function comprofileDetail() {
	$
			.ajax({
				url : basePath + '/f10/stock/profile/detail.html?stockcode='
						+ stockcode,
				dataType : 'json',
				type : 'get',
				success : function(data) {
					if (data) {
						// console.log(data);
						var comProfile = data;
						var html = [];
						// console.log(data);
						html.push('<div class="content">');
						html
								.push('<div class="headline">公司概况</div>');
						html.push('<div class="table-responsive content">');
						html
								.push('<table class="table table-bordered table-condensed line">');
						html.push('<tbody>');
						html.push('<tr><td class="headline">股票简称</td><td>');
						html.push(comProfile.stockname);
						html.push('</td><td class="headline">股票代码</td><td>');
						html.push(comProfile.stockcode + '</td>	</tr>');
						html
								.push('<tr><td class="headline">相关证券</td><td colspan="3">【');
						// console.log(comProfile.relStocks);
						for (var i = 0; i < comProfile.relStocks.length; i++) {
							html.push(comProfile.relStocks[i].sectype);
							html.push(':');
							html.push(comProfile.relStocks[i].secsname);
							html.push('(');
							html.push(comProfile.relStocks[i].seccode);
							html.push(')');
							html.push(comProfile.relStocks[i].mkttype);
						}
						html.push('】</td></tr>');
						// 曾用简称
						html
								.push('<tr><td class="headline">曾用简称</td><td colspan="3">');
						for (var i = 0; i < comProfile.formerNames.length - 1; i++) {
							html.push(comProfile.formerNames[i]);
							html.push('<br>');
						}
						html
								.push(comProfile.formerNames[comProfile.formerNames.length - 1]);
						html.push('</td></tr>')
						//
						html
								.push('<tr><td class="headline">中文名</td><td colspan="3">');
						html.push(comProfile.cname);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">英文名</td><td colspan="3">');
						html.push(comProfile.ename);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">相关指数</td><td colspan="3">');
						console.log(comProfile.index.length);
						for (var i = 0; i < comProfile.index.length; i++) {
							html.push(comProfile.index[i]);
							html.push('&nbsp;&nbsp;');
						}
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">注册地址</td><td colspan="3">');
						html.push(comProfile.regiAddr);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">办公地址</td><td colspan="3">');
						html.push(comProfile.officeAddr);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">主营业务</td><td colspan="3">');
						html.push(comProfile.briefBuz);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">公司网址</td><td>');
						html.push(comProfile.website);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">电子信箱</td><td>');
						html.push(comProfile.email);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">所属行业</td><td>');
						html.push(comProfile.induName);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">成立日期</td><td>');
						html.push(comProfile.builddate);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">注册资本</td><td>');
						html.push(comProfile.regicap);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">法人代表</td><td>');
						html.push(comProfile.legPerson);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">总经理</td><td>');
						html.push(comProfile.manager);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">董秘</td><td>');
						html.push(comProfile.boardSectry);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">证券事务代表</td><td>');
						html.push(comProfile.redr);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">电话</td><td>');
						html.push(comProfile.tel);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">传真</td><td>');
						html.push(comProfile.fax);
						html.push('</td></tr>');
						html.push('<tr><td class="headline">邮政编码</td><td>');
						html.push(comProfile.postcode);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">律师事务所</td><td colspan="3">');
						html.push(comProfile.lawOrgCode);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">财务审计</td><td colspan="3">');
						html.push(comProfile.accOrgCode);
						html.push('</td></tr>');
						html
								.push('<tr><td class="headline">历史沿革</td><td colspan="3">');
						html.push(comProfile.briefIntro);
						html.push('</td></tr>');
						html.push('</tbody>');
						html.push('</table>');
						html.push('</div>');
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
