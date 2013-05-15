'use strict';

/* Controllers */

function createDateFromDateTimePickers(datum){
	datum.date.setHours(datum.time.split(':')[0]);
	datum.date.setMinutes(datum.time.split(':')[1]);
	return datum.date.toJSON().replace('Z','');
}

function MeetingListController($scope, Meeting) {
	$scope.resetNewMeeting = function() {
    	$scope.newMeeting = new Meeting();
    	$scope.startdate = new Object();
    	$scope.startdate.date = null;
    	$scope.startdate.time = "00:00";
    	$scope.enddate = new Object();
    	$scope.enddate.date = null;
    	$scope.enddate.time = "00:00";
    };
    
	$scope.meetings = Meeting.query();
    $scope.orderProp = 'datum';
    $scope.resetNewMeeting();
    
    $scope.update = function(newMeeting, startdate, enddate) {
    	newMeeting.start = createDateFromDateTimePickers(startdate);
    	newMeeting.end = createDateFromDateTimePickers(enddate);
    	newMeeting.$save({}, function(data) {
    		$scope.meetings = Meeting.query();
    		$scope.resetNewMeeting();
        }, function(data) {
        	$scope.foutmeldingen = [{type:'error', title:'Error', content:"Something went wrong... Please try again."}];               
        });
    };
}
MeetingListController.$inject = ['$scope', 'Meeting'];


function MeetingDetailController($scope, $routeParams, $location, Meeting, Talk) {
    $scope.talks = Talk.query({meetingId: $routeParams.meetingId});
    $scope.meeting = Meeting.get({meetingId: $routeParams.meetingId});
   
    $scope.remove = function(meeting) {
    	meeting.$delete({meetingId:meeting.id}, function() {
    		$scope.meetings = Meeting.query();
    		$location.path('/meetings');
    	});
    };
    
    
    $scope.resetNewTalk = function() {
    	$scope.newTalk = new Talk();
    	$scope.startdate = new Object();
    	$scope.startdate.date = null;
    	$scope.startdate.time = "00:00";
    	$scope.enddate = new Object();
    	$scope.enddate.date = null;
    	$scope.enddate.time = "00:00";
    };
    
    $scope.resetNewTalk();
    
    $scope.update = function(newTalk, startdate, enddate) {
    	newTalk.till = createDateFromDateTimePickers(startdate);
    	newTalk.from = createDateFromDateTimePickers(enddate);
    	newTalk.$save({meetingId: $scope.meeting.id}, function(data) {
    		$scope.talks = Talk.query({meetingId: $scope.meeting.id});
    		$scope.resetNewTalk();
    		$scope.alerts = [{type:'success', title:'Talk was successfully saved', content:""}];
        }, function(data) {
        	$scope.alerts = [{type:'error', title:'Error', content:"Something went wrong... Please try again."}];               
        });
    };
    
}
MeetingDetailController.$inject = ['$scope', '$routeParams', '$location', 'Meeting', 'Talk'];

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