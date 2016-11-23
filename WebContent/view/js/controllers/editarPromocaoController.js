angular.module('vendasPlusApp').controller('editarPromocaoCtrl', ['$scope', '$http', '$uibModal', 'alertify', function($scope, $http, $uibModal, alertify){
	  $scope.promocoes = [];
	  
	  $http.get('/r/controller/user').then(function(resp) {
		  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
	  		  $scope.user = resp.data;
	  		  
				$http.get('/r/empresa/getCampanhas/' +  resp.data.idEmpresa).then(function(resp) {
					$scope.promocoes = resp.data;
				});
		  });
	  });

	$scope.validar = function validar(promocao){
	    var modal = $uibModal.open(
	     {
	       templateUrl: '../pages/empresa/editar-promocao-modal.html',
	       controller: 'editarPromocaoModalCtrl',
	       size: 'md',
	       resolve: {
	            params: function () {
	                return promocao
	            }
	       }
	    });

	    modal.result.then(function (response) {
        $http.get('/r/empresa/getCampanhas/' +  $scope.user.idEmpresa).then(function(resp) {
      		$scope.promocoes = resp.data;
      	});
			});
  	}

}]);
