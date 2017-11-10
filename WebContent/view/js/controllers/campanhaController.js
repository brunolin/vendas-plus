angular.module('vendasPlusApp').controller('campanhaCtrl', ['$scope', '$uibModal', '$http', function($scope, $uibModal, $http){

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
	  console.log(resp);
	  $scope.produtos = resp.data;
  });
  
  $scope.getImage = function getImage(img) {
      return '../../photos/' + img;
  }

}]);
