/**
 * Created by user on 8/12/16.
 */

'use strict';

siteApp.controller('CreatePageCtrl', ['$scope','$http', function( $scope, $http){
    var ppage = {};
    var template = [[0,'rectangle_horizontal','box','box'],
        [1,'rectangle_vertical','rectangle_vertical','none'],
        [2,'rectangle_vertical','box','box']];
    $scope.current_template = template[0];

    $scope.change_template = function (id) {
        $scope.current_template = template[id];
    };

    $( function() {
        $( ".radio" ).checkboxradio({
            icon: false
        });
    });
    // $( function() {
    //     $( "#1,#2, #3" ).sortable({
    //         connectWith: ".connectedSortable",
    //         update: function () {
    //             tinymce.init({
    //                 selector: '.mytextarea'
    //             });
    //         }
    //     });
    // } );
    make_drag_and_drop();
    make_dialog();

    $scope.save = function () {
        alert($($("iframe").get(0).contentDocument).html());
        ppage.content = $('.page_container').html();
        var res = $http.post('/savecompany_json', ppage);
        res.success(function(data, status, headers, config) {
            alert(data);
        });
        res.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });
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
                $(box_id).append('<iframe class="resizable" width="100%" height="100%" src=' + youtube_link + ' frameborder="0" allowfullscreen></iframe>');
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