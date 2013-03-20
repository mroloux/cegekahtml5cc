'use strict';

/* Controllers */

function MeetingListController($scope, Meeting) {
    $scope.meetings = Meeting.query();
    $scope.orderProp = 'datum';
    $scope.newMeeting = new Meeting();
    
    $scope.isAddNewMeetingFormVisible = false;
    $scope.showAddNewMeetingForm = function() {
    	$scope.isAddNewMeetingFormVisible = true;
    };
    
    $scope.update = function(newMeeting) {
    	newMeeting.$save({}, function(data) {
    		$scope.meetings = Meeting.query();
    		$scope.cancelNewMeeting();
        }, function(data) {
            console.log(data);               
        });
    };

    $scope.cancelNewMeeting = function() {
    	$scope.newMeeting = new Meeting();
    	$scope.isAddNewMeetingFormVisible = false;
    };
}
MeetingListController.$inject = ['$scope', 'Meeting'];


function MeetingDetailController($scope, $routeParams, Meeting, Talk) {
    $scope.talks = Talk.query({meetingId: $routeParams.meetingId});
    $scope.meeting = Meeting.get({meetingId: $routeParams.meetingId});
}
MeetingDetailController.$inject = ['$scope', '$routeParams', 'Meeting', 'Talk'];