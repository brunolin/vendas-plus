angular.module('vendasPlusApp', ['ngAnimate', 'ngAlertify', 'ui.bootstrap']).controller('mainCtrl', ['$scope', '$uibModal', function($scope, $uibModal){

  $scope.menu = {};

  $scope.menuSelected = function menuSelected(item){
    $scope.menu.active = item
    $scope.selectedTemplate = 'empresa/' + item + '.html';
  };
  
}]);
