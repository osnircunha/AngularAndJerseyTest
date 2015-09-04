/**
 * Created by osnircunha on 9/03/15.
 */
angular.module('directives.candidate.list', [])
    .directive('candidateList', function () {
        return {
            restrict: 'E',
            scope: {
                candidateList: '='
            },templateUrl: 'pages/directives/candidates-list.html'
        };
    });