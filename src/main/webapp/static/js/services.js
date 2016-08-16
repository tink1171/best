'use strict';

siteApp.factory('UserLoginService', function($resource) {

    return $resource('/login', {},
        {
            authenticate: {
                method: 'POST',
                params: {},
                headers : {'Content-Type': 'application/x-www-form-urlencoded'}
            },
            register:{
                method: 'POST',
                params: {'action' : 'register'},
                headers : {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }
    );
});