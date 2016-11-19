angular.module('vendasPlusApp').controller('editarPromocaoModalCtrl', ['$scope', '$uibModal', '$http', 'params', '$uibModalInstance', 'alertify', function($scope, $uibModal, $http , params, $uibModalInstance, alertify){

    $scope.promocao = params;
    $scope.loadingSuccess = false;
    $scope.modal = $uibModalInstance;

   	$scope.confirmar = function confirmar() {
      $scope.loadingSuccess = true;

  		$http.post('/r/empresa/atualizarCampanha', $scope.promocao).then(function(resp) {
        $scope.loadingSuccess = false;
        alertify.success('Promoção alterada');

  			$uibModalInstance.close(resp);
  		});
  	};

    $scope.options = {
      minDate: new Date(),
      showWeeks: true
    };
}]);
