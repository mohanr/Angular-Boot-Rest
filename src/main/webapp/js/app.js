'use strict';


var module = angular.module('librarysystem',['ngRoute','librarysystemcontroller','libraryServices'])
.config(['$routeProvider',
	function($routeProvider) {
 		$routeProvider.
			when('/data/:bookId', {
				templateUrl: 'partials/data.html',
				controller: 'BookAdditionCtrl'
			}).
			when('/data', {
				templateUrl: 'partials/data.html',
				controller: 'BookAdditionCtrl'
			}).
			when('/edit', {
				templateUrl: 'partials/data.html',
				controller: 'EmptyCtrl'
			}).
			when('/add', {
				templateUrl: 'partials/data.html',
				controller: 'BookAdditionCtrl'
			}).
			otherwise({
				redirectTo: '/'
			});
	}
]);