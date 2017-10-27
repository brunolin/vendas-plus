angular.module('vendasPlusApp').controller('campanhaCtrl', ['$scope', '$uibModal', '$http', function($scope, $uibModal, $http){

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
	  console.log(resp);
	  $scope.produtos = resp.data;
  });
  
  $scope.getImage64 = function getImage64(img) {
      return 'data:image/jpeg;base64,' + img;
  }

}]);
