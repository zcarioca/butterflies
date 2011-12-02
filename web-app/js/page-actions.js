(function($) {
  /*
   * Transforms a text input field into a simple search field.
   * renderElement = the element to store the content into
   * default element = the element to show when no data is returned
   * settings = basic settings
   * callback = a callback function
   */
  $.fn.termSearch = function(renderElement, defaultElement, settings, callback) {
     var render = $(renderElement);
     var defarea = $(defaultElement);
     
     settings = $.extend({
        controller: 'lepidoptera',
        action: 'search',
        minChars: 3
     }, settings || {});
     
     this.each(function() {
        var self = $(this);
        
        self.bind('keyup', function() {
           var term = self.val();
           
           var url = settings.controller + '/' +
              settings.action + '?' +
              self.attr('name') + '=';
              
           if (term.length < settings.minChars) {
              defarea.show();
              render.hide();
           } else {
              render.load(url+term, function() {
                 defarea.hide();
                 render.show();
                 if (callback) {
                    callback();
                 }
              });
           }
        });
     });
     return this;
  };
  
  /*
   * Creates a toggle feature to roll another element up and down.
   */
  $.fn.sliderToggle = function(query, settings) {
     var element = $(query);
     
     settings = $.extend({
        easing: 'swing',
        duration: 'fast'
     }, settings || {});
     
     this.each(function() {
        var self = $(this);
        
        self.bind('click', function() {
           if (element.is(':hidden')) {
              element.slideDown(settings.duration, settings.easing, function() {
                 self.removeClass('__show');
                 self.addClass('__hide');
              });
           } else {
              element.slideUp(settings.duration, settings.easing, function() {
                 self.removeClass('__hide');
                 self.addClass('__show');
              });
           }
        });
        if (element.is(':hidden')) {
           self.addClass('__show');
        } else {
           self.addClass('__hide');
        }
     });
     return this;
  };
})(jQuery);