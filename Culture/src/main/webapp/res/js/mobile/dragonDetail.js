/**
 * Created by mars on 2016/11/12.
 */
var times = 1;// 每次加载到了第几个
var isPost = true;// 请求后台获取数据。false不请求。
function loadDragonData(stockcode) {
    if(!isPost){
        console.log("没有数据了，别请求了。。。");
        return;
    }
    $("#content").hide();
    $("#jump").hide();
    $("#goback_li").show();
    $("#dragonDetail").show();
    $.ajax({
        url : basePath + '/f10/stock/dragon.html?stockcode=' + stockcode+"&times="+times,
        dataType : 'json',
        type : 'get',
        success : function(data) {
            if (data && data != null) {
                console.log(data);
                addDragonHtml(data);
            } else {
                isPost = false;
            }
        },
        error : function(xhr, status) {
            isPost = false;
        }
    });
}
// 动态加载--交易数据
function addDragonHtml(fluct) {
    //var fluct = $.parseJSON(fluct);
    var html = "<div>"
        +' <div class="line" style="color: #fef3cb;">'+times+'.&nbsp;'+fluct.refName
        +" <br/>&nbsp;&nbsp;["+fluct.enddate+"]&nbsp;跌幅："+fluct.fflChangePct
        +"&nbsp;成交量："+fluct.volume+"(万)&nbsp;"
        +" <br/>&nbsp;&nbsp;成交额："+fluct.value+"(万)"
        +" </div>"
        // 卖家
        +' <div class="table-responsive" style="margin-bottom: 0px;margin-left: 1px;">'
        +' <table class="table table-bordered table-condensed line table_body" style="border:1px solid #000;">'
        +'<thead class="headline">'
        +'<tr>'
        +"<td>卖出金额居前的营业部名称</td>"
        +"<td>买入金额(元)</td>"
        +"<td>卖出金额(元)</td>"
        +"</tr>"
        +"</thead>"
        +"<tbody>";

    console.log("卖："+fluct.details.length);
    for(var i =0; i<fluct.details.length; i++){
        // 限制 5 个
        // if(i>=5){
        //     break;
        // }
        var rankCls= fluct.details[i].rankCls;
        if(rankCls != "卖出金额最大的前5名"){
            continue;
        }
        var bvalue = "0.00";
        if("bvalue" in fluct.details[i]){
            bvalue = fluct.details[i].bvalue;
            if(bvalue==null || bvalue=="undefined" || bvalue==""){
                bvalue = "0.00";
            }
        }
        var svalue = "0.00";
        if("svalue" in fluct.details[i]){
            svalue = fluct.details[i].svalue;
            if(svalue==null || svalue=="undefined" || svalue==""){
                svalue = "0.00";
            }
        }

        html = html+"<tr>"
        +"<td>"+fluct.details[i].branchName+"</td>"
        +"<td>"+bvalue+"</td>"
        +"<td>"+svalue+"</td>"
        +"</tr>";
    }
        html = html+"</tbody>"
        +"</table>"
        +"</div><br/>"
        // 买家
        +' <div class="table-responsive" style="margin-bottom: 0px;margin-left: 1px;">'
        +' <table class="table table-bordered table-condensed line table_body" style="border:1px solid #fff;">'
        +'<thead class="headline">'
        +'<tr>'
        +"<td>买入金额居前的营业部名称</td>"
        +"<td>买入金额(元)</td>"
        +"<td>卖出金额(元)</td>"
        +"</tr>"
        +"</thead>"
        +"<tbody>";
    console.log("买："+fluct.details.length);
    for(var i =0; i<fluct.details.length; i++){
        // 限制 5 个
        // if(i>=5){
        //     break;
        // }
        var rankCls= fluct.details[i].rankCls;
        console.log("11："+rankCls);
        if(rankCls != "买入金额最大的前5名"){
            continue;
        }
        var bvalue = "0.00";
        if("bvalue" in fluct.details[i]){
            bvalue = fluct.details[i].bvalue;
            if(bvalue==null || bvalue=="undefined" || bvalue==""){
                bvalue = "0.00";
            }
        }
        var svalue = "0.00";
        if("svalue" in fluct.details[i]){
            svalue = fluct.details[i].svalue;
            if(svalue==null || svalue=="undefined" || svalue==""){
                svalue = "0.00";
            }
        }
        html = html+"<tr>"
        +"<td>"+fluct.details[i].branchName+"</td>"
        +"<td>"+bvalue+"</td>"
        +"<td>"+svalue+"</td>"
        "</tr>";
    }
        html = html+"</tbody>"
        +"</table>"
        +"</div>"

        +"</div><br/>";
    $("#dragonAddLoad").before(html);
    times++;
}