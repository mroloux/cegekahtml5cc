function MeetingController($scope, $http){

	$http.get('api/meetings').success(function(data, status, headers, config){
		$scope.meetings = data;
	})
	.error(function(){
		alert("backend not responding");
	}
	);

	$scope.getTalks = function (meeting){
		$http.get('api/meetings/'+meeting.id+'/talks')
		.success(function(data, status, headers, config){
			meeting.talks = data;
		})
		.error(function(){
			alert("backend not responding");
		}
		);
	}

	

}