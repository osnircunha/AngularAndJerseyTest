/**
 * Created by osnircunha on 9/24/15.
 */
app.controller('loginController', function ($scope, $rootScope, notification, $http, $state) {

    $scope.cancel = function(){
        $scope.$dismiss('cancel');
    }

    $scope.submit = function () {
        if ($scope.username && $scope.password) {
            var user = { username : $scope.username, password : $scope.password };
            var req = {
                method: 'POST',
                url: 'rest/login',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: 'username=' + $scope.username + '&credential=' + btoa($scope.username + ':' + $scope.password)
            };
            $http(req).then(function (resp) {
                $scope.invalidCredentials = false;
                $scope.$close(user);
            }, function (resp) {
                notification.showError('Credenciais invalidas.');
                $scope.invalidCredentials = true;
            });
        } else {
            notification.showError('Por favor preencha nome e senha.');
            $scope.invalidCredentials = true;
        }
    };

    $scope.logout = function(){
        delete $rootScope.currentUser;
        $state.go('welcome');
    }

});