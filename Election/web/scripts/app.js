/**
 * Created by osnircunha on 8/14/15.
 */
var app = angular.module('ElectionApp', ['ui.router', 'directives.footer', 'directives.header', 'directives.candidate.list']);

app.factory('notification', function () {
    return {
        showError : function (msg) {
            new PNotify({
                title: 'Erro:',
                message: msg,
                type: 'error',
                buttons: {
                    sticker: false
                }
            })
        },
        showSuccess : function (msg) {
            new PNotify({
                title: 'Sucesso:',
                message: msg,
                type: 'success',
                buttons: {
                    sticker: false
                }
            })
        }
    };
});

app.controller('homeController', ['$rootScope', '$scope', '$http', 'notification',function($rootScope, $scope, $http, notification) {
    $rootScope.page = 'home';
    $scope.saveCandidate = function () {
        if ($scope.candidate.name && $scope.candidate.votes) {
            var req = {
                method: 'POST',
                url: 'rest/candidates',
                data: $scope.candidate
            };
            $http(req).then(function (resp) {
                notification.showSuccess('Candidato salvo.');
            }, function (resp) {
                notification.showError('Erro ao salvar candidato.');
            });
        } else {
            console.error('missing values');
        }
    },
    $scope.listCandidates = function () {
        $http({
            method: 'GET',
            url: '/rest/candidates',
        }).then(function (resp) {
            $rootScope.candidates = resp.data;
        }, function (resp) {
            notification.showError('Erro ao listar candidatos.');
        });
    },
    $scope.getCandidate = function(id){
        $http({
            method: 'GET',
            url: '/rest/candidates?id=' + id
        }).then(function (resp) {
            $scope.candidateDetail = resp.data;
        }, function (resp) {
            notification.showError('Erro ao recuperar candidato.');
        });
    }
}]);
app.controller('customersController', function($rootScope){
    $rootScope.page = 'customers';
});

app.controller('contactController', function($scope, $rootScope, $http){
    $rootScope.page = 'contact';
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
                'bla@home' : {
                    templateUrl: "pages/about.html",
                }
            }
        })
        .state('home.candidate.detail',{
            url: "/detail",
            views: {
                'detail@home.candidate' : {
                    template: "<h1>{{candidateDetail.name}} details here</h1>"
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