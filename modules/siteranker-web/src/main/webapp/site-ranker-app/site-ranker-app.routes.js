(function(angular) {
	"use strict";
	angular
		.module('siteRankerApp')
	    .config(['$locationProvider', '$routeProvider',
	    	function config($locationProvider, $routeProvider) {
	    		$locationProvider.hashPrefix('!');
	    		$routeProvider.
			        when('/sites/:siteUrl/history', {
			        	template: '<site-history></site-history>'
			        }).
			        when('/sites', {
			        	template: '<site-list></site-list>'
			        }).
			        otherwise('/sites');
				    }
	    ]);
})(window.angular);