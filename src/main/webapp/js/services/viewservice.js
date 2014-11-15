var libraryServices = angular.module('libraryServices', ['ngResource']);

/*
	Query factory
*/
libraryServices.factory('Library', ['$resource',
  function($resource){
    return $resource('/books', {}, {
      query: {method:'GET', isArray:true}
    });
  }]);
  
/*
	Data pipeline between controllers. This should be
	a service and not a factory but it works.
*/
  
libraryServices.factory('EditBookService',[ function() {
  var books = [];
  var EditBookService = {};
 /*
 	We are not editing multiple selections
 */
    EditBookService.editBookService = function (newObj) {
	      console.log(newObj);
	      books.push(newObj);
    };
    
	EditBookService.getEditableBookService = function() {
	         //console.log( books.pop()); //Don't pop if  needed later.
	         return books;
	};

	EditBookService.getTableView = function() {
	         return tableview;
	};
	
	return EditBookService;
}]);