(function($) {
  var resetToolBar = function() {
    $('.tool_item').hover(function() { $(this).css('background-color','black'); }, 
       function() { $(this).css('background-color','transparent'); });
  }
  var resizeContentPane = function () {
    var width = $(window).width();
    var height = $(window).height();
    
    if ($.browser.msie && $.browser.version.substr(0,1)<7) {
      var bg = $('#bg');
      bg.css({'width':width, 'height':height});
    }
    var cp = $('#content_pane');
    var tp = $('#tool_pane');

    width = width - 60;
    height = height - 60;
    
    var css = {};
    css.width = 200;
    css.height = height - 12;
    css.left = width - 212 + 30;
    if (css.height < 300) css.height = 300;
    if (css.left < 472) css.left = 472;
    tp.css(css);

    css = {};
    css.width = width - 284;
    css.height = height - 42;
    if (css.height < 270) css.height = 270;
    if (css.width < 370) css.width = 370;
    cp.css(css);
  };
  /*
  var setBG = function(data) {
    var bg = $('#bg');
    var width = screen.width;
    var height = screen.height;
    var url = 'url(\'image.php?t=false&q=35&id='+data.id+'&w='+width+'&h='+height+'&o=crop&g=0.2\')';
    bg.css('background-image', url);
    if (typeof(data.attribution) !== 'undefined' && data.attribution.length > 0) {
       var attr = data.attribution;
       var site = null;
       if (typeof(data['attribution_site']) !== 'undefined' && data['attribution_site'].length > 0) {
          site = data['attribution_site'];
       }
       var html = attr;
       if (site != null) {
          html = '<a target="_blank" href="'+site+'">'+attr+' @ '+site+'</a>';
       }
       $('#attribution', bg).html('Image source: ' + html);
    }
  };
  */
  $(document).ready(function() {
    //$.getJSON('json_image.php', setBG);
    resizeContentPane();
    $(window).resize(resizeContentPane);
    //loadPage();
    resetToolBar();
    /*
    $(window).bind('hashchange', function() {
      loadPage();
    });
    */
  });
})(jQuery);