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


/** translations */
function AppController($scope, localize) {
    window.scope =  $scope;   // watch $scope in firebug
    $scope.People = [{FirstName:"Jim", LastName:"Lavin", Email:"jlavin@jimlavin.net", Bio:"Creator and Host of Coding Smackdown TV"}];

    $scope.localize = localize;

    $scope.setEnglishLanguage = function() {
        localize.setLanguage('en');
    };

    $scope.setDutch = function() {
            localize.setLanguage('nl');
        };
}
AppController.$inject = ['$scope', 'localize'];