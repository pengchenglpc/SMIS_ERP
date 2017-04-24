$(function(){
	$("form.form-horizontal button[name=login]").click(function(){
		var params = $("form.form-horizontal").serializeJSON();
		if(!params['loginName']){
			$(".alert").addClass('alert-warning').html("请输入用户名");
			$("form.form-horizontal input[name=loginName]").focus();
			return;
		}
		if(!params['password']){
			$(".alert").addClass('alert-warning').html("请输入密码");
			$("form.form-horizontal input[name=password]").focus();
			return;
		}
		if(!params['remember']){
			params['remember'] = '0';
		}
		params['password'] = $.md5(params['password']);
		$.post('home/login.smis', params, function(result){
			if(result.resultCode < 0){
				$(".alert").addClass('alert-warning').html(result.msg)
			}else{
				location.href = "index.html";
			}
		}, 'JSON');
	});
});