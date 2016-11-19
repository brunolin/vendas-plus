angular.module('vendasPlusApp').controller('cadastroNotaCtrl', ['$scope', '$http', 'alertify', function($scope, $http, alertify){

  $scope.venda = {};
  $scope.loadingSuccess = false;

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
    $scope.produtos = resp.data;
  });

  $scope.status = {
    isopen: false
  };

  $scope.nomeProduto = function(produto){
    $scope.venda.nomeProduto = produto.nomeProduto;
    $scope.venda.idProduto = produto.idProduto;
    $scope.venda.idVendedor = 1;
    $scope.venda.idEmpresa = produto.idEmpresa;

  };

  $scope.options = {
    maxDate: new Date(),
    showWeeks: true
  };

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };

  $scope.limpar = function limpar() {
    $scope.venda = {};
  };

  $scope.save = function save(){
    $scope.loadingSuccess = true;
	  $http.post('/r/vendedor/cadastrarNota', $scope.venda).then(function(resp) {
      $scope.loadingSuccess = false;
      alertify.success('Nota adicionada!');
      $scope.venda = {};
	  })
  }

  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
}]);
