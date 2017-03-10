define(['router'], function() {
	var app = angular.module("webapp", ['ui.router','ngCookies'])
		.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('login');
			$stateProvider
				.state("login", {
					url: "/login",
					controller: 'loginCtrl',
//					template: '<p>{{str}}</p>',
					templateUrl: 'login',
					resolve: {
						loadCtrl: ["$q", function($q) {
							var deferred = $q.defer();
							//异步加载controller／directive/filter/service
							require([
								'controller/loginCtrl'
							], function() {
								deferred.resolve();
							});
							return deferred.promise;
						}]
					}
				})
				.state("home", {
					url: "/home",
					controller: 'homeCtrl',
//					template: '<p>{{str}}</p>',
					templateUrl: 'home',
					resolve: {
						loadCtrl: ["$q", function($q) {
							var deferred = $q.defer();
							//异步加载controller／directive/filter/service
							require([
								'controller/homeCtrl'
							], function() {
								deferred.resolve();
							});
							return deferred.promise;
						}]
					}
				})
		}]);
	app.config(function($controllerProvider, $compileProvider, $filterProvider, $provide) {
		app.register = {
			//得到$controllerProvider的引用
			controller: $controllerProvider.register,
			//同样的，这里也可以保存directive／filter／service的引用
			directive: $compileProvider.directive,
			filter: $compileProvider.register,
			service: $provide.service
		};
	});
	return app;
})