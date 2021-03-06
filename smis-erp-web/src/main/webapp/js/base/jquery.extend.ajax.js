(function($){  
    //首先备份下jquery的ajax方法  
    var _ajax=$.ajax;  
       
    //重写jquery的ajax方法  
    $.ajax=function(url, opt){  
        //备份opt中error和success方法  
    	if(typeof url == 'object'){
    		opt = url;
    	}else{
    		opt['url'] = url;
    	}
        var fn = {  
            error:function(XMLHttpRequest, textStatus, errorThrown){},  
            success:function(data, textStatus){}  
        }  
        if(opt.error){  
            fn.error=opt.error;  
        }  
        if(opt.success){  
            fn.success=opt.success;  
        }  
           
        //扩展增强处理  
        var _opt = $.extend(opt,{  
            error:function(XMLHttpRequest, textStatus, errorThrown){  
                //错误方法增强处理  
                fn.error(XMLHttpRequest, textStatus, errorThrown);  
            },  
            success:function(data, textStatus){  
                //成功回调方法增强处理  
            	if(data['login']){
            		if(data['resultCode'] >= 0){
            			fn.success(data, textStatus);
            		}else{
            			$('body').noty({layout:'center',type:'error',text: data['msg']});
            		}
            	}else{
            		location.href = "login.html";
            	}
            },  
            beforeSend:function(XHR){  
                //提交前回调方法  
                //$('body').append("<div id='ajaxInfo' style=''>正在加载,请稍后...</div>");  
            },  
            complete:function(XHR, TS){  
                //请求完成后回调函数 (请求成功或失败之后均调用)。  
                //$("#ajaxInfo").remove();;  
            }  
        });  
        return _ajax(_opt);  
    };  
})(jQuery); 