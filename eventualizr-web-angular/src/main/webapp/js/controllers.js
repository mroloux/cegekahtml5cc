'use strict';

/* Controllers */

function MeetingListController($scope, Meeting, $modal) {
    $scope.viaService = function() {
        // do something
        var modal = $modal({
            template: 'partials/createMeeting.html',
            show: true,
            backdrop: 'static',
            scope: $scope
        });
    };

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
            console.log(data);               
        });
    };
    function createDateFromDateTimePickers(datum){
    	datum.date.setHours(datum.time.split(':')[0]);
    	datum.date.setMinutes(datum.time.split(':')[1]);
    	return datum.date.toJSON().replace('Z','');
    }
}
MeetingListController.$inject = ['$scope', 'Meeting', '$modal'];


function MeetingDetailController($scope, $routeParams, $location, Meeting, Talk) {
    $scope.talks = Talk.query({meetingId: $routeParams.meetingId});
    $scope.meeting = Meeting.get({meetingId: $routeParams.meetingId});
   
    $scope.remove = function(meeting) {
    	meeting.$delete({meetingId:meeting.id}, function() {
    		$scope.meetings = Meeting.query();
    		$location.path('/meetings');
    	});
    };
}
MeetingDetailController.$inject = ['$scope', '$routeParams', '$location', 'Meeting', 'Talk'];

/** translations */
function AppController($scope, i18n, $strapConfig, $location) {
    window.scope =  $scope;   // watch $scope in firebug

    $scope.setLanguage = function(lang, $event) {
        i18n.setLanguage(lang);
        $event.preventDefault();
        $strapConfig.datepicker.language = lang;
        $location.path($location.path());
    };

    $scope.languages = i18n.languages;
}
AppController.$inject = ['$scope', 'i18n', '$strap.config', '$location'];