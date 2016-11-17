angular.module('vendasPlusApp').controller('cadastroPromocaoCtrl', ['$scope', '$http', function($scope, $http){

  $scope.promocao = {};
  $scope.promocao.inicioCampanha = new Date();

  $scope.save = function save() {
    $http.post('/r/empresa/cadastrarCampanha', $scope.promocao).then(function(resp) {
      console.log(resp);
    });
  };

  $scope.options = {
    minDate: new Date(),
    showWeeks: true
  };

}]);
