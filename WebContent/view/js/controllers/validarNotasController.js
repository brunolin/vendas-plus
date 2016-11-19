angular.module('vendasPlusApp').controller('validarNotasCtrl', ['$scope', '$http', '$uibModal', 'alertify', function($scope, $http, $uibModal, alertify){

	$scope.notas = [];

	$http.get('/r/empresa/notasPendentes/' +  71).then(function(resp) {
		$scope.notas = resp.data;
	});

	$scope.validar = function validar(nota){
	    var modal = $uibModal.open(
	     {
	       templateUrl: 'confirmar-venda.html',
	       controller: 'confirmacaoCtrl',
	       size: 'sm',
	       resolve: {
	            params: function () {
	                return nota
	            }
	       }
	    });

	    modal.result.then(function (response) {
	    	$http.get('/r/empresa/notasPendentes/' +  71).then(function(resp) {
				$scope.notas = resp.data;
				});
			});
  	}
}]);
