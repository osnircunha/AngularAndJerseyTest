/**
 * Created by osnircunha on 9/03/15.
 */
angular.module('directives.loading', [])
    .directive('loadingView', function ($timeout, $http, $rootScope) {
        return {
            restrict: 'E',
            templateUrl: 'pages/directives/loading.html',
            link: function(scope, element, attrs, controllers){
                $timeout(function () {
                    $http({
                        method: 'GET',
                        url: '/rest/candidates',
                    }).then(function (resp) {
                        $rootScope.candidates = resp.data;
                        scope.candidate = {name: 'oi', id: 777, votes: 9};
                        element.fadeOut( "slow" );
                    }, function (resp) {
                        console.error('Erro ao listar candidatos.');
                        element.fadeOut( "slow" );
                    });
                }, 2000);
            },
            controller: 'homeController'
        };
    });