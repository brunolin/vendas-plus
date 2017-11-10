angular.module('vendasPlusApp').controller('cadastroNotaCtrl', ['$scope', '$http', 'alertify', function($scope, $http, alertify){

  $scope.venda = {};
  $scope.loadingSuccess = false;
  $scope.image64 = null;

  $http.get('/r/vendedor/getCampanhas').then(function(resp) {
    $scope.produtos = resp.data;
  });

  $http.get('/r/controller/user').then(function(resp) {
	  $http.post('/r/vendedor/getInfoVendedor', resp.data).then(function(resp) {
		  $scope.user = resp.data;
	  });
  });

  $scope.status = {
    isopen: false
  };

  $scope.options = {
    maxDate: new Date(),
    showWeeks: true
  };

  $scope.nomeProduto = function(produto){
    $scope.venda.nomeProduto = produto.nomeProduto;
    $scope.venda.idProduto = produto.idProduto;
    $scope.venda.idVendedor = $scope.user.idVendedor;
    $scope.venda.idEmpresa = produto.idEmpresa;

    minDate();
  };

  function minDate() {
    $scope.produtos.forEach(function(produto) {
      if($scope.venda.idProduto == produto.idProduto) {
        $scope.options.minDate = produto.inicioCampanha;
        return;
      }
    });
  };

  $scope.validForm = function() {
    return $scope.venda.idVenda && !!$scope.venda.data && ($scope.venda.idProduto || $scope.venda.idProduto == 0);
  }

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };

  $scope.limpar = function limpar() {
    $scope.venda = {};
  };

  $scope.save = function save(){
    $scope.loadingSuccess = true;
    $scope.venda.img = $scope.fileName;
	  $http.post('/r/vendedor/cadastrarNota', $scope.venda).then(function(resp) {
      $scope.loadingSuccess = false;
      alertify.success('Nota adicionada!');
      $scope.venda = {};
	  }, function(err) {
        alertify.success('Nota não adicionada');
    });
  }

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

  $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
}]);
