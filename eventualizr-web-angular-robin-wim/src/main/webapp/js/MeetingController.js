function MeetingController($scope, $http, AllMeetings){

	$scope.meetings = AllMeetings.success(function(data, status, headers, config){
			$scope.meetings = data;
		})
		.error(function(){
			alert("backend not responding");
		});
}