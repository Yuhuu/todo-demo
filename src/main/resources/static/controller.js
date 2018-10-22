app.controller('TodoListController', function TodoItemListController($scope, $http, crudService) {
 $scope.statusClass = 'label-info';
 $scope.status = 'Loading ...';
 $scope.IsNewRecord = 1; //The flag for the new record

 loadAllTodoItems();

 function loadAllTodoItems() {

 var promise = crudService.getAllTodoItems();

 promise.then(
 function (response) {
 $scope.todoItems = response.data;
 $scope.status = 'Loaded. Code: ' + response.status;
 $scope.statusClass = 'label-success';
 },
 function (error) {
 $scope.todoItems = null;
 $scope.status = 'Error: ' + error;
 $scope.statusClass = 'label-warning';
 $log.error('failure loading TodoItems', error);
 });
 }

 //Method to save and add
 $scope.save = function () {
 var TodoItem = {
 Id: $scope.TodoItemId,
 Name: $scope.TodoItemName,
 Author: $scope.TodoItemAuthor
 };

 //If the flag is 1 the it is a new record
 if ($scope.IsNewRecord === 1) {
 var promisePost = crudService.post(TodoItem);
 promisePost.then(function (result) {
 $scope.TodoItemId = result.data;
 loadAllTodoItems();
 }, function (error) {
 $scope.status = 'Error: ' + error;
 console.log("Err" + error);
 });
 } else { //Else Edit the record
 var promisePut = crudService.put($scope.TodoItemId, TodoItem);
 promisePut.then(function (result) {
 $scope.status = "Updated Successfuly";
 $scope.statusClass = 'label-success';
 loadAllTodoItems();
 }, function (error) {
 $scope.status = 'Error: ' + error;
 $scope.statusClass = 'label-warning';
 console.log("Err" + error);
 });
 }

 }

 //Method to Delete
 $scope.delete = function () {
 var promiseDelete = crudService.delete($scope.TodoItemId);
 promiseDelete.then(function (result) {
 $scope.status = "Deleted Successfuly";
 $scope.statusClass = 'label-success';
 $scope.TodoItemId = 0;
 $scope.TodoItemName = "";
 $scope.TodoItemAuthor = "";

 loadAllTodoItems();
 }, function (error) {
 $scope.status = 'Error: ' + error;
 $scope.statusClass = 'label-warning';
 console.log("Err" + error);
 });
 }

 //Method to Get Single TodoItem
 $scope.get = function (TodoItem) {
 var promiseGetSingle = crudService.get(TodoItem.id);

 promiseGetSingle.then(function (result) {
 var res = result.data;
 $scope.TodoItemId = res.id;
 $scope.TodoItemName = res.name;
 $scope.TodoItemAuthor = res.author;

 $scope.IsNewRecord = 0;
 },
 function (errorPl) {
 console.log('failure loading Employee', errorPl);
 });
 }

 //Clear the Scope models
 $scope.clear = function () {
 $scope.statusClass = 'label-info';
 $scope.status = "";
 $scope.IsNewRecord = 1;
 $scope.TodoItemId = 0;
 $scope.TodoItemName = "";
 $scope.TodoItemAuthor = "";
 }

}).directive('myTodoItemtableheader', function () {
 return {
 templateUrl: 'http://localhost:8080/TodoItemTemplate.html'
 };
});