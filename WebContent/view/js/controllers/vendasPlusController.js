angular.module('vendasPlusApp', ['ngAnimate', 'ngAlertify', 'ui.bootstrap']).controller('mainCtrl', ['$scope', '$uibModal', function($scope, $uibModal){

  $scope.menu = {};

  $scope.menuSelected = function menuSelected(item){
    $scope.menu.active = item
    $scope.selectedTemplate = 'empresa/' + item + '.html';
  };

  $scope.produtos = [
    {id: 1, nome: 'Produto 1', img: 'mixer', pontos: 170},
    {id: 2, nome: 'Produto 2', img: 'liquidificador', pontos: 120},
    {id: 3, nome: 'Produto 3', img: 'mixer', pontos: 170},
    {id: 1, nome: 'Produto 4', img: 'liquidificador', pontos: 120},
    {id: 2, nome: 'Produto 5', img: 'liquidificador', pontos: 120}
  ];

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
