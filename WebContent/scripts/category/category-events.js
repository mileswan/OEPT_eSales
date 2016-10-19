/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/16
 * Description: Product category events handlers.
 */
var CategoryEvents = function(){
	// initialize events function for all elements.
    var initCategoryEvents = function() {
    	
    	$('#new-cat-confirm').click(function() {
    		addCategory();
	    });
    	
    }
    
    //Generate categories list dynamically in product category dialog
    var loadProdCategoriesData = function() {
			
		$.ajax({
            type: "POST",
            url: "loadcat.do",
            data: "",
            dataType: "text",
            success: function (result) {	
            	var responseJson = eval ("(" + result + ")");
            	var cat = new Array();
            	var option = '';
            	
            	$("select.categories").append('<option value="1">无</option>');
            	for(var i=0;i<responseJson.length;i++){
            		var category = responseJson[i];
            		var catIndex = category.parentCatId;
                    if(category.hierlvl == "1"||category.hierlvl == "0"){
                    	option = '<option value="'+category.id+'">'+category.name+'</option>';
                    	$("select.categories").append(option);
                    }else{
                    	var parentCat = $("select.categories").find('option[value="'+category.parentCatId +'"]');
                    	var indent = '';
                    	for(var j=0;j<category.hierlvl*3;j++){
                    		indent = indent + '&nbsp;'
                    	}
                    	cat[catIndex] = '<option value="'+category.id+'">'+category.name+'</option>';
                    	parentCat.after(cat[catIndex]);
                    	$("select.categories").find('option[value="'+category.id +'"]').prepend(indent);
                    }
                }
            	
            	var prodcatid = $('input[name="parent_cat_id"]').val();
            	$("select.categories").find('option[value="'+ prodcatid +'"]').attr("selected",true);
            	//$('select.category-search').append($("select.categories").html());
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(textStatus);
            }
        });
    }
    
    var addCategory = function() {
		var name = $("div.modal-body input").val();
		var description = $("div.modal-body textarea").val();
		var parentCatId = $("div.modal-body option:selected").val();
		if(name==""||name==null){
			 $('.alert-success span').text("产品类别名称不能为空！");
  	 	  	 $('.alert-success').show();
	  	 	 setTimeout(function(){
	     		$('.alert-success').fadeOut();
	 			window.location.reload();
	  	 	 },2000);
		}else{
			$.ajax({
	            type: "POST",
	            url: "new_category.do",
	            data: {parentCatId:parentCatId,
	            	   name:name,
	            	   description:description},
	            dataType: "text",
	            success: function (result) {	
	            	var responseJson = eval ("(" + result + ")");
	            	if( responseJson.id ){
	            		$('.alert-success span').text("产品类别创建成功！");
	       	   	 	 	$('.alert-success').show();
	            	}else{
	            		$('.alert-danger span').text("产品类别创建失败，请重新操作！");
	            		$('.alert-danger').show();
	            	}
	            	setTimeout(function(){
	            		$('.alert-success').fadeOut();
	        			window.location.reload();
	        		},2000);
	            },
	            error: function(xhr, textStatus, thrownError) {
	           	 alert(thrownError);
	            }
	        });
		}
    }
    
	return {
		init: function(){
			
			initCategoryEvents();
			
		},
		
		loadProdCategoriesData: function(){
			loadProdCategoriesData();
		}
		
	};

}();