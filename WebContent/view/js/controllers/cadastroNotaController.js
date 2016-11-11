angular.module('vendasPlusApp').controller('cadastroNotaCtrl', ['$scope', '$http', function($scope, $http){

  $http.get('/vendas-plus/r/vendedor/getCampanhas').then(function(resp) {
    $scope.produtos = resp.data;
  });
  
  $scope.items = [
                  'The first choice!',
                  'And another choice for you.',
                  'but wait! A third!'
                ];

                $scope.status = {
                  isopen: false
                };

                $scope.toggleDropdown = function($event) {
                  $event.preventDefault();
                  $event.stopPropagation();
                  $scope.status.isopen = !$scope.status.isopen;
                };

                $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
}]);
