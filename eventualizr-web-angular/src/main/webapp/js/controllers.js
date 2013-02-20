'use strict';

/* Controllers */

function MeetingListController($scope, Meeting) {
    $scope.meetings = Meeting.query();
    $scope.orderProp = 'datum';
}
MeetingListController.$inject = ['$scope', 'Meeting'];


function MeetingDetailController($scope, $routeParams, Talk) {
    $scope.talks = Talk.query({meetingId: $routeParams.meetingId});
}
MeetingDetailController.$inject = ['$scope', '$routeParams', 'Talk'];