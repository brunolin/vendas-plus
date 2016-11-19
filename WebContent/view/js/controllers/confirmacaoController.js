angular.module('vendasPlusApp').controller('confirmacaoCtrl', ['$scope', '$uibModal', '$http', 'params', '$uibModalInstance', function($scope, $uibModal, $http , params, $uibModalInstance){
  $scope.param = params;

   	$scope.confirmar = function confirmar(nota) {
  		$http.post('/r/empresa/confirmarNota', nota).then(function(resp) {
  			$uibModalInstance.close(resp);
  		});
  	};

  	$scope.reprovar = function reprovar(nota) {
  		$http.post('/r/empresa/reprovarNota', nota).then(function(resp) {
  			$uibModalInstance.close(resp);
  		});
  	};
}]);
