angular.module('vendasPlusApp').controller('resgateCtrl', ['$scope', '$uibModal', '$http', function($scope, $uibModal, $http){

  $scope.produtos = [];
  
  $http.get('/r/controller/user').then(function(resp) {
	  $scope.type = resp.data.type;
	  $scope.cpf = resp.data.username;
	  $http.post('/r/vendedor/getInfoVendedor', resp.data).then(function(resp) {
		  $scope.user = resp.data; 
	  });		  
});
  
  $http.get('/r/vendedor/getBonus').then(function(resp) {
	  $scope.produtos = resp.data;
  });
  
  $scope.canGetItem = function canGetItem(produto) {
	  if($scope.user.pontos >= produto.pontosNecessarios){
		  return true;
	  }
	  
	  return false;
  };

  $scope.resgateProduto = function resgateProduto(produto){
    $uibModal.open(
     {
       templateUrl: '../pages/empresa/confirmacao-resgate.html',
       controller: 'confirmacaoCtrl',
       size: 'md',
       resolve: {
            params: function () {
                return {
                	'produto': produto,
                	'cpf': $scope.cpf 
                }
            }
      }
    });
  }

  $scope.exec = function exec(){
    console.log(params.produto);
  }

}]);
