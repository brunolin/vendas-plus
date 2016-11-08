angular.module('vendasPlusApp', ['ngAnimate', 'ngAlertify', 'ui.bootstrap']).controller('mainCtrl', ['$scope', function($scope){

  $scope.menu = {};

  $scope.menuSelected = function menuSelected(item){
    $scope.menu.active = item
    $scope.selectedTemplate = 'empresa/' + item + '.html';
  };
}]);
