angular.module('vendasPlusApp').controller('historicoNotasCtrl', ['$scope', '$http', function($scope, $http){
  $http.get('/r/vendedor/getNotasVendedor').then(function(resp) {
    $scope.notas = resp.data;
  });
}]);
