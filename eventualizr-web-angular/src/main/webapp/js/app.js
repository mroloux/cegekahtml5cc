'use strict';


// Declare app level module which depends on filters, and services
angular.module('eventualizrApp', ['ngResource', 'eventualizrApp.filters', 'eventualizrApp.services', 'eventualizrApp.directives', '$strap.directives', 'ngI18n']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/home', {templateUrl: 'partials/home.html', controller: MeetingListController});
        $routeProvider.when('/meetings', {templateUrl: 'partials/meeting-list.html', controller: MeetingListController});
        $routeProvider.when('/meetings/:meetingId', {templateUrl: 'partials/meeting-detail.html', controller: MeetingDetailController});
	$routeProvider.when('/meetings/:meetingId/talks/:talkId', {templateUrl: 'partials/talk-detail.html', controller: TalkDetailController});
        $routeProvider.otherwise({redirectTo: '/home'});
    }])
    .value('ngI18nConfig', {
        defaultLocale: 'en',
        supportedLocales: ['en', 'nl']
    }).value('$strap.config', {
        datepicker: {
            language: 'en'
        }
    });
