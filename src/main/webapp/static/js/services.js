'use strict';

siteApp.factory('UserLoginService', function($resource) {

    return $resource('/user/:action', {},
        {
            authenticate: {
                method: 'POST',
                params: {'action' : 'authenticate'},
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


siteApp.factory('UserService', ['$http', '$q', function($http, $q){

    return {
        fetchAllUsers: function() {
            return $http.get('/users/')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching users');
                        return $q.reject(errResponse);
                    }
                );
        },

        getUserById: function(userId){
            return $http.get('/user/'+userId)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while getting user');
                        return $q.reject(errResponse);
                    }
                );
        },

        getAvatarByUserName: function(username){
            return $http.post('/user/avatar/'+username)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while getting user avatar');
                        return $q.reject(errResponse);
                    }
                );
        },

        getUserByName: function(name){
            return $http.get('/user/'+name)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while getting user');
                        return $q.reject(errResponse);
                    }
                );
        },

        get:function (user) {
            return $http.post('/user/', user).then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while creating user');
                    return $q.reject(errResponse);
                }
            );
        },

        createUser: function(user){
            return $http.post('/user/', user)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateUser: function(user, id){
            return $http.put('/user/'+id, user)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                );
        },
        addNewComix: function(user, id){
            return $http.put('/user/createcomix'+id, user)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating user');
                        return $q.reject(errResponse);
                    }
                );
        },
        deleteUser: function(id){
            return $http.delete('/user/'+id)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting user');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

}]);

siteApp.factory('ImageUrlService',['$http', '$q', function($http, $q) {
    return{
        getImageUrl: function (base64) {
            return $http.post('/image/',base64).then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while fetching tags');
                    return $q.reject(errResponse);
                }
            )
        }
    }
}]);

