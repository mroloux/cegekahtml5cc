'use strict';

/* Directives */

angular.module('eventualizrApp.directives', [])
	.directive('appVersion',
		[ 'version', function(version) {
			return function(scope, elm, attrs) {
				elm.text(version);
			};
		} ])
	
	.directive('inputcomponent', function factory() {
		return {
			templateUrl : 'partials/components/inputcomponent.html',
			restrict : 'A',
			scope : {
				label:"@",
				guimodel:"="
			}
		};
	})
	.directive('datetimecomponent', function factory() {
		return {
			templateUrl : 'partials/components/datetimecomponent.html',
			restrict : 'A',
			scope : {
				label:"@",
				guimodel:"="
			}
		};
	});
