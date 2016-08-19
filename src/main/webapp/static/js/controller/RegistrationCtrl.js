/**
 * Created by user on 8/19/16.
 */
siteApp.controller('RegistrationCtrl',[
    '$scope','$rootScope','$http', '$location','$cookieStore','UserLoginService',
    function($scope, $rootScope, $http, $location,$cookieStore,UserLoginService) {
        $scope.rememberMe = true;
        $scope.register = function () {
            UserLoginService.register($.param({
                user_name: $scope.user_name,
                first_name: $scope.first_name,
                last_name: $scope.last_name,
                email: $scope.email,
                password: $scope.password
            }), function () {
                $scope.confirm_email = true;
                // UserLoginService.authenticate($.param({
                //     username: $scope.email,
                //     password: $scope.password
                // }), function (authenticationResult) {
                //     var authToken = authenticationResult.token;
                //     $rootScope.authToken = authToken;
                //     if ($scope.rememberMe) {
                //         $cookieStore.put('authToken', authToken);
                //     }
                //     UserLoginService.get(function (user) {
                //         $rootScope.user = user;
                //         $location.path("/user/" + user.name);
                //     });
                // });
            })
        };
        $scope.reset = function () {
            $scope.myForm.$setPristine();
        }
    }
]);