define(['app'], function(app) {
	app.register
	.controller('loginCtrl', function($scope, $http, $state,$cookieStore) {
		$scope.login = function() {
			$http({
					method: "POST",
					url: "http://localhost:8082/CAS/user/login",
					data: $scope.user,
				})
				.then(function(resp) {
					$scope.data = resp.data;
					if(resp.data.code == 0) {
						$state.go('home');
						var expireDate = new Date();  
						expireDate.setDate(expireDate.getDate() + 1);                
						$cookieStore.put('user', resp.data, {'expires': expireDate.toUTCString()});
					}
				}, function(resp) {
					// 带有错误信息的resp
				});
		};
	});
})