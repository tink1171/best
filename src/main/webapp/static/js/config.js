siteApp.config([
    '$routeProvider', '$locationProvider','$httpProvider',  "$translateProvider",
    function ($routeProvider, $locationProvider,$httpProvider,$translateProvider) {
        $locationProvider.html5Mode({
            enable: true,
            requireBase: false
        });

    $routeProvider
        .when('/',
            {
                templateUrl:"static/template/home.html",
                controller:'SiteListCtrl'
            })
        .when('/create-site',
            {
                templateUrl:"static/template/create-site.html",
                controller:'CreateSiteCtrl'
            })
        .when('/show-site',
            {
                templateUrl:"static/template/show-site.html",
                controller:'ShowSiteCtrl'
            })
        .when('/create-page',
            {
                templateUrl:"static/template/create-page.html",
                controller:'CreatePageCtrl'
            })
        .when('/login',
            {
                templateUrl:"static/template/login.html",
                controller:'LoginCtrl'
            })
        .when('/register', {
            templateUrl: 'static/template/register.html',
            controller: 'RegistrationCtrl'
        })
        .when('/admin',{
            templateUrl:"static/template/admin.html",
                controller:'AdminCtrl'
        })
        .when('/user/:userName', {
            templateUrl: 'static/template/userprofile.html',
            controller: 'UserProfileCtrl'
        })
        .when('/edituser/:userName', {
            templateUrl: 'static/template/edituser.html',
            controller: 'UserEditCtrl'
        })
        .otherwise({
            redirectTo : '/'
    });
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'responseError': function (rejection) {
                        var status = rejection.status;
                        var config = rejection.config;
                        var method = config.method;
                        var url = config.url;

                        if (status == 401) {
                            $location.path("/login");
                        } else {
                            $rootScope.error = method + " on " + url + " failed with status " + status;
                        }

                        return $q.reject(rejection);
                    }
                };
            }
        );

        /* Registers auth token interceptor, auth token is either passed by header or by query parameter
         * as soon as there is an authenticated user */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'request': function (config) {
                        var isRestCall = config.url.indexOf('rest') == 0;
                        if (isRestCall && angular.isDefined($rootScope.authToken)) {
                            var authToken = $rootScope.authToken;
                            if (exampleAppConfig.useAuthTokenHeader) {
                                config.headers['X-Auth-Token'] = authToken;
                            } else {
                                config.url = config.url + "?token=" + authToken;
                            }
                        }
                        return config || $q.when(config);
                    }
                };
            }
        );

        $translateProvider.translations('en', {
            SITE:'SITE',
            SITES:'Sites',
            HOME: 'Home',
            LOGIN: 'Login',
            REGISTRATION: 'Registration',
            CREATE_SITE:'Create site',
            USER_PROFILE:'User profile',
            SEARCH:'Search',
            SORT:'Sort by',
            BYDATE:'By date',
            BYRATE:'By rate',
            VIEW_SITE:'view site',
            COMMENTS:'Comments',
            USERNAME:'Username',
            PASSWORD:'Password',
            SIGN_IN:'Sing in',
            CREATE_ACCOUNT:'Create account',
            NEW_TO_SITE:'new to site',
            REMEMBER_ME:'Remember me',
            YOUR_NAME:'Your name',
            YOUR_EMAIL:'Your email',
            PASSWORD_AGAIN:'Password again',
            CREATE_YOUR_ACCOUNT:'Create your account',
            RESET_FORM:'Reset form',
            ALREADY_HAVE_ACCOUNT:'Already have account',
            RATE:'Rate',
            LOGOUT:'Logout',
            FIRST_NAME:'First name',
            LAST_NAME:'Last name',
            EMAIL:'Email',
            ABOUT:'About',
            EDIT_USER:'Edit user',
            SAVE:'Save',
            CANCEL:'Cancel',
            NAME:'Name',
            DESCRIPTION:'Description',
            TEMPLATES:'Templates',
            PAGE:'Page',
            ADD_NEW_PAGE:'Add new page',
            CLOUDS:"Clouds"
        });
        $translateProvider.translations('ru', {
            SITES: 'Сайты',
            HOME: 'Главная',
            LOGIN: 'Вход',
            REGISTRATION: 'Регистрация',
            CREATE_SITE:'Создать сайт',
            USER_PROFILE:'Персональная страница',
            SEARCH:'Поиск',
            SORT:'Сортировать',
            BYDATE:'по дате',
            BYRATE:'по рейтингу',
            VIEW_SITE:'просмотреть сайт',
            COMMENTS:'Комментарии',
            USERNAME:'Имя пользователя',
            PASSWORD:'Пароль',
            SIGN_IN:'Вход',
            CREATE_ACCOUNT:'Создать аккаунт',
            NEW_TO_SITE:'впервые на сайте',
            REMEMBER_ME:'Запомнить меня',
            YOUR_NAME:'Ваше имя',
            YOUR_EMAIL:'Ваш email',
            PASSWORD_AGAIN:'Повторите пароль',
            CREATE_YOUR_ACCOUNT:'Создать ваш аккаунт',
            RESET_FORM:'Очистить данные',
            ALREADY_HAVE_ACCOUNT:'Уже есть аккаунт',
            RATE:'Рейтинг',
            LOGOUT:'Выход',
            FIRST_NAME:'Имя',
            LAST_NAME:'Фамилия',
            EMAIL:'Почта',
            ABOUT:'О себе',
            EDIT_USER:'Редактировать профиль',
            SAVE:'Сохранить',
            CANCEL:'Отменить',
            NAME:'Имя',
            DESCRIPTION:'Описание',
            TEMPLATES:'Шаблоны',
            PAGE:'Страница',
            ADD_NEW_PAGE:'Добавить страницу',
            CLOUDS:"Облака"
        });
        $translateProvider.useSanitizeValueStrategy(null);
        $translateProvider.preferredLanguage('en');

    }]).run(function ($rootScope, $location, $cookieStore,$translate, UserLoginService) {

    /* Reset error when a new view is loaded */
    $rootScope.$on('$viewContentLoaded', function () {
        delete $rootScope.error;
    });

    $rootScope.hasRole = function (role) {

        if ($rootScope.user === undefined) {
            return false;
        }

        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }

        return $rootScope.user.roles[role];
    };

    $rootScope.logout = function () {
        delete $rootScope.user;
        delete $rootScope.authToken;
        $cookieStore.remove('authToken');
        $location.path("/login");
    };

    /* Try getting valid user from cookie or go to login page */
    $location.path("/login");
    var authToken = $cookieStore.get('authToken');
    if (authToken !== undefined) {
        $rootScope.authToken = authToken;
        UserLoginService.get(function (user) {
            $rootScope.user = user;
            $location.path("/");
        });
    }

    $rootScope.initialized = true;

    $rootScope.changeLanguage = function (key) {
        $translate.use(key);
    };
});