var indexScript = angular.module('indexScript',[]);

indexScript.controller('listController', function($scope, $http) {
    // filtering and paging variables
    $scope.usersOnPage = 5;
    $scope.currentPage = 1;

    $scope.filterName = null;
    $scope.filterAge = -1;
    $scope.searchAdmin = false;
    $scope.filterAdmin = false;

    // load the data on initial page load
    $http.get('/users/count', {
        params: {
            name: $scope.filterName,
            age: $scope.filterAge,
            searchadmin: $scope.searchAdmin,
            admin: $scope.filterAdmin
        }
    }).success(function(userCount){
        $scope.totalCountOfUsers = userCount;
        if (userCount <= $scope.usersOnPage * ($scope.currentPage - 1) || $scope.currentPage < 1) {
            $scope.currentPage = 1;
        }
        $scope.firstIndexOnPage = Math.min(userCount, ($scope.currentPage - 1) * $scope.usersOnPage + 1);
        $scope.lastIndexOnPage = Math.min(userCount, $scope.currentPage * $scope.usersOnPage);
        $http.get('/users', {
            params: {
                from: $scope.firstIndexOnPage,
                to: $scope.lastIndexOnPage,
                name: $scope.filterName,
                age: $scope.filterAge,
                searchadmin: $scope.searchAdmin,
                admin: $scope.filterAdmin
            }
        }).success(function(data) {
            $scope.users = data;
        });
    });

    // function to get data from server and update it on page
    $scope.updateData = function(){
        $http.get('/users/count', {
            params: {
                name: $scope.filterName,
                age: $scope.filterAge,
                searchadmin: $scope.searchAdmin,
                admin: $scope.filterAdmin
            }
        }).success(function(userCount){
            $scope.totalCountOfUsers = userCount;
            if (userCount <= $scope.usersOnPage * ($scope.currentPage - 1) || $scope.currentPage < 1) {
                $scope.currentPage = 1;
            }
            $scope.firstIndexOnPage = Math.min(userCount, ($scope.currentPage - 1) * $scope.usersOnPage + 1);
            $scope.lastIndexOnPage = Math.min(userCount, $scope.currentPage * $scope.usersOnPage);
            $http.get('/users', {
                params: {
                    from: $scope.firstIndexOnPage,
                    to: $scope.lastIndexOnPage,
                    name: $scope.filterName,
                    age: $scope.filterAge,
                    searchadmin: $scope.searchAdmin,
                    admin: $scope.filterAdmin
                }
            }).success(function(data) {
                $scope.users = data;
            }).error(function() {
                $scope.users = [];
            });
        });
    };

    // previous/next page
    $scope.changePage = function (page) {
        $scope.currentPage = page;
        $scope.updateData();
    };

    // change number of entries on page
    $scope.changeUsersOnPage = function(newUsersOnPage){
        $scope.currentPage = 1;
        $scope.usersOnPage = newUsersOnPage;
        $scope.updateData();
    };

    // delete User by ID
    $scope.deleteUser = function (id) {
        $http.delete('/users/' + id).success(function(){
            $scope.updateData();
        });
    };

    // variables for showing edit/add and filter rows
    $scope.showNewUserRow = false;
    $scope.showFilterRow = false;

    // temp variables for new user submission
    $scope.newUserId = -1;
    $scope.newUserName = '';
    $scope.newUserAge = 0;
    $scope.newUserCreatedDate = 0;
    $scope.newUserIsAdmin = false;

    // reset "new user" variables which are used for submission
    $scope.resetNewUser = function() {
        $scope.showNewUserRow = false;
        $scope.newUserId = -1;
        $scope.newUserName = '';
        $scope.newUserAge = 0;
        $scope.newUserCreatedDate = 0;
        $scope.newUserIsAdmin = false;
    };

    // submit new user OR edited old one
    $scope.submitNewUserToServer = function () {
        if ($scope.newUserId == -1) {
            $scope.newUserCreatedDate = Date.now();
        }
        var newUserObject = {
            id: $scope.newUserId,
            name: $scope.newUserName,
            age: $scope.newUserAge,
            createdDate: $scope.newUserCreatedDate,
            admin: $scope.newUserIsAdmin
        };
        var res = $http.post('/users', newUserObject);
        res.success(function() {
            $scope.updateData();
        });
        $scope.resetNewUser();
    };

    // edit existing user - paste his variables to temp variables
    $scope.editUser = function (index) {
        $scope.showNewUserRow = true;
        $scope.newUserId = $scope.users[index].id;
        $scope.newUserName = $scope.users[index].name;
        $scope.newUserAge = $scope.users[index].age;
        $scope.newUserCreatedDate = $scope.users[index].createdDate;
        $scope.newUserIsAdmin = $scope.users[index].admin;
    };

    $scope.tempFilterName = null;
    $scope.tempFilterAge = -1;
    $scope.tempSearchAdmin = false;
    $scope.tempFilterAdmin = false;
    
    $scope.resetFilterUser = function() {
        $scope.filterName = null;
        $scope.filterAge = -1;
        $scope.searchAdmin = false;
        $scope.filterAdmin = false;
        $scope.showFilterRow = false;
        $scope.updateData();
    };

    $scope.resetTempFilterUser = function() {
        $scope.tempFilterName = null;
        $scope.tempFilterAge = -1;
        $scope.tempSearchAdmin = false;
        $scope.tempFilterAdmin = false;
        $scope.showFilterRow = false;
    };

    $scope.applyFilter = function() {
        $scope.filterName = $scope.tempFilterName;
        $scope.filterAge = $scope.tempFilterAge;
        $scope.searchAdmin = $scope.tempSearchAdmin;
        $scope.filterAdmin = $scope.tempFilterAdmin;
        $scope.resetTempFilterUser();
        $scope.showFilterRow = false;
        $scope.updateData();
    };

    $scope.filterString = function() {
        if ($scope.filterName == null &&
            $scope.filterAge == -1 &&
            $scope.searchAdmin == false)
            return '';
        var str = "Filter:"
        if ($scope.filterName != null)
            str += " name: " + $scope.filterName;
        if ($scope.filterAge != -1)
            str += " age: " + $scope.filterAge;
        if ($scope.searchAdmin)
            str += " admin: " + $scope.filterAdmin;
        return str;
    };
    
});