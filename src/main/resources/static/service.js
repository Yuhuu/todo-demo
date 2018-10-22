app.service('crudService', function ($http) {

 var baseUrl = 'http://localhost:8080';

 //Create new record
 this.post = function (Todolist) {
 var request = $http({
 method: "post",
 url: baseUrl + "/todoItems/",
 data: Todolist
 });
 return request;
 }

 //Get Single Records
 this.get = function (Id) {
 return $http.get(baseUrl + "/todoItems/" + Id);
 }

 //Get All Todolists
 this.getAllTodolists = function () {
 return $http.get(baseUrl + "/todoItems");
 }

 //Update the Record
 this.put = function (Id, Todolist) {
 var request = $http({
 method: "put",
 url: baseUrl + "/todoItems/" + Id,
 data: Todolist
 });
 return request;
 }

 //Delete the Record
 this.delete = function (Id) {
 var request = $http({
 method: "delete",
 url: baseUrl + "/todoItems/" + Id
 });
 return request;
 }
});