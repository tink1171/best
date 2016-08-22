/**
 * Created by user on 8/12/16.
 */

'use strict';

siteApp.controller('CreatePageCtrl', ['$scope','SiteService','$routeParams' ,
    function( $scope, SiteService, $routeParams){
        $scope.page = {};
        $scope.title = '';
        $scope.template = 0;
        var template = [[0,'rectangle_horizontal','box','box'],
            [1,'rectangle_vertical','rectangle_vertical','none'],
            [2,'rectangle_vertical','box','box']];

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


        $( function() {
            $( ".radio" ).checkboxradio({
                icon: false
            });
        });

        make_drag_and_drop();
        make_dialog();

        $scope.save = function () {
                $scope.page.title = $scope.title;
                $scope.page.content1 = $('#1').html();
                $scope.page.content2 = $('#2').html();
                $scope.page.content3 = $('#3').html();
                $scope.page.template = $scope.template;

                SiteService.updatePage($scope.page , $scope.page.id);
        }

}]);


function make_dialog() {
    $('#dialog_Youtube').dialog({
        autoOpen: false,
        modal: true
    });
    $('#dialog_text').dialog({
        autoOpen: false,
        modal: true
    });
}

function make_drag_and_drop() {
    $('div.draggable').draggable({
        revert:true,
        helper: "clone"

    });
    $('.droppable').droppable({
        drop: function(event , ui) {
            var dragg_id = ui.draggable.attr("id");
            var box_id = this;

            if(dragg_id == "dragg1") {add_text(box_id);}
            if(dragg_id == "dragg2") {add_image(box_id);}
            if(dragg_id == "dragg3") {add_Youtube(box_id);}
        },

    });

}


function add_Youtube(box_id) {
   // $(box_id).append($("#hide_youtube").html());
    $('#dialog_Youtube').dialog("open").dialog({
        buttons: [{
            text: "OK", click: function () {
                var youtube_link = $("#link").val();
                $(box_id).append('<iframe class="resizable" width="100%" height="100%" src=' +
                    youtube_link + ' frameborder="0" allowfullscreen></iframe>');
                $(this).dialog("close");
            }
        }]
    });
}





function add_text(box_id) {

   // $(box_id).append($("#hide_text").html());
    tinymce.init({
        selector: '#mytextarea'
    });
    $('#dialog_text').dialog("open").dialog({
        buttons: [{
            text: "OK", click: function () {
                var text = tinymce.get('mytextarea').getContent();
                $(box_id).append(text);
                $(this).dialog("close");
            }
        }]
    });
}

function add_image(box_id) {
    cloudinary.openUploadWidget({ cloud_name: 'dgyxvzoad',
            upload_preset: 'xpdfeyzq', theme : 'minimal'},
        function(error, result) {
            $(box_id).append("<img style=\"width:100%; height:100%;\" src=\"" +
                result[0].secure_url + "\"/>");
        });
}