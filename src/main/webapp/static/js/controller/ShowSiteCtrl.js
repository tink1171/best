/**
 * Created by user on 8/22/16.
 */
siteApp.controller('ShowSiteCtrl', ['$scope','$routeParams','SiteService',
    function( $scope, $routeParams,SiteService){
        $scope.page = {};
        $scope.page = {};
        $scope.title = '';
        $scope.template = 0;
        var template = [[0,'rectangle_horizontal_view','box_view','box_view'],
            [1,'rectangle_vertical_view','rectangle_vertical_view','none_view'],
            [2,'rectangle_vertical_view','box_view','box_view']];

        $scope.current_template = template[0];

        $scope.change_template = function change_template (id) {
            $scope.current_template = template[id];
            $scope.template = id;
        };
        SiteService.getPageById($routeParams.id).then(
            function (data) {
                $scope.page = data;
                alert($scope.page.content);
                $('#1').append($scope.page.content1);
                $('#2').append($scope.page.content2);
                $('#3').append($scope.page.content3);

                $scope.template = $scope.page.template;
                $scope.current_template = template[$scope.page.template];
            }
        );



}]);
