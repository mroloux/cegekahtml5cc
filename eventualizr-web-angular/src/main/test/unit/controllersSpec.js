'use strict';

/* jasmine specs for controllers go here */

describe('MeetingListController', function(){
  var scope;
  var meeting;
  beforeEach(angular.mock.module('eventualizrApp'));

  beforeEach(angular.mock.inject(function($rootScope, $controller) {
    meeting = {
      query: jasmine.createSpy('query')
    };
    scope = $rootScope.$new();
    

    $controller(MeetingListController, {
      $scope: scope,
      Meeting: meeting
    });
  }));

});
