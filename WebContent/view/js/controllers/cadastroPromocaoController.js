angular.module('vendasPlusApp').controller('cadastroPromocaoCtrl', ['$scope', '$http', 'alertify', function($scope, $http, alertify){

  $scope.promocao = {};
  $scope.promocao.inicioCampanha = new Date();
  $scope.loadingSuccess = false;
  
  $http.get('/r/controller/user').then(function(resp) {
	  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
		  $scope.user = resp.data; 
	  });
  });

  $scope.save = function save() {
    $scope.loadingSuccess = true;
    $scope.promocao.idEmpresa = $scope.user.idEmpresa;
    $scope.promocao.img = $scope.image64.base64;
    $http.post('/r/empresa/cadastrarCampanha', $scope.promocao).then(function(resp) {
      $scope.promocao = {};
      $scope.loadingSuccess = false;
      alertify.success('Promoção adicionada!');
    }, function(err) {
        alertify.success('Promoção não adicionada');        
    });
  };

  $scope.options = {
    minDate: new Date(),
    showWeeks: true
  };

  $scope.validForm = function() {
    return $scope.promocao.vigenciaCampanha && $scope.promocao.nomeProduto && $scope.promocao.pontosRecompensa;
  }
  
  $scope.getImage64 = function getImage64(img) {
    if($scope.image64) {
      return 'data:image/jpeg;base64,' + $scope.image64.base64;
    }
  }

  $scope.limpar = function limpar() {
    $scope.promocao = {};
  };

}]);
