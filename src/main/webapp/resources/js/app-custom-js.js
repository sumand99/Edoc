// Document Ready fn
$(function () {
  $('.tooltip-btn').tooltip(); //tooltip initilize
  
  
  $.fn.loading = function(options){
      return this.each(function(){
              var _target = $(this), divId = _target.attr("id");

              //methods
              var methods = {
                      hide : function(){
                              _target.remove();
                      }
              };
              //default parameters
              var defaults = {
                      width: _target.width(),
                      height: _target.height(),
                      offset: _target.offset()
              };

              this.options = $.extend({}, defaults, options);
              var width = this.options.width,
                  height = this.options.height,
                      offset = this.options.offset,
                      offsetLeft = this.options.offset.left,
                      offsetTop = this.options.offset.top;
             
              //default parameters
              if(methods[options]){
                      methods[options].apply(this);
              }else{
                      var loadingDiv = $("<div/>", {
                              "class" : "loading",
                              "id" : divId + "LoadingDiv"
                      }).css({
                              "width" : width,
                              "height" : height,
                              "top" : offsetTop,
                              "left" : offsetLeft,
                      });

                      $("body").append(loadingDiv);
                     
              }
      });
}
//end of loading plugin
 
})

