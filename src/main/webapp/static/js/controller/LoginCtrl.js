siteApp.controller('LoginCtrl',[
    '$scope','$rootScope','$http', '$location','$cookieStore','UserLoginService',
    function($scope, $rootScope, $http, $location,$cookieStore,UserLoginService) {
        $scope.rememberMe = false;

        $scope.login = function() {
            UserLoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
                var authToken = authenticationResult.token;
                $rootScope.authToken = authToken;
                if ($scope.rememberMe) {
                    $cookieStore.put('authToken', authToken);
                }
                UserLoginService.get(function(user) {
                    $rootScope.user = user;
                    $location.path("/");
                    alert(user.email);
                });
            });
        };
    }
]);