'use strict';

/* Controllers */

siteApp.controller('CreateSiteCtrl', ['$scope','$http', function( $scope, $http) {

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





