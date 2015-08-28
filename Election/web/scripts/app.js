/**
 * Created by osnircunha on 8/14/15.
 */
(function(angular) {
    'use strict';

angular.module('ElectionApp', ['ui.router', 'ngAnimate'])

.controller('candidateController', function ($scope, $http) {
    this.name = '';

    this.addCandidate = function () {
        var req = {
            method: 'POST',
            url: 'candidates/post',
            data: {test: 'test'}
        };
        $http.get("/candidates/get?name=" + this.name).success(function (response) {
            $scope.result = response;
        });
        this.name = '';
    }

    this.listCandidate = function () {
        $http.get("/candidates/post?name=" + this.name).success(function (response) {
            $scope.result = response;
        });
        this.name = '';
    }
})
.controller('homeController', function($rootScope){
    $rootScope.page = 'home';
})
.controller('customersController', function($rootScope){
    $rootScope.page = 'customers';
})
.controller('contactController', function($rootScope){
    $rootScope.page = 'contact';
})
.controller('aboutController', function($rootScope){
    $rootScope.page = 'about';
})
.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/home");

    $stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "pages/home.html",
            controller: 'homeController'
        })
        .state('customers', {
            url: "/customers",
            templateUrl: "pages/customers.html",
            controller: 'customersController'
        })
        .state('contact', {
            url: "/contact",
            templateUrl: "pages/contact.html",
            controller: 'contactController'
        })
        .state('about', {
            url: "/about",
            templateUrl: "pages/about.html",
            controller: 'aboutController'
        });
//        $locationProvider.html5Mode(true);

});
})(window.angular);

$(document).ready(function() {
// Hidde nav when is on mobile and onclick is performed
    $('.navbar-nav').on('click', function () {
        if ($('.navbar-header .navbar-toggle').css('display') != 'none') {
            $(".navbar-header .navbar-toggle").trigger("click");
        }
    });

});