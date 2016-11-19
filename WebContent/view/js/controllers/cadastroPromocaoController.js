angular.module('vendasPlusApp').controller('cadastroPromocaoCtrl', ['$scope', '$http', 'alertify', function($scope, $http, alertify){

  $scope.promocao = {};
  $scope.promocao.inicioCampanha = new Date();
  $scope.loadingSuccess = false;

  $scope.save = function save() {
    $scope.loadingSuccess = true;
    $http.post('/r/empresa/cadastrarCampanha', $scope.promocao).then(function(resp) {
      $scope.promocao = {};
      $scope.loadingSuccess = false;
      alertify.success('Promoção adicionada!');
    });
  };

  $scope.options = {
    minDate: new Date(),
    showWeeks: true
  };

  $scope.limpar = function limpar() {
    $scope.promocao = {};
  };

}]);
