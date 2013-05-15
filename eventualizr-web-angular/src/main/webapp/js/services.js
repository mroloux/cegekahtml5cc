'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('eventualizrApp.services', []).
  value('version', '0.1');

angular.module('eventualizrApp.services').factory('Meeting', ['$resource', function($resource){
	var Meeting = $resource('api/meetings/:meetingId', {meetingId: '@meetingId'});
	
	return Meeting;
}]);

angular.module('eventualizrApp.services').factory('Talk', ['$resource', function($resource){
	var Talk = $resource('api/meetings/:meetingId/talks/:talkId', {meetingId: '@meetingId', talkId: '@talkId'});
	
	return Talk;
}]);