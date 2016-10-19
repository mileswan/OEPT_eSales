/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/25
 * Description: Portfolio events handlers.
 */
var PortfolioEvents = function(){
	// initialize events function for all elements.
    var initPortfolioEvents = function() {
    	
    	$('a[href="#add-shopping-cart"]').click(function() {
    		$('span.product-id').text( $(this).parent().attr('id') );
	    });
    	$('button.add-shopcart-confirm').click(function() {
    		addProdToShopcart();
	    });
    	//Begin generate order directly functions
    	$('a.generate-order').click(function() {
    		var prod_price = $(this).parents('div.caption').find('span.product-price').text();
    		var prod_id = $(this).parent().attr('id');
    		
    		$('span.order-product-price').text(prod_price);
    		$('span.order-product-id').text(prod_id);
	    });
    	$('span.purchase-amount').text('0元');
    	$('.shop-quantity').find('input.spinner-input').keyup(function() {
    		var prod_price = $('span.order-product-price').text()*1;
    		var prod_quantity = $(this).val()*1;
    		$('span.purchase-amount').text( (prod_price * prod_quantity).toFixed(2) );
    	});
    	$('.shop-quantity').spinner({value: 1, step: 1, min: 0, max: 2000}).on( "change", function() {
    		$(this).find('input.spinner-input').keyup();
		} );
    	$('button.generate-order-confirm').click(function() {
    		generateOrder();
	    });
    	//End generate order directly functions
    	
    	$('a.add-to-favorite').click(function() {
    		var prodId = $(this).parents('div.caption').find('p.product-id').attr('id');
    		addItemToFav(prodId);
	    });
    }
    
  //Add product to user's shopping cart
    var addProdToShopcart = function() {
    	var productId = $("span.product-id").text();
    	
		$.ajax({
            type: "GET",
            url: "addToShopcart.do",
            data: {productId:productId},
            dataType: "text",
            success: function (result) {
            	if(result == 'success'){
            		$('.alert-success span').text("已成功添加到购物车！");
       	   	 	 	$('.alert-success').show();
            	}else{
            		$('.alert-danger span').text("添加到购物车失败！");
					$('.alert-danger').show();
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Add product to user's favorite list
    var addItemToFav = function(prodId) {
    	
		$.ajax({
            type: "GET",
            url: "../favorite/addItem.do",
            data: {prodId:prodId},
            dataType: "text",
            success: function (result) {
            	if(result == 'success'){
            		var str = '<p> <span> 已收藏<i class="fa fa-check"></i></span> </p>';
            		$('p.favorite-status-prod'+prodId).html(str);
            	}else{
            		$('.alert-danger span').text("添加到收藏夹失败！");
					$('.alert-danger').show();
            	}
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    //Generate order directly from portfolio
    var generateOrder = function() {
    	var prod_price = $('span.order-product-price').text()*1;
		var prod_quantity = $('.shop-quantity').find('input.spinner-input').val()*1;
		var prod_id = $('span.order-product-id').text();
    	
		$.ajax({
            type: "GET",
            url: "../order/generate.do",
            data: {prod_id:prod_id,
            	prod_price:prod_price,
            	prod_quantity:prod_quantity},
            dataType: "text",
            success: function (result) {
            	var responseJson = eval ("(" + result + ")");
            	window.location.href = '../order/details.do?id='+responseJson.order_id;
            },
            error: function(xhr, textStatus, thrownError) {
           	 alert(thrownError);
            }
        });
    }
    
	return {
		init: function(){
			
			initPortfolioEvents();
			
		},
		
	};

}();