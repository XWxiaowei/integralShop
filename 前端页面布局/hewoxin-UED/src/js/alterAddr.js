/**
 * Created by yovino on 2016/8/5.
 */
(function(){
    //手机号码验证
    $('#bind-tel').on('input',function(){
        var val = $(this).val();
        val = val.replace(/[^0-9]+/, "");
        $(this).val(val);
        inputMaxBlur($(this),11);
    }).on('blur',function(){
        getTelInfo($(this));
        bindBtn();
    }); 

    $('#bind-name').on('blur',function (){
        //inputBlur($(this),'name');
        bindBtn();
    });

    $('#bind-addr').on('blur',function (){
        //inputBlur($(this),'addr');
        bindBtn();
    });

    //输入字符数达到限制自动失去焦点
    function inputMaxBlur(ele,num) {
        if (ele.val().length === num) {
            window.setTimeout(function(){
                ele.blur();
            },1);
        }
    }

    function inputBlur(ele,opt){
        var element = ele.val();
        var validate = false;
        if(!element){
            $('#bind-'+ opt + 'Tip').css('color','red').html('该项不能为空！');
        }else{
            $('#bind-'+ opt + 'Tip').html('');
            validate = true;
        }
        ele.data('validate', validate);
    }

    /**
     * 验证手机号码
     */
    function getTelInfo(ele){
        var tel = ele.val();
        var validate = false;
        if(!tel){
            $('#bind-telTip').css('color','red').html('收货手机不能为空！');
        }else if(/^1[3|4|5|7|8]\d{9}$/.test(tel)){
            $('#bind-telTip').html('');
            validate = true;
        }else{
            $('#bind-telTip').css('color','red').html('请输入正确的手机号码！');
        }
        ele.data('validate', validate);
    }

    /**
     * 绑定按钮状态
     */
    function bindBtn(){
        return $('#bind-tel').data("validate") === 'true';
    }

    $('#bind-btn').on('click', function () {
        getTelInfo($('#bind-tel'));
        if(bindBtn()){
            $('.del').addClass('hide');
            $('.mod').removeClass('hide');
            $('#popup').modal('show');
        }
    });

    $('#new-btn').on('click', function () {
        getTelInfo($('#bind-tel'));
        if(bindBtn()){
            $('.del').addClass('hide');
            $('.mod').removeClass('hide');
            $('#popup_new').modal('show');
        }
    });

})();