var app = angular.module("Management", []);
 
// Controller Part
app.controller("TodoListController", function($scope, $http) {
 
 
    $scope.todolists = [];
    $scope.todolistForm = {
        empId: 1,
        empNo: "",
        empName: ""
    };
 
    // Now load the data from server
    _refreshtodolistData();
 
    // HTTP POST/PUT methods for add/edit todolist  
    // Call: http://localhost:8080/todolist
    $scope.submittodolist = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.todolistForm.empId == -1) {
            method = "POST";
            url = '/todolist';
        } else {
            method = "PUT";
            url = '/todolist';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.todolistForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createtodolist = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete todolist by Id
    // Call: http://localhost:8080/todolist/{empId}
    $scope.deletetodolist = function(todolist) {
        $http({
            method: 'DELETE',
            url: '/todolist/' + todolist.empId
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.edittodolist = function(todolist) {
        $scope.todolistForm.empId = todolist.empId;
        $scope.todolistForm.empNo = todolist.empNo;
        $scope.todolistForm.empName = todolist.empName;
    };
 
    // Private Method  
    // HTTP GET- get all todolists collection
    // Call: http://localhost:8080/todolists
    function _refreshtodolistData() {
        $http({
            method: 'GET',
            url: '/todoitems'
        }).then(
            function(res) { // success
                $scope.todolists = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshtodolistData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.todolistForm.tagsId = -1;
        $scope.todolistForm.description = "";

    };
});