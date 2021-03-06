$(document).ready(function () {
    //themes, change CSS with JS
    //default theme(CSS) is cerulean, change it if needed
    var defaultTheme = 'cerulean';

    var currentTheme = $.cookie('currentTheme') == null ? defaultTheme : $.cookie('currentTheme');
    var msie = navigator.userAgent.match(/msie/i);
    $.browser = {};
    $.browser.msie = {};
    switchTheme(currentTheme);

    $('.navbar-toggle').click(function (e) {
        e.preventDefault();
        $('.nav-sm').html($('.navbar-collapse').html());
        $('.sidebar-nav').toggleClass('active');
        $(this).toggleClass('active');
    });

    var $sidebarNav = $('.sidebar-nav');

    // Hide responsive navbar on clicking outside
    $(document).mouseup(function (e) {
        if (!$sidebarNav.is(e.target) // if the target of the click isn't the container...
            && $sidebarNav.has(e.target).length === 0
            && !$('.navbar-toggle').is(e.target)
            && $('.navbar-toggle').has(e.target).length === 0
            && $sidebarNav.hasClass('active')
            )// ... nor a descendant of the container
        {
            e.stopPropagation();
            $('.navbar-toggle').click();
        }
    });


    $('#themes a').click(function (e) {
        e.preventDefault();
        currentTheme = $(this).attr('data-value');
        $.cookie('currentTheme', currentTheme, {expires: 365});
        switchTheme(currentTheme);
    });


    function switchTheme(themeName) {
        if (themeName == 'classic') {
            $('#bs-css').attr('href', 'js/bower_components/bootstrap/dist/css/bootstrap.min.css');
        } else {
            $('#bs-css').attr('href', 'css/bootstrap-' + themeName + '.min.css');
        }

        $('#themes i').removeClass('glyphicon glyphicon-ok whitespace').addClass('whitespace');
        $('#themes a[data-value=' + themeName + ']').find('i').removeClass('whitespace').addClass('glyphicon glyphicon-ok');
    }

    //ajax menu checkbox
    $('#is-ajax').click(function (e) {
        $.cookie('is-ajax', $(this).prop('checked'), {expires: 365});
    });
    $('#is-ajax').prop('checked', $.cookie('is-ajax') === 'true' ? true : false);

    //disbaling some functions for Internet Explorer
    if (msie) {
        $('#is-ajax').prop('checked', false);
        $('#for-is-ajax').hide();
        $('#toggle-fullscreen').hide();
        $('.login-box').find('.input-large').removeClass('span10');

    }


    //highlight current / active link
    $('ul.main-menu li a').each(function () {
        if ($($(this))[0].href == String(window.location))
            $(this).parent().addClass('active');
    });

    //establish history variables
    var
        History = window.History, // Note: We are using a capital H instead of a lower h
        State = History.getState(),
        $log = $('#log');

    //bind to State Change
    History.Adapter.bind(window, 'statechange', function () { // Note: We are using statechange instead of popstate
        var State = History.getState(); // Note: We are using History.getState() instead of event.state
        $.ajax({
            url: State.url,
            success: function (msg) {
                $('#content').html($(msg).find('#content').html());
                $('#loading').remove();
                $('#content').fadeIn();
                var newTitle = $(msg).filter('title').text();
                $('title').text(newTitle);
                docReady();
            }
        });
    });
    function handle(menus, pid){
		var _menus = [];
		for(var key in menus){
			var menu = menus[key];
			if(menu['parentId'] == pid){
				_menus.push(menu);
				delete menus[key];
				if(menu['menuType'] != 3){
					menu['children'] = handle(menus, menu['menuId']);
				}
			}
		}
		return _menus;
	}
	function createMenu(menus, container){
		for(var key in menus){
			var $li = $('<li>');
			var $a = $('<a>').appendTo($li);
			
			$li.appendTo(container);
			if(menus[key]['menuType'] == 1){
				$li.addClass('accordion');
				$a.attr('href', '#');
				$a.append('<i class="glyphicon glyphicon-align-justify"></i><span> '+ menus[key]['menuName'] + '</span>');
				var $ul = $('<ul class="nav nav-pills nav-stacked">').appendTo($li);
				createMenu(menus[key]['children'], $ul);
			}else{
				$a.addClass('ajax-link');
				$a.append('<i class="glyphicon"></i><span> '+ menus[key]['menuName'] + '</span>');
				$a.attr('href', menus[key]['url']);
			}
		}
		
	}
    $.ajax('home/getMenu.smis',{
    	async:false,
    	dataType:'json',
    	data:{id:0},
    	success:function(result){
    		var menus = result.data;
    		var _menus = handle(menus, 0);
    		createMenu(_menus, $('ul.main-menu'));
    	}
    });
    
    //ajaxify menus
    $('a.ajax-link').click(function (e) {
        if (msie) e.which = 1;
       // if (e.which != 1 || !$('#is-ajax').prop('checked') || $(this).parent().hasClass('active')) return;
        e.preventDefault();
        $('.sidebar-nav').removeClass('active');
        $('.navbar-toggle').removeClass('active');
        $('#loading').remove();
        $('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
        var $clink = $(this);
        History.pushState(null, null, $clink.attr('href'));
        $('ul.main-menu li.active').removeClass('active');
        $clink.parent('li').addClass('active');
    });

    $('.accordion > a').click(function (e) {
        e.preventDefault();
        var $ul = $(this).siblings('ul');
        var $li = $(this).parent();
        if ($ul.is(':visible')) $li.removeClass('active');
        else                    $li.addClass('active');
        $ul.slideToggle();
    });

    $('.accordion li.active:first').parents('ul').slideDown();


    //other things to do on document ready, separated for ajax calls
    docReady();
    var dataSet = [
                   ['Trident','Internet Explorer 4.0','Win 95+','4','X'],
                   ['Trident','Internet Explorer 5.0','Win 95+','5','C'],
                   ['Trident','Internet Explorer 5.5','Win 95+','5.5','A'],
                   ['Trident','Internet Explorer 6','Win 98+','6','A'],
                   ['Trident','Internet Explorer 7','Win XP SP2+','7','A'],
                   ['Trident','AOL browser (AOL desktop)','Win XP','6','A'],
                   ['Gecko','Firefox 1.0','Win 98+ / OSX.2+','1.7','A'],
                   ['Gecko','Firefox 1.5','Win 98+ / OSX.2+','1.8','A'],
                   ['Gecko','Firefox 2.0','Win 98+ / OSX.2+','1.8','A'],
                   ['Gecko','Firefox 3.0','Win 2k+ / OSX.3+','1.9','A'],
                   ['Gecko','Camino 1.0','OSX.2+','1.8','A'],
                   ['Gecko','Camino 1.5','OSX.3+','1.8','A'],
                   ['Gecko','Netscape 7.2','Win 95+ / Mac OS 8.6-9.2','1.7','A'],
                   ['Gecko','Netscape Browser 8','Win 98SE+','1.7','A'],
                   ['Gecko','Netscape Navigator 9','Win 98+ / OSX.2+','1.8','A'],
                   ['Gecko','Mozilla 1.0','Win 95+ / OSX.1+',1,'A'],
                   ['Gecko','Mozilla 1.1','Win 95+ / OSX.1+',1.1,'A'],
                   ['Gecko','Mozilla 1.2','Win 95+ / OSX.1+',1.2,'A'],
                   ['Gecko','Mozilla 1.3','Win 95+ / OSX.1+',1.3,'A'],
                   ['Gecko','Mozilla 1.4','Win 95+ / OSX.1+',1.4,'A'],
                   ['Gecko','Mozilla 1.5','Win 95+ / OSX.1+',1.5,'A'],
                   ['Gecko','Mozilla 1.6','Win 95+ / OSX.1+',1.6,'A'],
                   ['Gecko','Mozilla 1.7','Win 98+ / OSX.1+',1.7,'A'],
                   ['Gecko','Mozilla 1.8','Win 98+ / OSX.1+',1.8,'A'],
                   ['Gecko','Seamonkey 1.1','Win 98+ / OSX.2+','1.8','A'],
                   ['Gecko','Epiphany 2.20','Gnome','1.8','A'],
                   ['Webkit','Safari 1.2','OSX.3','125.5','A'],
                   ['Webkit','Safari 1.3','OSX.3','312.8','A'],
                   ['Webkit','Safari 2.0','OSX.4+','419.3','A'],
                   ['Webkit','Safari 3.0','OSX.4+','522.1','A'],
                   ['Webkit','OmniWeb 5.5','OSX.4+','420','A'],
                   ['Webkit','iPod Touch / iPhone','iPod','420.1','A'],
                   ['Webkit','S60','S60','413','A'],
                   ['Presto','Opera 7.0','Win 95+ / OSX.1+','-','A'],
                   ['Presto','Opera 7.5','Win 95+ / OSX.2+','-','A'],
                   ['Presto','Opera 8.0','Win 95+ / OSX.2+','-','A'],
                   ['Presto','Opera 8.5','Win 95+ / OSX.2+','-','A'],
                   ['Presto','Opera 9.0','Win 95+ / OSX.3+','-','A'],
                   ['Presto','Opera 9.2','Win 88+ / OSX.3+','-','A'],
                   ['Presto','Opera 9.5','Win 88+ / OSX.3+','-','A'],
                   ['Presto','Opera for Wii','Wii','-','A'],
                   ['Presto','Nokia N800','N800','-','A'],
                   ['Presto','Nintendo DS browser','Nintendo DS','8.5','C/A<sup>1</sup>'],
                   ['KHTML','Konqureror 3.1','KDE 3.1','3.1','C'],
                   ['KHTML','Konqureror 3.3','KDE 3.3','3.3','A'],
                   ['KHTML','Konqureror 3.5','KDE 3.5','3.5','A'],
                   ['Tasman','Internet Explorer 4.5','Mac OS 8-9','-','X'],
                   ['Tasman','Internet Explorer 5.1','Mac OS 7.6-9','1','C'],
                   ['Tasman','Internet Explorer 5.2','Mac OS 8-X','1','C'],
                   ['Misc','NetFront 3.1','Embedded devices','-','C'],
                   ['Misc','NetFront 3.4','Embedded devices','-','A'],
                   ['Misc','Dillo 0.8','Embedded devices','-','X'],
                   ['Misc','Links','Text only','-','X'],
                   ['Misc','Lynx','Text only','-','X'],
                   ['Misc','IE Mobile','Windows Mobile 6','-','C'],
                   ['Misc','PSP browser','PSP','-','C'],
                   ['Other browsers','All others','-','-','U']
               ];
                
                   $('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
                debugger;
                   $('#example').dataTable( {
                       "data": dataSet,
                       "columns": [
                           { "title": "Engine" },
                           { "title": "Browser" },
                           { "title": "Platform" },
                           { "title": "Version", "class": "center" },
                           { "title": "Grade", "class": "center" }
                       ]
                   } );
});


function docReady() {
    //prevent # links from moving to top
    $('a[href="#"][data-top!=true]').click(function (e) {
        e.preventDefault();
    });

    //notifications
    $('.noty').click(function (e) {
        e.preventDefault();
        var options = $.parseJSON($(this).attr('data-noty-options'));
        noty(options);
    });

    //chosen - improves select
    $('[data-rel="chosen"],[rel="chosen"]').chosen();

    //tabs
    $('#myTab a:first').tab('show');
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });


    //tooltip
    $('[data-toggle="tooltip"]').tooltip();

    //auto grow textarea
    $('textarea.autogrow').autogrow();

    //popover
    $('[data-toggle="popover"]').popover();

    //iOS / iPhone style toggle switch
    $('.iphone-toggle').iphoneStyle();

    //star rating
    $('.raty').raty({
        score: 4 //default stars
    });

    //uploadify - multiple uploads
    $('#file_upload').uploadify({
        'swf': 'misc/uploadify.swf',
        'uploader': 'misc/uploadify.php'
        // Put your options here
    });

    //gallery controls container animation
    $('ul.gallery li').hover(function () {
        $('img', this).fadeToggle(1000);
        $(this).find('.gallery-controls').remove();
        $(this).append('<div class="well gallery-controls">' +
            '<p><a href="#" class="gallery-edit btn"><i class="glyphicon glyphicon-edit"></i></a> <a href="#" class="gallery-delete btn"><i class="glyphicon glyphicon-remove"></i></a></p>' +
            '</div>');
        $(this).find('.gallery-controls').stop().animate({'margin-top': '-1'}, 400);
    }, function () {
        $('img', this).fadeToggle(1000);
        $(this).find('.gallery-controls').stop().animate({'margin-top': '-30'}, 200, function () {
            $(this).remove();
        });
    });


    //gallery image controls example
    //gallery delete
    $('.thumbnails').on('click', '.gallery-delete', function (e) {
        e.preventDefault();
        //get image id
        //alert($(this).parents('.thumbnail').attr('id'));
        $(this).parents('.thumbnail').fadeOut();
    });
    //gallery edit
    $('.thumbnails').on('click', '.gallery-edit', function (e) {
        e.preventDefault();
        //get image id
        //alert($(this).parents('.thumbnail').attr('id'));
    });

    //gallery colorbox
    $('.thumbnail a').colorbox({
        rel: 'thumbnail a',
        transition: "elastic",
        maxWidth: "95%",
        maxHeight: "95%",
        slideshow: true
    });

    //gallery fullscreen
    $('#toggle-fullscreen').button().click(function () {
        var button = $(this), root = document.documentElement;
        if (!button.hasClass('active')) {
            $('#thumbnails').addClass('modal-fullscreen');
            if (root.webkitRequestFullScreen) {
                root.webkitRequestFullScreen(
                    window.Element.ALLOW_KEYBOARD_INPUT
                );
            } else if (root.mozRequestFullScreen) {
                root.mozRequestFullScreen();
            }
        } else {
            $('#thumbnails').removeClass('modal-fullscreen');
            (document.webkitCancelFullScreen ||
                document.mozCancelFullScreen ||
                $.noop).apply(document);
        }
    });

    $('.btn-close').click(function (e) {
        e.preventDefault();
        $(this).parent().parent().parent().fadeOut();
    });
    $('.btn-minimize').click(function (e) {
        e.preventDefault();
        var $target = $(this).parent().parent().next('.box-content');
        if ($target.is(':visible')) $('i', $(this)).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        else                       $('i', $(this)).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
        $target.slideToggle();
    });
    $('.btn-setting').click(function (e) {
        e.preventDefault();
        $('#myModal').modal('show');
    });

}


