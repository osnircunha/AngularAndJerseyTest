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
                headers: {'Content-Type': 'application/x-www-form-urlencoded',
                          'X-SAML': 'SAMLRequest:IwPnJynT6rioPQRuoOq2vj+lEM/xvDy+ZXkRR7dxGlk='},
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

    //var loggedButton = angular.element('#logged-user');
    angular.element('ul.nav li.dropdown').hover(function() {
        angular.element(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(200);
    }, function() {
        angular.element(this).find('.dropdown-menu').stop(true, true).delay(100).fadeOut(200);
    });

});