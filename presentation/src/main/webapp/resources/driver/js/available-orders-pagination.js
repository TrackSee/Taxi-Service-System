/**
 * @author Ruslan Gunavardana
 */
var app = angular.module('driver', ['angularUtils.directives.dirPagination']);

app.config(function($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        'self',
        'https://www.google.com/maps/**'
    ]);
});

app.controller('availableOrdersController', function($scope, $http) {
    $scope.orders = [];
    $scope.totalOrders = 0;
    $scope.ordersPerPage = 3;
    getResultsPage(1);

    $scope.pagination = {
        current: 1
    };

    $scope.pageChanged = function(newPage) {
        getResultsPage(newPage);
    };

    function getResultsPage(pageNumber) {
        $http.get(getContextPath() + 'rest/driver/orders/available?page=' + pageNumber)
            .then(function(result) {
                $scope.orders = result.data.items;
                $scope.totalOrders = result.data.count;

                function getString(latLng) { return latLng.lat().toFixed(6) + ',' + latLng.lng().toFixed(6) }

                for (var i in $scope.orders) {
                    var latlngArr = google.maps.geometry.encoding.decodePath($scope.orders[i].routes[0].encodedRoute);
                    $scope.orders[i].map = 'https://www.google.com/maps/embed/v1/directions?key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&mode=driving&origin=' +
                        getString(latlngArr.shift()) +
                        '&destination=' +
                        getString(latlngArr.pop());
                }
            });
    }
});