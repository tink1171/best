'use strict';

/* Controllers */

siteApp.controller('CreateSiteCtrl', ['$scope','$http','UserService','$rootScope',
    function( $scope, $http , UserService, $rootScope) {

    $scope.tags = [];

    $scope.pages=[];
    $scope.name='';
    $scope.description='';
    $scope.site = {};

    // $scope.save = function () {
    //     var res = $http.post('/savecompany_json', page);
    //     res.success(function(data, status, headers, config) {
    //         $scope.message = data;
    //     });
    //     res.error(function(data, status, headers, config) {
    //         alert( "failure message: " + JSON.stringify({data: data}));
    //     });
    // }

    $scope.save = function () {
        $scope.site.id=2;
        $scope.site.username=$rootScope.user.name;
        $scope.site.sitename=$scope.name;
        $scope.site.siteLogoUrl = "";
        $scope.site.description = "4324234";
        $scope.site.creationTime="12.05";

        //$scope.site.comment=[];
      //  $scope.site.theme = $scope.theme;
    //    $scope.site.rate=0.0;
    //    $scope.site.tags=[];
     //   $scope.tags.forEach(function(item, i, arr) {
    //        $scope.site.tags.push(item);
    //    });

        console.log($scope.site.username);

        UserService.getUserByName($scope.site.username).then(function (data) {
            $scope.user=data;
            $scope.user.site.push($scope.site);
            UserService.updateUser($scope.user,$scope.user.id);
        });


    };


}]);



siteApp.controller('ShowSiteCtrl', ['$scope','$http', function( $scope, $http){
    $scope.page = {};
    $http.get("/page/2").success(function (response) {
        $scope.page = response;
        $('.hui').append($scope.page.content);
    }).error(function () {
        alert("hui");
    });
    $scope.theme = "dark";

}]);





