angular.module('vendasPlusApp').controller('confirmacaoCtrl', ['$scope', '$uibModal', '$http', 'params', '$uibModalInstance', 'alertify', function($scope, $uibModal, $http , params, $uibModalInstance, alertify){

    $scope.param = params;
    $scope.loadingSuccess = false;
    $scope.loadingDanger = false;
    $scope.modal = $uibModalInstance;

   	$scope.confirmar = function confirmar(nota) {
      $scope.loadingSuccess = true;

  		$http.post('/r/empresa/confirmarNota', nota).then(function(resp) {
        $scope.loadingSuccess = false;
        alertify.success('Nota Aprovada');

  			$uibModalInstance.close(resp);
  		});
  	};

  	$scope.reprovar = function reprovar(nota) {
      $scope.loadingDanger = true;

  		$http.post('/r/empresa/reprovarNota', nota).then(function(resp) {
        $scope.loadingDanger = false;
        alertify.success('Nota Reprovada');

  			$uibModalInstance.close(resp);
  		});
  	};

    $scope.confirmarResgate = function confirmarResgate() {
      $scope.loadingSuccess = true;
    }


}]);
