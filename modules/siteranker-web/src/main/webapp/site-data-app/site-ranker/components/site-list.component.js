(function(angular) {
	"use strict";
	angular
	    .module('siteRanker')
		.component('siteList', {
			  templateUrl: 'site-data-app/site-ranker/sites/site-list.template.html',
			  controller: 'SiteListController'
	});
	
})(window.angular);