define(['app'],function(app){
    app.register
    .controller('homeCtrl', function($scope,$cookieStore, $state){
    	// 获取 cookie
    	var user = $cookieStore.get('user');
    	if(user == undefined){
    		$state.go("login");
    	}else{
    		$scope.user = user.data;
    	}
    });
    app.register
    .controller('logoutCtrl', function($scope,$cookieStore,$state){
    	//用户登出
    	$scope.logout = function(user){
    		//清理cookie
      		$cookieStore.remove(user);
      		$state.go("login");
    	};
    });
})