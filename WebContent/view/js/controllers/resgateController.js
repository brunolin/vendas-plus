angular.module('vendasPlusApp').controller('resgateCtrl', ['$scope', '$uibModal', '$http', function($scope, $uibModal, $http){

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
	  console.log(resp);
	  $scope.produtos = resp.data; 
  });

  $scope.resgateProduto = function resgateProduto(produto){
    $uibModal.open(
     {
       templateUrl: '../pages/empresa/confirmacao-resgate.html',
       controller: 'confirmacaoCtrl',
       size: 'sm',
       resolve: {
            params: function () {
                return produto
            }

      }
    });
  }

  $scope.exec = function exec(){
    console.log(params.produto);
  }
  
}]);