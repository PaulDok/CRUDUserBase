var indexScript = angular.module('indexScript',[]);

indexScript.controller('listController', function($scope, $http) {
    $http.get('/users').success(function(data) {
        $scope.users = data;
    });
});