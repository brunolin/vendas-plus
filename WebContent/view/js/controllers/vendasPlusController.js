angular.module('vendasPlusApp', ['ngFileUpload', 'ngAnimate', 'ngAlertify', 'ui.bootstrap', 'naif.base64']).controller('mainCtrl', ['$scope', '$uibModal', 'alertify', '$http', function($scope, $uibModal, alertify, $http){

  $scope.menu = {};
  $scope.user = undefined;

  $scope.menuSelected = function menuSelected(item){
    $scope.menu.active = item
    $scope.selectedTemplate = 'empresa/' + item + '.html';
  };

  $http.get('/r/controller/user').then(function(resp) {
	  $scope.type = resp.data.type;
	  if($scope.type == 'vendedor'){
		  $http.post('/r/vendedor/getInfoVendedor', resp.data).then(function(resp) {
			  $scope.user = resp.data;
		  });
	  } else {
		  $http.post('/r/empresa/getInfoEmpresa', resp.data).then(function(resp) {
			  $scope.user = resp.data;
		  });
	  }

  });
  
  $scope.logout = function logout() {
	  $http.get('/r/controller/logout').then(function(resp) {
		  if(resp.data){
			  window.location = '/';
		  }
	  });
  }

  alertify.defaults = {
        // dialogs defaults
        autoReset:true,
        basic:false,
        closable:true,
        closableByDimmer:true,
        frameless:false,
        maintainFocus:true, // <== global default not per instance, applies to all dialogs
        maximizable:true,
        modal:true,
        movable:true,
        moveBounded:false,
        overflow:true,
        padding: true,
        pinnable:true,
        pinned:true,
        preventBodyShift:false, // <== global default not per instance, applies to all dialogs
        resizable:true,
        startMaximized:false,
        transition:'pulse',

        // notifier defaults
        notifier:{
            // auto-dismiss wait time (in seconds)
            delay:5,
            // default position
            position:'bottom-right'
        },

        // language resources
        glossary:{
            // dialogs default title
            title:'AlertifyJS',
            // ok button text
            ok: 'OK',
            // cancel button text
            cancel: 'Cancel'
        },

        // theme settings
        theme:{
            // class name attached to prompt dialog input textbox.
            input:'ajs-input',
            // class name attached to ok button
            ok:'ajs-ok',
            // class name attached to cancel button
            cancel:'ajs-cancel'
        }
    };

}]);
