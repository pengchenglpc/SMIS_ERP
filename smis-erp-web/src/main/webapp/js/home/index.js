$(function(){
	$.get('home/login_query.smis', null, function(result){
		$('#login_user_span').html(result['data']['userName']);
	}, 'JSON');

});