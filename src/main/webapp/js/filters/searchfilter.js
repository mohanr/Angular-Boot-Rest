angular.module('libraryFilters', []).filter('searchParameter', function() {
return function(input) {
  return input ? '\u2713' : '\u2718';
};
});