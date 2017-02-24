(function(angular) {
	"use strict";
	angular
		.module('siteCore')
		.factory('Site', ['$resource',
			function($resource) {
				return $resource('/rest/sites/:siteId.json', {}, {
					'get': {
						method: 'GET'
					},
					'availablePeriods': {
						method: 'GET', 
						isArray: true, 
						url: '/rest/sites/availablePeriods'
					},
					'query': { 
						method: 'GET', 
					    isArray: true, 
					    url: '/rest/sites.json?periodEndDate=:periodEndDate&page=0&size=:limit'
					},
					'history': {
						method: 'GET',
						isArray: true,
						url: '/rest/sites/:siteUrl/history'
					}
				});
			}
		]);
})(window.angular);
