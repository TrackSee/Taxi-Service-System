angular.module('driverIndex', ['angularUtils.directives.dirPagination'])
    .controller('ordersController', function($scope, $http) {
        $scope.orders = [];
        $scope.totalOrders = 0;
        $scope.usersPerPage = 1; // this should match however many results your API puts on one page
        getResultsPage(1);

        $scope.pagination = {
            current: 1
        };

        $scope.pageChanged = function(newPage) {
            getResultsPage(newPage);
        };

        function getResultsPage(pageNumber) {
            // this is just an example, in reality this stuff should be in a service
            $http.get(getContextPath() + 'rest/orders/available?page=' + pageNumber)
                .then(function(result) {
                    $scope.orders = result.data.Items;
                    $scope.totalUsers = result.data.Count
                });
        }
    });