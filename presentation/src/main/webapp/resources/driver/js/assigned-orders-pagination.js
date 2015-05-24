/**
 * @author Ruslan Gunavardana
 */
var app = angular.module('driver', ['angularUtils.directives.dirPagination'])
app.controller('assignedOrdersController', function($scope, $http) {
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
        $http.get(getContextPath() + 'rest/driver/orders/assigned?page=' + pageNumber)
            .then(function(result) {
                $scope.orders = result.data.items;
                $scope.totalOrders = result.data.count
            });
    }
});
