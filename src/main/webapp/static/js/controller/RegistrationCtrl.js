/**
 * Created by user on 8/19/16.
 */
siteApp.controller('RegistrationCtrl',[
    '$scope','$rootScope','$http', '$location','$cookieStore','UserLoginService',
    function($scope, $rootScope, $http, $location,$cookieStore,UserLoginService) {
        $scope.rememberMe = true;
        $scope.register = function () {
            UserLoginService.register($.param({
                username: $scope.username,
                email: $scope.email,
                password: $scope.password
            }), function () {
                UserLoginService.authenticate($.param({
                    username: $scope.username,
                    password: $scope.password
                }), function (authenticationResult) {
                    var authToken = authenticationResult.token;
                    $rootScope.authToken = authToken;
                    if ($scope.rememberMe) {
                        $cookieStore.put('authToken', authToken);
                    }
                    UserLoginService.get(function (user) {
                        $rootScope.user = user;
                        $location.path("/user/" + user.name);
                    });
                });
            })
        };
        $scope.reset = function () {
            $scope.myForm.$setPristine();
        }
    }
]);