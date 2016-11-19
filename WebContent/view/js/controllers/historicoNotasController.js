angular.module('vendasPlusApp').controller('historicoNotasCtrl', ['$scope', '$http', function($scope, $http){
  $http.get('/r/vendedor/getNotasVendedor').then(function(resp) {
    $scope.notas = resp.data;
  });

  $scope.getStatus = function getStatus(status) {
  	if(status == 'T') {
  		return 'Aprovada';
  	} else if(status == 'X') {
  		return 'Não aprovada';
  	} else {
  		return 'Reprovada';
  	}

  };
}]);
