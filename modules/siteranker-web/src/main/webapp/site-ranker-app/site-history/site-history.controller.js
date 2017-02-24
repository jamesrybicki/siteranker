(function(angular) {
	"use strict";
	angular
        .module('siteRankerApp')
	  	.controller('SiteHistoryController', ['$filter', 'Site', '$routeParams', SiteHistoryController]);
	  
		function SiteHistoryController($filter, Site, $routeParams) {
			
		    var ctrl = this;
		    ctrl.orderProp = 'totalVisits';
		    ctrl.reverse = true;
		    ctrl.siteUrl = $routeParams.siteUrl;
		    
		    var sites = Site.history({siteUrl: $routeParams.siteUrl}, function() {
		    	ctrl.sites = $filter('orderBy')(sites, ctrl.orderProp, ctrl.reverse);
		    })
		    
		    ctrl.sortBy = function(orderProp) {
		        ctrl.reverse = (orderProp !== null && ctrl.orderProp === orderProp) ? !ctrl.reverse : false;
		        ctrl.orderProp = orderProp;
		        ctrl.sites = $filter('orderBy')(ctrl.sites, ctrl.orderProp, ctrl.reverse);
		    };

		};
})(window.angular);
