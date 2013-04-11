describe('Eventualizr controllers', function() {
 
beforeEach(function(){
    this.addMatchers({
      toEqualData: function(expected) {
        return angular.equals(this.actual, expected);
      }
    });
  });

  describe('MeetingController_getMeetings', function(){
    var scope, ctrl, $httpBackend;

   beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('api/meetings').
            respond([{"id":0}]);
   
        scope = $rootScope.$new();
        ctrl = $controller(MeetingController, {$scope: scope});
      }));

    
    it('should call httpBackend', function() {
      $httpBackend.flush();
 
      expect(scope.meetings).toEqualData(
          [{"id":0}]);
    });
  });

  describe('MeetingController_getTalks', function(){
    var scope, ctrl, $httpBackend;

   beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('api/meetings').
            respond([{"id":1}]);
   
        $httpBackend.expectGET('api/meetings/1/talks').
            respond([{"id":0}]);
        scope = $rootScope.$new();
        ctrl = $controller(MeetingController, {$scope: scope});
      }));

    
    it('should call httpBackend', function() {
      ctrl.getTalks(scope.meetings[0]);
      
      $httpBackend.flush();
 
      

      expect(scope.meetings).toEqualData(
          [{"id":1,talks:[{"id":0}]}]);

    });
  });

});