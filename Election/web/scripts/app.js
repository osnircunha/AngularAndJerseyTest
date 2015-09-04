/**
 * Created by osnircunha on 8/14/15.
 */
var app = angular.module('ElectionApp', ['ui.router', 'directives.footer', 'directives.header', 'directives.candidate.list']);

app.controller('candidateController', function ($scope, $http) {
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
});
app.controller('homeController', function($rootScope, $scope, $http, $stateParams){
    $rootScope.page = 'home';

    $http({
        method: 'GET',
        url: '/rest/candidates',
    }).then(function (resp) {
        $scope.candidates = resp.data;
    }, function (resp) {
        console.log(resp);
    });

    $scope.userName = 'user ' + $stateParams.id;
});
app.controller('customersController', function($rootScope){
    $rootScope.page = 'customers';
});
app.controller('contactController', function($scope, $rootScope, $http){
    $rootScope.page = 'contact';

    angular.element('#sendForm').click(function () {
        var req = {
            method: 'POST',
            url: '/rest/candidates',
            headers: {
                'Content-Type': 'application/json'
            },
            data: { name: 'rebeca', votes: 10 }
        }

        $http(req).then(function (resp) {
            console.log(resp);
        }, function (resp) {
            console.log(resp);
        });
    });
});
app.controller('aboutController', function($rootScope){
    $rootScope.page = 'about';
});
app.config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/home");

    $stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "pages/home.html",
            controller: 'homeController'
        })
        .state('home.candidate',{
            url: "/:id",
            views: {
                '@' : {
                    template: "<h1>{{userName}}</h1>",
                    controller: 'homeController'
                }
            }
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