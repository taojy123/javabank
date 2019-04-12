	//对Date的扩展，将 Date 转化为指定格式的String   
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
	// 例子：   
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
	Date.prototype.Format = function(fmt)   
	{ // author: meizz
	  var o = {   
	    "M+" : this.getMonth()+1,                 // 月份
	    "d+" : this.getDate(),                    // 日
	    "h+" : this.getHours(),                   // 小时
	    "m+" : this.getMinutes(),                 // 分
	    "s+" : this.getSeconds(),                 // 秒
	    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
	    "S"  : this.getMilliseconds()             // 毫秒
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}  
	$.fn.datetimepicker.dates['zh-CN'] = {
			  days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
			  daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			  daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
			  months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			  monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			  today: "今天",
			  suffix: [],
			  meridiem: ["上午", "下午"],
			  rtl: true // 从右向左书写的语言你可以使用 rtl: true 来设置
			};
	/**
	 * 封装模态窗口
	 * 
	 * @param {Object}
	 *            title 提示标题
	 * @param {Object}
	 *            content 提示内容
	 */
	$.fn.alertModal = function(title, content,fun) {
		$("button:contains('操作中...')").removeAttr("disabled");
		$("button:contains('操作中...')").text("提交保存");
		var _this = $(this);
//		_this.append('<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">' +
//			'<div class="am-modal-dialog">' +
//			'<div class="am-modal-hd"></div>' +
//			'<div class="am-modal-bd">'
//
//			+
//			'</div>' +
//			'<div class="am-modal-footer">' +
//			' <span class="am-modal-btn" >确定</span>' +
//			'</div>' +
//			'</div>' +
//			'</div>');
//		var $alertModal = $("#my-alert", _this);
//		$(".am-modal-hd", $alertModal).text(title);
//		$(".am-modal-bd", $alertModal).html(content);
//		$alertModal.modal({
//			closeViaDimmer: false
//		});
//		// 点击确认后移除html
//		$(".am-modal-btn", $alertModal).click(function() {
//			$alertModal.remove();
//			// 清除遮罩层 以及 body控制
//			closeDimmer();
//			if(isFunction(fun))
//				fun();
//		});
		//layer.alert(content);
//		layer.alert(content, {closeBtn: 0 ,anim: 4 ,icon: content.indexOf("成功")>-1?6:5},function(index){
//			if(isFunction(fun))
//				fun();
//			layer.close(index);
//		});
		layer.msg(content);
		setTimeout(function(){
			fun();
		},1500);
		
//		layer.msg(content, {icon: content.indexOf("成功")>-1?6:5}, function(){
//			if(isFunction(fun))
//				fun();
//		});
		

	}
	
	$.fn.alertImgModal = function(title, content,fun) {
		
		var _this = $(this);
		_this.append('<div class="am-popup" id="my-popup" style="z-index:9999;">'+
		  '<div class="am-popup-inner" >'+
		    '<div class="am-popup-hd">'+
		      '<h4 class="am-popup-title">...</h4>'+
		      '<span data-am-modal-close class="am-close">&times;</span>'+
		   ' </div>'+
		    '<div class="am-popup-bd" >'+
		    '</div>'+
		  '</div>'+
		'</div>');
		
		var $alertModal = $("#my-popup", _this);
		$(".am-popup-title", $alertModal).text(title);
		$(".am-popup-bd", $alertModal).html(content);
		$('.am-slider-manual').flexslider({
			playAfterPaused: 1000,
			controlNav: 'thumbnails', 
			directionNav: false,
			slideshow: true
			
		});
		$alertModal.modal({
			closeViaDimmer: false
		});
		// 点击确认后移除html
		$(".am-close", $alertModal).click(function() {
			$alertModal.remove();
			// 清除遮罩层 以及 body控制
			closeDimmer();
			if(isFunction(fun))
				fun();
		});

	}
	
	
	

	/**
	 * 封装内容模态窗口
	 * 
	 * @param {Object}
	 *            title 提示标题
	 * @param {Object}
	 *            confirm 确定按钮
	 */
	$.fn.alertInputModal = function(title, content, btn, confirm, cancle) {
		var _this = $(this);
		_this.append('<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">' +
			'<div class="am-modal-dialog"><div class="am-modal-hd"></div><div class="am-modal-bd"><input type="text" class="am-modal-prompt-input">' +
			'</div><div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-cancel>取消</span>' +
			'<span class="am-modal-btn confirm"  data-am-modal-confirm></span></div></div></div>');
		var $alertModal = $("#my-prompt", _this);
		$(".am-modal-hd", $alertModal).text(title);
		if(content) {
			$(".am-modal-prompt-input", $alertModal).val(content);
		}
		$(".confirm", $alertModal).text(btn);
		$alertModal.modal({
			closeViaDimmer: false,
			relatedTarget: this,
			onConfirm: function(e) {
				if(isFunction(confirm)) {
					confirm(e.data);
				}
				$alertModal.remove();
			},
			onCancel: function(e) {
				$alertModal.remove();
				// 清除遮罩层 以及 body控制
				closeDimmer();
				$('.am-modal-prompt-input').val('');
				if(isFunction(cancle))
					cancle();
			}
		});
	}

	/**
	 * 封装取消－确定模态窗口
	 * 
	 * @param {Object}
	 *            title 提示内容
	 * @param {Object}
	 *            confirm 确定按钮
	 */
	$.fn.confirmModal = function(title,content,confirm,btnleftContent,btnrightContent) {
		
//		var _this = $(this);
//		_this.append('<div class="am-modal am-modal-confirm" tabindex="-1" id="my-tip" style="padding:35px;">' +
//			'<div class="am-modal-dialog"><div class="am-modal-hd">提示</div><div class="am-modal-bd"></div>' +
//			'<div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-confirm>'+(btnleftContent!=undefined?btnleftContent:"确定")+'</span>' +
//			'<span class="am-modal-btn"  data-am-modal-cancel>'+(btnrightContent!=undefined?btnrightContent:"取消")+'</span></div></div></div>');
//		var $confirmModal = $("#my-tip");
//		$(".am-modal-hd", $confirmModal).text(title);
//		$(".am-modal-bd", $confirmModal).html(content);
//		
//		$confirmModal.modal({
//			closeViaDimmer: false,
//			relatedTarget: this,
//			onConfirm: function() {
//				if(isFunction(confirm)) {
//					confirm($(this.relatedTarget));
//				}
//				$confirmModal.remove();
//				closeDimmer();
//			},
//			onCancel: function() {
//				$confirmModal.remove();
//				closeDimmer();
//			}
//		});
		
		layer.confirm(content, {
			  title:title,
			  btn: [(btnleftContent!=undefined?btnleftContent:"确定"),(btnrightContent!=undefined?btnrightContent:"取消")] //按钮
			}, function(){
				confirm();
				
			}, function(){
		});
	}

	/**
	 * 封装加载模态窗口
	 * 
	 * @param {Object}
	 *            title 提示内容
	 * @param {Object}
	 *            confirm 确定按钮
	 */
	$.fn.alertLoadingModal = function() {
		var _this = $(this);
		_this.append('<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">' +
			'<div class="am-modal-dialog"><div class="am-modal-hd">加载中</div><div class="am-modal-bd">' +
			'<span class="am-icon-spinner am-icon-spin"></span></div></div></div>');
		var $alertModal = $("#my-modal-loading", _this);
		$alertModal.modal({
			closeViaDimmer: false
		});
	}

function closeLoading() {
	var $alertModal = $("#my-modal-loading");
	$alertModal.remove();
	closeDimmer();
}


function closeDimmer() {
	if($(".am-dimmer").hasClass("am-active")) {
		$("body").removeClass("am-dimmer-active");
		$(".am-dimmer").removeClass("am-active").css("display", "none");
		$("button:contains('操作中，请勿重复点击')").text("提交保存").removeAttr("disabled");
	}
}

function isFunction(obj) {
	return typeof obj === 'function';
}
function CKupdate() {
    for (instance in CKEDITOR.instances)
        CKEDITOR.instances[instance].updateElement();
}

