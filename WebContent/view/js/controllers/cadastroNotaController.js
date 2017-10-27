angular.module('vendasPlusApp').controller('cadastroNotaCtrl', ['$scope', '$http', 'alertify', function($scope, $http, alertify){

  $scope.venda = {};
  $scope.loadingSuccess = false;

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
    $scope.produtos = resp.data;
  });

  $http.get('/r/controller/user').then(function(resp) {
	  $http.post('/r/vendedor/getInfoVendedor', resp.data).then(function(resp) {
		  $scope.user = resp.data;
	  });
  });

  $scope.status = {
    isopen: false
  };

  $scope.nomeProduto = function(produto){
    $scope.venda.nomeProduto = produto.nomeProduto;
    $scope.venda.idProduto = produto.idProduto;
    $scope.venda.idVendedor = $scope.user.idVendedor;
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

  $scope.getImage = function() {
    if($scope.image64) {
      return 'data:image/jpeg;base64,' + $scope.image64.base64;
    }
   }

  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
}]);
