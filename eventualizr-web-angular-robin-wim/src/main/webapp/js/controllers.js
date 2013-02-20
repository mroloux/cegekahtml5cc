'use strict';

/* Controllers */

function MeetingListController($scope, Meeting) {

    $scope.meetings = Meeting.query();

    $scope.orderProp = 'datum';
}
MeetingListController.$inject = ['$scope', 'Meeting'];

function MyCtrl1() {}
MyCtrl1.$inject = [];


function MyCtrl2() {
}
MyCtrl2.$inject = [];
