angular.module('vendasPlusApp').controller('cadastroPromocaoCtrl', ['$scope', '$uibModal', function($scope, $uibModal){

  $scope.promocao = {};
  $scope.promocao.inicioCampanha = new Date();

  $scope.options = {
    minDate: new Date(),
    showWeeks: true
  };

}]);
