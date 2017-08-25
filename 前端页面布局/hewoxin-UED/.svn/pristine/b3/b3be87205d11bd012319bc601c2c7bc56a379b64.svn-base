var shared = false;
var current = 0;
var remain = 0;
var costFlows = 0;
var lenFlows = 0;
var hasEnoughFlow = true;
var bRotate = false;
var isConfirm = false;
var lotteryMap = {
    "特等奖": 0,
    "一等奖": 2,
    "二等奖": 4,
    "三等奖": 6
};
var failMap = 1;
var TIP_DIALOG = {
    "FAIL_1": "虽然这次没有中，但梦想<br>还是要有的，要么再来一次？"
};

var awardMap = {
    "AWARD_1": "流量直接充入您的账户中，<br>请进入中移流量Plus公众号内查看，谢谢!",
    "AWARD_2": "后台客服人员会在三个工作日内联系您哦。"
};

var zeroMap = {
    "true": "别贪心，今天抽奖次数已经用完<br>了，请明天再来吧！",
    "false": "您中移流量Plus账户中的<br>流量额度不足！明天再来吧！"
};

$(function (){
    //渲染状态数据当前可玩的次数，是否已经分享等
    renderStatusData();

    //lottery插件
    lotteryAOP();

    //跑马灯
    $("#marquee").kxbdMarquee({direction:"up",isEqual:false,scrollDelay:"30"});

    //弹框事件监听
    dialogListeners();
});

/**
 * 弹框事件监听
 */
function dialogListeners(){
    $("#used .modal-footer").on("tap",function(){
        window.location.href = ctx+"/lottery/showBind.html";
    });

    $("#first .styleBtn").on("tap", function (){
        if(current < lenFlows){
            isConfirm = true;
            runLottery();
        }
        else{
            zero();
        }
    });


    $("#notFirst .styleBtn").on("tap",function () {
        if(current < lenFlows){
            isConfirm = true;
            runLottery();
        }
        else{
            zero();
        }
    });

    //点击分享的隐藏
    $('#share').on("tap",function () {
        $(this).modal('hide');
    });

    //关闭弹框时如果未分享则弹出分享框
    $('.close-font').on("tap", function () {
        if (shared === "false") {
            $('#share').modal('show');
        }
    });

    $(".cancel-play").on("tap", function(){
        lottery.running = false;
    });

    $('#afterShare .styleBtn').on("tap",function () {
        $("#afterShare").modal('hide');
    });
}

/**
 * 渲染状态数据
 * 当前可玩的次数，是否已经分享等
 */
function renderStatusData(){
    getCountAndShared(function (data){
        remain = data.count;
        current = data.current;
        costFlows = data.costFlows;
        lenFlows = costFlows.length;
        hasEnoughFlow = data.hasEnoughFlow + "";
        updateCount();
        shared = data.share + "";
    });
}

/**
 * 更新次数
 */
function updateCount(){
    $('#remainCount').html(remain);
}

/**
 * 回调
 */
function lotteryAOP(){
    lottery.beforeRun = function(){
        //次数判定
        if(!zero()){
            return false;
        }
        //正在运行则返回
        if(lottery.running){
            return;
        }
        lottery.running = true;
        //验证是否绑定
        checkIsBind(function(data){
            if(!current){
                $('#first').modal('show');
            }else{
                $('#cost-flow').html(costFlows[current]);
                $('#notFirst').modal('show');
            }
            //runLottery();
        });
    };
}

/**
 * 判断是否绑定
 */
function checkIsBind(callback){
    isBind(function(data){
        //是否绑定
        if(data.code == "302"){
            window.location.href = ctx+"/lottery/showBind.html";
        }
        //是否用过
        else if (data.code == "303"){
            $('#used').modal('show');
        }
        //网络问题
        else if (data.code == "202"){
            showDialogContent("busy");
        }
        else{
            if(callback){
                callback();
            }
        }
    });
}

/**
 * 获取次数和分享状态
 */
function getCountAndShared(cback) {
    $.ajax({
        url: ctx + "/lottery/shareCount.html?_=" + new Date(),
        type: "POST",
        async: false,
        dataType: "json",
        success: function (data) {
            if(cback){
                cback(data);
            }
        }
    });
}

/**
 * 检查绑定和使用
 */
function isBind(cback){
    $.ajax({
        url: ctx + "/lottery/preCheck.html",
        type: "GET",
        timeout: 5000,
        dataType: "json",
        success: function(data) {
            if(cback){
                cback(data);
            }
        },
        error: function (){
            //网络延时处理
            showDialogContent("busy");
        }
    });
}

/**
 * 运行转盘
 */
function runLottery() {
    $.ajax({
        url: ctx + "/lottery/playAjax.html",
        type: "GET",
        timeout: 5000,
        dataType: "json",
        success: function(data){
            //错误数据
            if(data.code === '202'){
                showDialogContent("busy");
                return;
            }

            remain--;
            if(remain <= 0) remain = 0;
            //remain = lenFlows - current - 1;
            updateCount();
            if(isConfirm === true){
                isConfirm = false;
                current++;
            }
            //中奖
            if (data.winning) {
                lottery.start({
                    index: lotteryMap[data.rankName],
                    done: function(){
                        $('#flow-size').html(data.prizeName);
                        if(data.prizeName.indexOf("流量") > 0 )
                        {
                            $('.body-flow .tip-font').html(awardMap.AWARD_1);
                        }else{
                            $('.body-flow .tip-font').html(awardMap.AWARD_2);
                        }
                        //showSharedModalFooter("flow");
                        showDialogContent("flow");
                    }
                });
            }else{
                //没中奖
                lottery.start({
                    index: failMap,
                    done: function(){
                        //showSharedModalFooter("failure");
                            $(".body-failure .tip-font").html(TIP_DIALOG.FAIL_1);
                        showDialogContent("failure");
                    }
                });
            }
        },
        error: function(){
            lottery.start({
                index: failMap,
                done: function(){
                    showDialogContent("busy");
                }
            });
        }
    });
}

/**
 * 未分享时弹出框按钮
 * @returns {boolean}
 */
// function showSharedModalFooter(clazz){
//     var ele = $('.body-' + clazz + ' .close-font');
//     ele.html(closeMap[shared]);
//     showDialogContent(clazz);
// }

/**
 * 次数零处理
 * @returns {boolean}
 */
function zero(){
    if (remain < 1 && current >= lenFlows) {
        if (lottery.running) {
            return false;
        } else {
            $('.body-zero .tip-font').html(zeroMap[hasEnoughFlow]);
            showDialogContent("zero");
            return false;
        }
    }else{
        return true;
    }
}

/**
 * 显示弹框
 */
function showDialogContent(clazz){
    $("#turntable-result .modal-content").addClass("hide");
    $('#turntable-result .body-'+clazz).removeClass('hide');
    $('#turntable-result').modal('show');
}