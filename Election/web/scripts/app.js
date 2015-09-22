/**
 * Created by osnircunha on 8/14/15.
 */
var app = angular.module('ElectionApp',
    ['ui.router', 'directives.footer', 'directives.header', 'directives.candidate.list', 'directives.loading', 'ngAnimate', 'ui.bootstrap']);
app.run(function($rootScope, $state, loginModal, notification, $state){
    // init content here
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
        var requireLogin = toState.data.requireLogin;
        $rootScope.page = toState.name;

        if (requireLogin && typeof $rootScope.currentUser === 'undefined') {
            event.preventDefault();

            loginModal()
                .then(function () {
                    return $state.go(toState.name, toParams);
                })
                .catch(function () {
                    if($state.current.name == 'welcome'){
                        $state.forceReload();
                    } else {
                        return $state.go('welcome');
                    }
                });
        }
    });
});

app.service('loginModal', function ($modal, $rootScope) {

    function assignCurrentUser (user) {
        $rootScope.currentUser = user;
        return user;
    }

    return function() {
        var instance = $modal.open({
            templateUrl: 'pages/login.html',
            backdrop: 'static',
            keyboard: false,
            size: 'sm',
            controller: 'loginController'
        })

        return instance.result.then(assignCurrentUser);
    };

});

app.factory('notification', function () {
    return {
        showError : function (msg) {
            new PNotify({
                title: 'Erro:',
                text: msg,
                type: 'error',
                buttons: {
                    sticker: false
                }
            })
        },
        showSuccess : function (msg) {
            new PNotify({
                title: 'Sucesso:',
                text: msg,
                type: 'success',
                buttons: {
                    sticker: false
                }
            })
        }
    };
});

app.controller('homeController', function($rootScope, $scope, $http, notification) {
    $scope.saveCandidate = function () {
        try {
            if ($scope.candidate.name && $scope.candidate.votes) {
                var req = {
                    method: 'POST',
                    url: 'rest/candidates',
                    data: $scope.candidate
                };
                $http(req).then(function (resp) {
                    notification.showSuccess('Candidato salvo.');
                }, function (resp) {
                   throw resp;
                });
            } else {
                notification.showError('Erro ao salvar candidato.');
            }
        }catch(err){
            notification.showError('Erro ao salvar candidato. ' + err.message);
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
            $rootScope.candidateDetail = resp.data;
        }, function (resp) {
            notification.showError('Erro ao recuperar candidato.');
        });
    }
    $scope.open = function(){
        console.log('damn');
    }
    $scope.listCandidates();
});

app.controller('candidatesController', function($rootScope, $scope, $stateParams){
    $scope.getCandidate($stateParams.candidateid);
});


app.controller('loginController', function ($scope, $rootScope, notification, $http) {

    $scope.cancel = function(){
        $scope.$dismiss('cancel');
    }

    $scope.submit = function () {
        if ($scope.username && $scope.password) {
            var user = { "username" : $scope.username, "password" : $scope.password };
            var req = {
                method: 'POST',
                url: 'rest/login',
                data: user
            };
            $http(req).then(function (resp) {
                $scope.invalidCredentials = false;
                $scope.$close(resp.data);
                $rootScope.loggedUser = resp.data;
            }, function (resp) {
                throw resp;
            });
        } else {
            notification.showError('Por favor preencha nome e senha.');
            $scope.invalidCredentials = true;
        }
    };

});


app.config(function ($stateProvider, $urlRouterProvider, $locationProvider, $provide) {
    //
    // For any unmatched url, redirect to /state1
    $urlRouterProvider.otherwise("/welcome");

    $stateProvider
        .state('welcome', {
            url: "/welcome",
            templateUrl: "pages/customers.html",
            data: {
                requireLogin: false
            }
        })
        .state('home', {
            url: "/home",
            templateUrl: "pages/home.html",
            controller: 'homeController',
            data: {
                requireLogin: true
            }
        })
        .state('candidate', {
            url: "/candidate/:candidateid",
            views: {
                '@' : {
                    templateUrl: "pages/about.html",
                    controller: "candidatesController"
                }
            },
            data: {
                requireLogin: true
            }
        })
        .state('candidate.detail',{
            url: "/detail",
            views: {
                '@' : {
                    template: "<h1>Well, we don't have more details about {{candidateDetail.name}}</h1>"
                }
            },
            data: {
                requireLogin: true
            }
        })
//      $locationProvider.html5Mode(true);

        $provide.decorator('$state', function($delegate, $stateParams) {
            $delegate.forceReload = function() {
                return $delegate.go($delegate.current, $stateParams, {
                    reload: true,
                    inherit: false,
                    notify: true
                });
            };
            return $delegate;
        });

});