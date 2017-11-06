angular.module('vendasPlusApp').controller('cadastroPromocaoCtrl', ['$scope', '$http', 'alertify', 'Upload', '$timeout', function($scope, $http, alertify, Upload, $timeout){

  $scope.promocao = {};
  $scope.promocao.inicioCampanha = new Date();
  $scope.loadingSuccess = false;

  $http.get('/r/controller/user').then(function(resp) {
	  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
		  $scope.user = resp.data;
	  });
  });

  $scope.save = function save(file) {
    $scope.loadingSuccess = true;
    $scope.promocao.idEmpresa = $scope.user.idEmpresa;
    $scope.promocao.img = $scope.fileName;
    $http.post('/r/empresa/cadastrarCampanha', $scope.promocao).then(function(resp) {
      $scope.promocao = {};
      $scope.loadingSuccess = false;
      alertify.success('Promoção adicionada!');
    }, function(err) {
        alertify.success('Promoção não adicionada');
    });
  };

  $scope.sendPhoto = function() {
	  
	  var form = $('<form action="/r/controller/upload" method="post" enctype="multipart/form-data"></form>');
	  var input = $('.picInput');
	  var path = input.val();
	  
	  $scope.fileName = path.replace('C:\\fakepath\\', '');
	  form.append(input);
	  
	  form.ajaxSubmit();
	  $timeout(function() {
		  $scope.save();
	  }, 1000);
  };

  $scope.options = {
    minDate: new Date(),
    showWeeks: true
  };

  $scope.validForm = function() {
    return $scope.promocao.vigenciaCampanha && $scope.promocao.nomeProduto && $scope.promocao.pontosRecompensa;
  }

  $scope.limpar = function limpar() {
    $scope.promocao = {};
  };

}]);
