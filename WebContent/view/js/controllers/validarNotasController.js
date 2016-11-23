angular.module('vendasPlusApp').controller('validarNotasCtrl', ['$scope', '$http', '$uibModal', 'alertify', function($scope, $http, $uibModal, alertify){

	$scope.notas = [];
	  $http.get('/r/controller/user').then(function(resp) {
		  $scope.type = resp.data.type;
		  if(resp.data.type == 'empresa'){
			  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
				  $scope.user = resp.data;
				  $http.get('/r/empresa/notasPendentes/' +  resp.data.idEmpresa).then(function(resp) {
				      $scope.notas = resp.data;
				  });
			  });		  
		  }
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
	    	$http.get('/r/empresa/notasPendentes/' +  $scope.user.idEmpresa).then(function(resp) {
				$scope.notas = resp.data;
				});
			});
  	}
}]);
