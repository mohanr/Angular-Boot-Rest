'use strict';


var librarysystemcontroller = angular.module('librarysystemcontroller',['ngTable']);

	librarysystemcontroller.controller('EmptyCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
		function($rootScope, $scope, $routeParams, $http) {
			/*This is not used effectively*/
			console.log('EmptyCtrl');
		}
	]);

	 librarysystemcontroller.controller('SearchListCtrl',
	                                     ['$scope',
	                                      'Library',
	                                      'EditBookService',
	                                      '$http',
	                                      '$rootScope',
	                                      function($scope,
	                                               Library,
	                                               EditBookService,
	                                               $http,
	                                               $rootScope) {
     	console.log( 'SearchListCtrl' )
     	$scope.theDate  = new Date();
     	$scope.titleFilter = null;
     	var result = Library.query();
        console.log( result )
	    $scope.books = result;
        /*This will be called only if it is the current controller in the surrounding div*/
		$scope.editBook = function (editableBook){
	  		EditBookService.editBookService(editableBook);
	    	console.log(editableBook);
  		}

		$scope.deleteRow = function (row){
	    	console.log('Row to be deleted is [%s]',row);
  		
	  		 /*Move to a factory/service*/
	        $http.delete('/deletebook/' + row, {
						data: {"index": row}
				},
				{
						headers: {'Content-Type': 'application/json'}
				})
				.success(function(data, status, headers, config) {
	    			console.log('Row [%s] is deleted ',row);
	    			$scope.books.splice(row,1);
				})
				.error(function(data, status, headers, config) {
	    			console.log('Row [%s] is not deleted ',row);
				});
  		}
  		
  		$scope.$on('refresh',function($event) {
  			// trigger the ajax call.Bring from the central repository
  			//to see the latest changes.
  			console.log('Refresh table');
	     	var result = Library.query();
	        console.log( result )
		    $scope.books = result;
		    //$scope.tableview.reload();
  		});
	}]); 
    
    
    /* Controller loads test data. This should be replaced with proper
       karma tests.
    
    
     librarysystemcontroller.controller('SearchListCtrl', function($scope) {
     console.log( 'SearchListCtrl' )
     $scope.theDate  = new Date();
     $scope.nameFilter = null;
     $scope.books = [];
     $scope.books = [
      {
          genre: 'Science Fiction',
          prize: '',
          title: 'Pandora\'s star',
          author: 'Peter F. Hamilton'
      },
      {
          genre: 'Science Fiction',
          prize: '',
          title: "Absolution gap",
          author: 'Alistair Reynolds'
      },
      {
          genre: 'Fiction',
          prize: 'Man Booker',
          title: 'Foucalt\'s Pendulum',
          author: 'Umberto Eco'
      },
      {
          genre: 'Fiction',
          prize: 'Man Booker',
          title: 'A suitable Boy',
          author: 'Vikram Seth'
      },
      {
          genre: 'Spy Thriller',
          prize: 'Man Booker',
          title: 'Tinker Tailor Soldier Spy',
          author: 'John Le Carre'
      }
    ];
    
 );*/
 
  
  
 librarysystemcontroller.controller('BookAdditionCtrl', ['$scope',
                                                         'EditBookService',
                                                         '$routeParams',
                                                         '$location',
                                                         '$http',
                                                         '$rootScope',
  function($scope,
           EditBookService,
           $routeParams,
           $location,
           $http,
           $rootScope) {
 		
       		if($routeParams.bookId != null){
	 		    $scope.bookId = $routeParams.bookId;
    			getEditableBookService();
    		}
		    function getEditableBookService() {
		        var editablebook = EditBookService.getEditableBookService();
		        var editablebookdetails = editablebook.pop();
		        
         		if( !editablebookdetails ){
         			$location.path("/"); // path not hash
         			return;
		        }
		        console.log('Edit service is called [%s][%s][%s][%s]',
		                    editablebookdetails.genre,
		                    editablebookdetails.author,
		                    editablebookdetails.title,
		                    $routeParams.bookId);
		        $scope.editablegenre = editablebookdetails.genre;
		        $scope.editableauthor = editablebookdetails.author;
		        $scope.editabletitle = editablebookdetails.title;
		        
		    }
    		
		      /*Move to a factory/service*/
		    $scope.saveData = function(validity) {
 
 			console.log(validity);     
      		if($routeParams.bookId == null){
      
			      console.log("BookAdditionCtrl[%s][%s][%s]",
                   $scope.editablegenre,
                   $scope.editableauthor,
                   $scope.editabletitle);
                   
      	      $http.post('/addbook', {
						"genre": $scope.editablegenre
						,"title": $scope.editabletitle
						,"author": $scope.editableauthor
						,"id" : $routeParams.bookId
				})
				.success(function(data, status, headers, config) {
					console.log('Successfully added book = ' , data);
					$scope.genre = '';
					$scope.title = '';
					$scope.author = '';
				})
				.error(function(data, status, headers, config) {
					console.log('error adding book: data = ' , data);
				});
				
			}else{
			      console.log("BookAdditionCtrl[%s][%s][%s]",
                   $scope.editablegenre,
                   $scope.editableauthor,
                   $scope.editabletitle,
                   $routeParams.bookId);
			
			
      
			      $http.post('/editbook', {
								"genre": $scope.editablegenre
								,"title": $scope.editabletitle
								,"author": $scope.editableauthor
								,"id" : $routeParams.bookId
						})
						.success(function(data, status, headers, config) {
							console.log('Successfully edited book = ' , data);
							$scope.genre = '';
							$scope.title = '';
							$scope.author = '';
 				    		$scope.successfuledit = true;
 						})
						.error(function(data, status, headers, config) {
							console.log('error editing book: data = ' , data);
				     		$scope.failededit = true;
 
						});
      		}
      		console.log('Broadcast');
      		$rootScope.$broadcast('refresh');
    };
    
     /*Move to a factory/service*/
     $scope.addData = function() {
	      console.log("BookAdditionCtrl[%s][%s][%s]",
	                   $scope.editablegenre,
	                   $scope.editableauthor,
	                   $scope.editabletitle);
	      $scope.genreRequired = '';
	      $scope.titleRequired = '';
	      $scope.authorRequired = '';
	 
	      if (!$scope.editablegenre) {
	        $scope.genreRequired = 'Genre Required';
	      }
	 
	      if (!$scope.editabletitle) {
	        $scope.titleRequired = 'Title Required';
	      }
	 
	      if (!$scope.editableauthor) {
	        $scope.authorRequired = 'Author Required';
	      }
      
	      $http.post('/addbook', {
						"genre": $scope.editablegenre
						,"title": $scope.editabletitle
						,"author": $scope.editableauthor
						,"id" : $routeParams.bookId
				})
				.success(function(data, status, headers, config) {
					console.log('data = ' , data);
					$scope.genre = '';
					$scope.title = '';
					$scope.author = '';
				})
				.error(function(data, status, headers, config) {
					console.log('error: data = ' , data);
				});
      
    };
    
  }]);
 
 

 
