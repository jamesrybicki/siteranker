(function(angular) {
	"use strict";
	angular
	    .module('siteRankerApp')
		.component('siteList', {
			  templateUrl: 'site-ranker-app/site-list/site-list.template.html',
			  controller: 'SiteListController'
	});
	
})(window.angular);