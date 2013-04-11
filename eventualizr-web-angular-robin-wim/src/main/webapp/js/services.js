'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('eventualizrApp.services', []).
  value('version', '0.1');

angular.module('eventualizrApp.services').factory('AllMeetings', ['$http', function($http){
	var AllMeetings = $http.get('api/meetings');
	return AllMeetings;
}]);