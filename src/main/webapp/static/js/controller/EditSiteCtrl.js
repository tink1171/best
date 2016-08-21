/**
 * Created by user on 8/21/16.
 */
siteApp.controller('EditSiteCtrl', ['$scope','$http','UserService', function( $scope, $http , UserService) {

    $scope.tags = [];

    $scope.pages=[];
    $scope.name='';
    $scope.description='';
    $scope.site = {};



    $scope.save = function () {
        var res = $http.post('/savecompany_json', page);
        res.success(function(data, status, headers, config) {
            $scope.message = data;
        });
        res.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });
    }

    $scope.save = function () {
        $scope.site = getSite();
        //$scope.site.comment=[];
        $scope.site.creationTime="12.05";
        $scope.site.description=$scope.description;
        $scope.site.id=null;
        $scope.site.name=$scope.name;
        $scope.site.rate=0.0;
        $scope.site.tags=[];
        $scope.tags.forEach(function(item, i, arr) {
            $scope.site.tags.push(item);
        });
        $scope.site.userAvatarUrl=$rootScope.user.avatarUrl;
        $scope.site.userName=$rootScope.user.name;
        console.log($scope.site.userName);

        UserService.getUserByName($scope.site.userName).then(function (data) {
            $scope.user=data;
            $scope.user.site.push($scope.site);
            UserService.addNewSite($scope.user,$scope.user.id);
        });


    };


}]);




