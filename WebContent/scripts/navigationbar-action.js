/**
 * Author: mwan
 * Version: 1.0
 * Date: 2015/11/11
 * Description: logic to display navigation bar.
 */
var navigationBar = function(){
    
	return {
	
		activeHomeMenu: function(){
			
		    $('li.home-menu').addClass("active open");
		    $('li.home-menu span.arrow').addClass("open");
		},
		
        activePurchaseMenu: function(){
			
        	$('li.purchase-menu').addClass("active open");
        	$('li.purchase-menu span.arrow').addClass("open");
		},
		
        activeOrderMenu: function(){
			
        	$('li.order-menu').addClass("active open");
        	$('li.order-menu span.arrow').addClass("open");
		},
		
		activePOMenu: function(){
			
        	$('li.po-menu').addClass("active open");
        	$('li.po-menu span.arrow').addClass("open");
		},
		
		activeSOMenu: function(){
			
        	$('li.so-menu').addClass("active open");
        	$('li.so-menu span.arrow').addClass("open");
		},
		
		activeContractMenu: function(){
			
        	$('li.contract-menu').addClass("active open");
        	$('li.contract-menu span.arrow').addClass("open");
		},
		
		activeAccountMenu: function(){
			
        	$('li.account-menu').addClass("active open");
        	$('li.account-menu span.arrow').addClass("open");
		},
		
        activeServiceMenu: function(){
			
        	$('li.service-menu').addClass("active open");
        	$('li.service-menu span.arrow').addClass("open");
		},
		
		activeInventoryMenu: function(){
			
        	$('li.inventory-menu').addClass("active open");
        	$('li.inventory-menu span.arrow').addClass("open");
		},
		
		activePersonMenu: function(){
			
        	$('li.person-menu').addClass("active open");
        	$('li.person-menu span.arrow').addClass("open");
		},
		
		activeProductMenu: function(){
			
        	$('li.product-menu').addClass("active open");
        	$('li.product-menu span.arrow').addClass("open");
		},
		
		activeAddrAdminMenu: function(){
				
	        	$('li.addr-adminmenu').addClass("active open");
	        	$('li.addr-adminmenu span.arrow').addClass("open");
		},
		
		activeOrderAdminMenu: function(){
			
        	$('li.order-adminmenu').addClass("active open");
        	$('li.order-adminmenu span.arrow').addClass("open");
		},
		
		activeUserMenu: function(){

			$('li.user-menu').addClass("active open");
        	$('li.user-menu span.arrow').addClass("open");
		},
		
        activeSystemMenu: function(){
			
        	$('li.system-menu').addClass("active open");
        	$('li.system-menu span.arrow').addClass("open");
		},
		
        activeHelpMenu: function(){
			
        	$('li.help-menu').addClass("active open");
        	$('li.help-menu span.arrow').addClass("open");
		}
		
	};

}();