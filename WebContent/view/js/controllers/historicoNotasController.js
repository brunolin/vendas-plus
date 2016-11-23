angular.module('vendasPlusApp').controller('historicoNotasCtrl', ['$scope', '$http', function($scope, $http){
  
  $http.get('/r/controller/user').then(function(resp) {
	  $scope.type = resp.data.type;
	  if(resp.data.type == 'empresa'){
		  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
			  $scope.user = resp.data;
			  $http.get('/r/empresa/getNotasEmpresa/' + resp.data.idEmpresa).then(function(resp) {
				    $scope.notas = resp.data;
			  });
		  });		  
	  } else {
		  $http.post('/r/vendedor/getInfoVendedor', resp.data).then(function(resp) {
			  $scope.user = resp.data;
			  $http.get('/r/vendedor/getNotasVendedor/' + resp.data.idVendedor).then(function(resp) {
				    $scope.notas = resp.data;
			  });
		  });			  
	  }

  });

  $scope.getStatus = function getStatus(status) {
  	if(status == 'T') {
  		return 'Aprovada';
  	} else if(status == 'F') {
  		return 'Em avaliação';
  	} else {
  		return 'Reprovada';
  	}

  };
}]);
