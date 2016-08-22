'use strict';

/* Controllers */

siteApp.controller('CreateSiteCtrl', ['$scope','$http','SiteService','$rootScope', '$location',
    function( $scope, $http , SiteService, $rootScope ,$location) {

    $scope.tags = [];

    $scope.pages=[];
    $scope.sitename='';
    $scope.siteLogoUrl = '';
    $scope.site = {};
    $scope.theme = 'default';
        $scope.siteLogoUrl='http://img1.goodfon.su/wallpaper/big/d/19/minimalizm-origami-ptica.jpg';

    $scope.change_theme = function (theme) {
        $scope.theme = theme;
    };

    $scope.load_image = function () {
        cloudinary.openUploadWidget({
                cloud_name: 'dgyxvzoad',
                upload_preset: 'xpdfeyzq', theme: 'minimal'
            },
            function (error, result) {
                $scope.siteLogoUrl = result[0].secure_url;
                $location.path('/create-site')
            });
    };

    $scope.save = function () {
        $scope.site.id=null;
        $scope.site.username=$rootScope.user.name;
        $scope.site.sitename=$scope.sitename;
        alert($scope.description);
        $scope.site.description = $scope.description;
        $scope.site.creationTime="12.05";
        $scope.site.siteLogoUrl = $scope.siteLogoUrl;
        $scope.site.pages = [];
        $scope.site.pages.push({id:null,title:'Page 1',template:0,
            content1:'',content2:'',content3:''});
        //$scope.site.comment=[];
      //  $scope.site.theme = $scope.theme;
    //    $scope.site.rate=0.0;
    //    $scope.site.tags=[];
     //   $scope.tags.forEach(function(item, i, arr) {
    //        $scope.site.tags.push(item);
    //    });
        console.log($scope.site.username);
        SiteService.createSite($scope.site,$scope.site.username).then(
            function (response) {
                $location.path("/edit-site/" + response);
            }
        );

    };


}]);






