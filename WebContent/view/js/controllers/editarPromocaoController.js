angular.module('vendasPlusApp').controller('editarPromocaoCtrl', ['$scope', '$http', '$uibModal', 'alertify', function($scope, $http, $uibModal, alertify){
  $scope.promocoes = [];

	$http.get('/r/empresa/getCampanhas/' +  71).then(function(resp) {
		$scope.promocoes = resp.data;
	});

	$scope.promocoes.push({idProduto:111, nomeProduto: 'zzz', dataVenda:'aaa'});

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
        $http.get('/r/empresa/getCampanhas/' +  71).then(function(resp) {
      		$scope.promocoes = resp.data;
      	});
			});
  	}

}]);
