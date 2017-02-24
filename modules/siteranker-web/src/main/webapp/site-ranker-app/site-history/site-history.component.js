(function(angular) {
	"use strict";
	angular
	    .module('siteRankerApp')
		.component('siteHistory', {
			  templateUrl: 'site-ranker-app/site-history/site-history.template.html',
			  controller: 'SiteHistoryController'
	});
})(window.angular);
