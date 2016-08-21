<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en" ng-app="siteApp">
<head>
    <meta charset="utf-8">
    <title>Loftblog Angular Phonecat App</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bower_components/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bower_components/jquery-ui/themes/smoothness/jquery-ui.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/site_defoult.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cards.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userProfile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bower_components/angular-tagger/build/angular-tagger.css"/>


    <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>

    <script src="${pageContext.request.contextPath}/static/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bower_components/jquery-ui/jquery-ui.min.js"></script>

    <style type="text/css">
        #draggable, #droppable {font-size: large; border: thin solid black; padding: 10px;
            width: 100px; text-align: center; background-color: lightgray; margin: 4px;}
        #droppable {padding: 20px; position: absolute; right: 5px;}
    </style>

    <link rel="import" href="${pageContext.request.contextPath}/static/template/pattern/pattern1.html">

</head>
<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#/">SITE</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#/">{{'HOME' | translate}}</a></li>
                <li ng-show="!user"><a href="#/login">{{'LOGIN' | translate}}</a></li>
                <li ng-show="!user" ><a href="#/register">{{'REGISTRATION' | translate}}</a></li>
                <li ng-show="user"><a href="#/create-site">{{'CREATE_SITE' | translate}}</a></li>

                <li ng-show="user">
                    <a href="#/user/{{user.name}}">{{user.name}}</a>
                </li>
                <li ng-show="user">
                    <a href="" ng-click="logout()"><span class="fa fa-sign-out"></span> {{'LOGOUT' | translate}}</a>
                </li>
                <li><a href="" ng-click="changeLanguage('ru')">ru</a></li>
                <li><a href="" ng-click="changeLanguage('en')">en</a></li>
            </ul>

        </div>
    </div>
</nav>

<nav class="navbar navbar-light bg-faded">
    <a class="navbar-brand" href="#">Navbar</a>
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">About</a>
        </li>
    </ul>
    <form class="form-inline pull-xs-right">
        <input class="form-control" type="text" placeholder="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</nav>

<div class="container" style="margin-top: 100px">

    <div class="alert alert-danger" ng-show="error">{{error}}</div>

    <ng-view></ng-view>



    <footer>
        <p>&copy; Vadia i Volina</p>
    </footer>

</div><!--/.container-->


<!-- Angular.js core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/static/bower_components/angular/angular.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-route/angular-route.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-resource/angular-resource.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-sanitize/angular-sanitize.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-resource/angular-resource.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-cookies/angular-cookies.js"></script>
<script src="${pageContext.request.contextPath}/static/bower_components/angular-translate/angular-translate.min.js"></script>

<script src="${pageContext.request.contextPath}/static/lib/cloudinary_widget.js"></script>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
<script src="${pageContext.request.contextPath}/static/js/config.js"></script>
<script src="${pageContext.request.contextPath}/static/js/services.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/CreateSiteCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/CreatePageCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/LoginCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/RegistrationCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/UserEditCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/UserProfileCtrl.js"></script>
<script src="${pageContext.request.contextPath}/static/js/controller/SiteListCtrl.js"></script>


</body>
</html>
