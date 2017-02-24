(function(angular) {
	"use strict";
	angular
        .module('siteRankerApp')
	  	.controller('SiteListController', ['$filter', 'Site', SiteListController]);
	  
		function SiteListController($filter, Site) {
		    var ctrl = this;
		    ctrl.orderProp = 'totalVisits';
		    ctrl.reverse = true;
		    var weeks = Site.availablePeriods(function() {
		    	ctrl.weeks = $filter('orderBy')(weeks, 'endDate');
		    	ctrl.week = weeks[weeks.length -1];
			    var sites = Site.query({limit: 5, periodEndDate: ctrl.week.endDate}, function() {
				    ctrl.sites = $filter('orderBy')(sites, ctrl.orderProp, ctrl.reverse);
			    });
		    });
		    ctrl.update = function() {
		    	var sites = Site.query({limit: 5, periodEndDate: ctrl.week.endDate}, function() {
		    		ctrl.sites = $filter('orderBy')(sites, ctrl.orderProp, ctrl.reverse);
		    	});
		    };
		    ctrl.sortBy = function(orderProp) {
		        ctrl.reverse = (orderProp !== null && ctrl.orderProp === orderProp) ? !ctrl.reverse : false;
		        ctrl.orderProp = orderProp;
		        ctrl.sites = $filter('orderBy')(ctrl.sites, ctrl.orderProp, ctrl.reverse);
		    };
		};
		
		// SiteListController.$inject = ['$filter', 'Site'];
		
})(window.angular);

