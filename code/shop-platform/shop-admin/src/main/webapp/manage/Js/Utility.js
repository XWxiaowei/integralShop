/**
 * 工具函数
 */

//非空验证
function emptyVerify(obj) {
	if (!obj.value.trim()) {
		$(obj).nextAll('.empty').show();
		return false
	} else {
		return true
	}
}

//验证长度
function checkLength(selector, minLength, maxLength){
	var objList = $(selector);//document.getElementById(objID);
	if(objList == null)return false;
	
	var bFlagList = Array.prototype.map.call(objList, function(obj){
		if(minLength === undefined && maxLength === undefined)
			return emptyVerify(obj);
		
		var nLength = obj.value.trim().length;
		
		var bFlag = true;
		if(minLength != undefined){
			minLength = Math.abs(minLength);
			bFlag = bFlag && (nLength >= minLength);
		}
		
		if(maxLength != undefined){
			maxLength = Math.abs(maxLength);
			bFlag = bFlag && (nLength <= maxLength);
		}
		
		return bFlag;
	});
	
	
	var bResult = Array.prototype.reduce.call(bFlagList, function(x, y){
		return x && y;
	}, true);
	
	if(!bResult){
		Array.prototype.forEach.call(objList, function(obj){
			obj.value = "";
			$(obj).nextAll('.empty').show();
		});		
	}
	
	return bResult;
}

//根据类名和回调函数来检验，验证通过返回true, 否则返回false
function checkByClass(className, callback){
	/*var bFlags = $('.' + className).map(callback);

	var bResult = Array.prototype.some.call(bFlags, function(element) {
		if (!element)
			return true;
	});

	return !bResult;*/
	var bFlags = $('.' + className).map(callback);
	return !bFlags.is(function(){
		return this == false;
	});
}

function checkBeforeSubmit() {
	//alert('onsubmit');
	//长度验证
	var result =checkByClass("needCheck", function() {
		return checkLength(this);//emptyVerify(this);
	});
	
	if(!result){
		return false;
	}
	
	//数字验证
	result= checkByClass("needNumber", function(){
		var szReg= /^\d{1,}$/;
		if(!szReg.test(this.value)){
			$(this).nextAll('.empty').show();
			return false;
		}
		
		return true;
	});
	
	if(!result){
		return false;
	}
	
	//选择验证
	result = checkByClass("needSelect", function(){
		if(this.selectedIndex <= 0){
			$(this).nextAll('.empty').show();
			return false;
		}			
		else
			return true;
	});
	
	if(!result)
		return false;

	//有效性验证
	//非负验证
	result = checkByClass("needNonNegtive", function(){
		var szReg = /^([1-9][0-9]*|0)$/;
		if(!szReg.test(this.value.toString())){
			$(this).nextAll('.empty').show();
			return false;
		}			
		else
			return true;
	});
	
	if(!result)
		return false;
	
	//正数验证
	result = checkByClass("needPositive", function(){
		var szReg = /^[1-9][0-9]*$/;
		if(!szReg.test(this.value.toString())){
			$(this).nextAll('.empty').show();
			return false;
		}			
		else
			return true;
	});
	
	if(!result)
		return false;
	
	//手机号验证
	var mobilePhone = document.getElementById('mobilePhone');
	if(mobilePhone!=null){
		var szPhoneRegExp = /^1[3578](\d{9})$/;
		var szPhoneContent = mobilePhone.value;
		if (!szPhoneRegExp.test(szPhoneContent)) {
			$(mobilePhone).nextAll('.empty').show()
			return false;
		}
	}

	//邮箱
	var email = document.getElementById('email');
	if(email!=null){
		//var szEmailRegexp = /^.+@.+\..+$/;
		var szEmailRegexp = /^[_a-z\d\-\./]+@[_a-z\d\-]+(\.[_a-z\d\-]+)*(\.(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn))$/;
		var szEmailContent = email.value.toLowerCase();
		if (!szEmailRegexp.test(szEmailContent)) {
			$(email).nextAll('.empty').show()
			return false;
		}
	}
	

	return true;
}

$(function(){
	$('.deleteConfirm').click(function(){
		var szAlert = $(this).attr("info") || "确定要删除吗?" ;
		
		if(confirm(szAlert)){
			window.location.href=$(this).attr("href");//this.url;
		}
		else{
			return false;
		}
	});
	
	$(":input").change(function(){
		//$(this).(".prompt:first").
	});
	
	$('.needCheck, .needNumber').focus(function(){
        //
        $(this).nextAll('.empty').hide();
    });
});