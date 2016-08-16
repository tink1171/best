/**
 * Created by user on 8/12/16.
 */

'use strict';

siteApp.controller('CreatePageCtrl', ['$scope','$http', function( $scope, $http){
    var ppage = {};
    var id = 0;
    make_dialog();
    make_drag_and_drop(id);

    $scope.save = function () {
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

function make_resizable() {
    $( function() {
        $( ".resizable" ).resizable({
            containment: ".page_container"
        });
    } );
}

function make_drag_and_drop(id) {
    $('div.draggable').draggable({
        revert:true,
        helper: "clone"

    });
    $('.page_container').droppable({
        drop: function(event , ui) {
            var dragg_id = ui.draggable.attr("id");
            var box_id = this;
            $('.page_container').append('<div class ="resizable box" id = ' + id + '></div>' );
            make_resizable();
            if(dragg_id == "dragg1") {add_text('#'+id);}
            if(dragg_id == "dragg2") {add_image('#'+id);}
            if(dragg_id == "dragg3") {add_Youtube('#'+id);}
            id = id+1;
        },
        over: function() {
            $(this).css({
                border: "medium double blue"
            });
        }

    });

}


function add_Youtube(box_id) {
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