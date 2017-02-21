(function(angular) {
	"use strict";
	angular
		.module('siteDataApp')
	    .config(['$locationProvider', '$routeProvider',
	    	function config($locationProvider, $routeProvider) {
	    		$locationProvider.hashPrefix('!');
	    		$routeProvider.
			        when('/sites', {
			        	template: '<site-list></site-list>'
			        }).
			        when('/sites/:siteId', {
			        	template: '<site-detail></site-detail>'
			        }).
			        otherwise('/sites');
				    }
	    ]);
})(window.angular);