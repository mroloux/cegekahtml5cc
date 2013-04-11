getTalks = function (meeting){
		$http.get('api/meetings/'+meeting.id+'/talks')
		.success(function(data, status, headers, config){
			meeting.talks = data;
		})
		.error(function(){
			alert("backend not responding");
		}
		);
	}