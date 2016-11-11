angular.module('vendasPlusApp').controller('resgateCtrl', ['$scope', '$uibModal', '$http', function($scope, $uibModal, $http){

  $scope.produtos = [
    {id: 1, nomeProduto: 'Produto 1', img: 'mixer', pontosRecompensa: 250},
    {id: 2, nomeProduto: 'Produto 2', img: 'liquidificador', pontosRecompensa: 780},
    {id: 3, nomeProduto: 'Produto 3', img: 'mixer', pontosRecompensa: 2500},
  ]


  $scope.resgateProduto = function resgateProduto(produto){
    $uibModal.open(
     {
       templateUrl: '../pages/empresa/confirmacao-resgate.html',
       controller: 'confirmacaoCtrl',
       size: 'md',
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
