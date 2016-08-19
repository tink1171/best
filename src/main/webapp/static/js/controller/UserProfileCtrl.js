/**
 * Created by user on 8/19/16.
 */
siteApp.controller('UserEditCtrl',[
    '$scope','$rootScope','$http','$routeParams','$location','UserService',
    function($scope, $rootScope, $http, $routeParams,$location,UserService) {
        $scope.userid = null;
        $scope.user={};

        UserService.getUserByName($routeParams.userName).then(function(data){
            $scope.user=data;
        });


        $scope.isRootUser = function() {
            return $rootScope.user.name == $scope.user.username;
        };

        $scope.update=function () {
            UserService.updateUser($scope.user, $scope.user.id).then(function(data){
                $scope.user=data;
                $rootScope.user.avatarUrl=$scope.user.avatarUrl;
            });
        };

        $scope.save = function () {
            $scope.changeAvatarUrl();
            $scope.update();
            $location.path('user/'+$scope.user.username);
        };
    }
]);

/* UserProfile Controller*/
