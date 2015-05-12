'use strict';
angular.module('favoritePlaces', ['ngResource', 'ngRoute', 'ui.bootstrap'])
    .config(function ($routeProvider) {
        $routeProvider.when('/',{
            controller: 'SeatCtrl'
        }).otherwise({
            redirectTo: '/'
        })
    });

