/**
 * Created by user on 8/21/16.
 */
siteApp.controller('EditSiteCtrl', ['$scope','$http','UserService','SiteService','$routeParams', '$location',
    function( $scope, $http , UserService, SiteService ,$routeParams,$location) {

        $scope.site = {};
        $scope.siteId = $routeParams.siteId;
        $scope.page = {};

        function getSite() {
            SiteService.getSiteById($scope.siteId).then(function (response) {
                $scope.site = response;
            });
        }

        getSite();

     $scope.add_page = function () {
         $scope.nullpage = {id:null,title:'Page 1',template:0,
             content1:'',content2:'',content3:''};
         $scope.site.pages.push($scope.nullpage);
         SiteService.updateSite($scope.site, $scope.site.id).then(
             function (data) {
                 $scope.site = data;
             }
         )
     };

     $scope.delete = function (id) {
       SiteService.deletePage($scope.site.id ,id).then(
           function () {
               getSite();
           }
       )
     };


     $scope.edit_page = function (id) {
         $location.path('/edit-page/'+id);
     };


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




