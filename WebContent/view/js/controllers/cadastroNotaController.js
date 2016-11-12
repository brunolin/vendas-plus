angular.module('vendasPlusApp').controller('cadastroNotaCtrl', ['$scope', '$http', function($scope, $http){
  $scope.venda = {};

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
    $scope.produtos = resp.data;
  });

  $scope.status = {
    isopen: false
  };

  $scope.nomeProduto = function(produto){
    $scope.venda.nomeProduto = produto.nomeProduto;
    $scope.venda.idProduto = produto.idProduto;
    $scope.venda.idVendedor = 0;

  };

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };
  
  $scope.save = function save(){
	  $http.post('/r/vendedor/cadastrarNota', $scope.venda).then(function(resp) {
		  console.log(resp);
	  })
  }

  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
}]);
