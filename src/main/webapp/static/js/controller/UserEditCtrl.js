/**
 * Created by user on 8/19/16.
 */
siteApp.controller('UserProfileCtrl',[
    '$scope','$rootScope','$http', '$location','$routeParams','UserService','ImageUrlService',
    function($scope, $rootScope, $http, $location,$routeParams,UserService,ImageUrlService) {
        var nullcomment={};
        $scope.userid = null;
        $scope.comment = nullcomment;
        $scope.user={};
        $scope.isShowComics=true;
        $scope.isShowComments=false;

        console.log($scope.image);

        UserService.getUserByName($routeParams.userName).then(function(data){
            $scope.user=data;
        });


        $scope.updateCommentsUsersAvatars = function () {
            $scope.user.comments.forEach(function(item, i, arr) {
                UserService.getAvatarByUserName(item.userName).then(function(data){
                    item.userAvatarUrl = data.url;
                })
            });
        };

        $scope.isRootUser = function() {
            return $rootScope.user.name == $scope.user.username;
        };

        $scope.addComment = function () {
            if ($scope.comment.text!=null) {
                $scope.comment.userName = $rootScope.user.name;
                $scope.comment.userAvatarUrl = $rootScope.user.avatarUrl;
                $scope.user.comments.push(angular.copy($scope.comment));
                $scope.update();
                $scope.comment = nullcomment;
            }
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

        $scope.changeAvatarUrl = function () {
            if ($scope.image) {
                ImageUrlService.getImageUrl($scope.image).then(function (data) {
                    $scope.user.avatarUrl = data.url;
                    $scope.update();
                });
            }
        };

        $scope.showComics = function(){
            $scope.isShowComics=true;
            $scope.isShowComments=false;
        };

        $scope.showComments = function(){
            $scope.isShowComics=false;
            $scope.isShowComments=true;
            $scope.updateCommentsUsersAvatars();
        }

    }
]);
