'use strict';

/* Controllers */

function MeetingListController($scope, Meeting) {
	$scope.resetNewMeeting = function() {
    	$scope.newMeeting = new Meeting();
    };
    
	$scope.meetings = Meeting.query();
    $scope.orderProp = 'datum';
    $scope.resetNewMeeting();
    
    $scope.update = function(newMeeting) {
    	newMeeting.$save({}, function(data) {
    		$scope.meetings = Meeting.query();
    		$scope.resetNewMeeting();
        }, function(data) {
            console.log(data);               
        });
    };
    $scope.remove = function(meeting) {
    	meeting.$delete({meetingId:meeting.id}, function() {
    		$scope.meetings = Meeting.query();
    	});
    };
}
MeetingListController.$inject = ['$scope', 'Meeting'];


function MeetingDetailController($scope, $routeParams, Meeting, Talk) {
    $scope.talks = Talk.query({meetingId: $routeParams.meetingId});
    $scope.meeting = Meeting.get({meetingId: $routeParams.meetingId});
}
MeetingDetailController.$inject = ['$scope', '$routeParams', 'Meeting', 'Talk'];