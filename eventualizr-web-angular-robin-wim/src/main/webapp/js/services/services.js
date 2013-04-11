angular.module('eventualizrApp', [], function($provide) {
  $provide.factory('meetings', ['$http', function(http){
	    	http.get('api/meetings')
	    	.success(function(data, status, headers, config){
				return data;
			})
			.error(function(){
				alert("backend not responding");
			}
			)
		}]
	);
});
