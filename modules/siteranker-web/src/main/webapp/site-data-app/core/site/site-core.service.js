(function(angular) {
	"use strict";
	angular
		.module('siteCore')
		.factory('Site', ['$resource',
			function($resource) {
				return $resource('/rest/sites/:id.json', {}, {
					'get': {method: 'GET'},
					'weeksAvailable': {method: 'GET', isArray: true, url: '/rest/sites/weeks'},
					'query': { method: 'GET', isArray: true, url: '/rest/sites.json?periodEndDate=:periodEndDate&page=0&size=:limit' }
				});
			}
		]);
})(window.angular);