//additional functions for data table
$.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
    return {
        "iStart": oSettings._iDisplayStart,
        "iEnd": oSettings.fnDisplayEnd(),
        "iLength": oSettings._iDisplayLength,
        "iTotal": oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage": Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
        "iTotalPages": Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
    };
}
$.extend($.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function (oSettings, nPaging, fnDraw) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function (e) {
                e.preventDefault();
                if (oSettings.oApi._fnPageChange(oSettings, e.data.action)) {
                    fnDraw(oSettings);
                }
            };

            $(nPaging).addClass('pagination').append(
                '<ul class="pagination">' +
                    '<li class="prev disabled"><a href="#">&larr; ' + oLang.sPrevious + '</a></li>' +
                    '<li class="next disabled"><a href="#">' + oLang.sNext + ' &rarr; </a></li>' +
                    '</ul>'
            );
            var els = $('a', nPaging);
            $(els[0]).bind('click.DT', { action: "previous" }, fnClickHandler);
            $(els[1]).bind('click.DT', { action: "next" }, fnClickHandler);
        },

        "fnUpdate": function (oSettings, fnDraw) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);

            if (oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if (oPaging.iPage <= iHalf) {
                iStart = 1;
                iEnd = iListLength;
            } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for (i = 0, iLen = an.length; i < iLen; i++) {
                // remove the middle elements
                $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                // add the new list items and their event handlers
                for (j = iStart; j <= iEnd; j++) {
                    sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                    $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
                        .insertBefore($('li:last', an[i])[0])
                        .bind('click', function (e) {
                            e.preventDefault();
                            oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                            fnDraw(oSettings);
                        });
                }

                // add / remove disabled classes from the static elements
                if (oPaging.iPage === 0) {
                    $('li:first', an[i]).addClass('disabled');
                } else {
                    $('li:first', an[i]).removeClass('disabled');
                }

                if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
                    $('li:last', an[i]).addClass('disabled');
                } else {
                    $('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
});
