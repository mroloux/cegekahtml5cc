'use strict';


// Declare app level module which depends on filters, and services
angular.module('eventualizrApp', ['ngResource', 'eventualizrApp.filters', 'eventualizrApp.services', 'eventualizrApp.directives', '$strap.directives']).
  config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {templateUrl: 'partials/home.html', controller: MeetingListController});
	$routeProvider.when('/meetings', {templateUrl: 'partials/meeting-list.html', controller: MeetingListController});
	$routeProvider.when('/meetings/:meetingId', {templateUrl: 'partials/meeting-detail.html', controller: MeetingDetailController});
    $routeProvider.otherwise({redirectTo: '/home'});
  }]);
