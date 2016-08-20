/**
 * Created by user on 8/20/16.
 */
siteApp.controller('SiteListCtrl', ['$scope', 'SiteService', function($scope, SiteService) {
    var self = this;
    var nullsite={id:0,name:"",user:{},rate:0.0,description:'',pages:{blocks:''},tags:{}};
    self.site=nullsite;
    self.sites=[];

    $scope.sortField=undefined;
    $scope.reverse = true;

    $scope.sortBy = function (fieldName) {
        if ($scope.sortField === fieldName){
            $scope.reverse =!$scope.reverse;
        } else {
            $scope.sortField=fieldName;
            $scope.reverse=false;
        }
    };

    $scope.isSortUp = function(fieldName){
        return $scope.sortField === fieldName && !$scope.reverse;
    };

    $scope.isSortDown = function(fieldName){
        return $scope.sortField === fieldName && $scope.reverse;
    };

    $scope.loadMore= function(){

    };

    self.fetchAllSites = function(){
        SiteService.fetchAllSites()
            .then(
                function(d) {
                    self.sites = d;
                    self.sites.forEach(function(item,i,arr){
                        console.log(item);
                        }
                    );

                },
                function(errResponse){
                    console.error('Error while fetching Currencies');
                }
            );
    };

    self.createSite = function(site){
        SiteService.createSite(site)
            .then(
                //self.fetchAllSites,
                function(errResponse){
                    console.error('Error while creating Site.');
                }
            );
    };

    self.updateSite = function(site, id){
        SiteService.updateSite(site, id)
            .then(
                //self.fetchAllSites,
                function(errResponse){
                    console.error('Error while updating Site.');
                }
            );
    };

    self.deleteSite = function(id){
        SiteService.deleteSite(id)
            .then(
                //self.fetchAllSites,
                function(errResponse){
                    console.error('Error while deleting Site.');
                }
            );
    };

    self.fetchAllSites();

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.site.length; i++){
            if(self.sites[i].id == id) {
                self.site = angular.copy(self.site[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.site.id === id) {
            self.reset();
        }
        self.deleteSite(id);
    };


    self.reset = function(){
        self.site=nullsite;
        $scope.myForm.$setPristine(); //reset Form
    };


}]);
