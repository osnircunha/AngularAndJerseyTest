/**
 * Created by osnircunha on 9/24/15.
 */

app.controller('candidatesController', function($rootScope, $scope,
  $stateParams, notification, $http, $modal) {

  $scope.saveCandidate = function() {
    try {
      if ($scope.candidate.name && $scope.candidate.votes) {
        var req = {
          method: 'POST',
          url: 'rest/candidates',
          data: $scope.candidate
        };
        $http(req).then(function(resp) {
          notification.showSuccess('Candidato salvo.');
          $scope.listCandidates();
        }, function(resp) {
          throw resp;
        });
      } else {
        notification.showError('Erro ao salvar candidato.');
      }
    } catch (err) {
      notification.showError('Erro ao salvar candidato. ' + err.message);
    }
  };

  $scope.updateCandidate = function() {
    try {
      if ($scope.candidate.name && $scope.candidate.votes) {
        var req = {
          method: 'PUT',
          url: 'rest/candidates',
          data: $scope.candidate
        };
        $http(req).then(function(resp) {
          notification.showSuccess('Candidato atualizado.');
          $scope.listCandidates();
        }, function(resp) {
          throw resp;
        });
      } else {
        notification.showError('Erro ao salvar candidato.');
      }
    } catch (err) {
      notification.showError('Erro ao salvar candidato. ' + err.message);
    }
  };

  $scope.listCandidates = function() {
    $http({
      method: 'GET',
      url: 'rest/candidates'
    }).then(function(resp) {
      $scope.candidates = resp.data;
    }, function(resp) {
      console.error('Erro ao listar candidatos.');
    });
  };

  $scope.getCandidate = function(id) {
    $http({
      method: 'GET',
      url: 'rest/candidates/' + id
    }).then(function(resp) {
      $scope.candidateDetail = resp.data;
    }, function(resp) {
      notification.showError('Erro ao recuperar candidato.');
    });
  };

  $scope.deleteCandidate = function(id) {
    $http({
      method: 'DELETE',
      url: 'rest/candidates/' + id
    }).then(function(resp) {
      notification.showSuccess('Candidato removido.');
      $scope.listCandidates();
    }, function(resp) {
      notification.showError('Erro ao recuperar candidato.');
    });
  };

  $scope.showDetailModal = function(id) {
    $modal.open({
      templateUrl: "pages/directives/view-candidate-modal.html",
      resolve: {
        id: function() {
          return id;
        }
      },
      controller: function(id) {
        $scope.getCandidate(id);

        $scope.no = function() {
          this.$dismiss();
        };
      },
      scope: $scope,
      size: 'sm',
    })
  }

  $scope.showDeleteModal = function(candidate) {
    $modal.open({
      templateUrl: "pages/directives/delete-candidates-modal.html",
      resolve: {
        c: function() {
          return candidate;
        }
      },
      controller: function(c) {
        $scope.c = c;

        $scope.yes = function(id) {
          $scope.deleteCandidate(id);
          this.$dismiss();
        };
        $scope.no = function() {
          this.$dismiss();
        };
      },
      scope: $scope,
      size: 'sm',
    })
  }

  $scope.showNewModal = function(action, c) {
    $modal.open({
      resolve: {
        action: function() {
          return action;
        },
        c: function() {
          return c;
        }
      },
      templateUrl: "pages/directives/new-candidate-modal.html",
      controller: function(action, c) {
        $scope.action = action;
        $scope.salvar = function(candidate) {
          $scope.candidate = candidate;
          $scope.saveCandidate();
          this.$dismiss();
        };
        if (c) {
          $scope.candidate = JSON.parse(JSON.stringify(c));
        }
        $scope.atualizar = function(candidate) {
          $scope.updateCandidate();
          this.$dismiss();
        };
        $scope.no = function() {
          this.$dismiss();
        };
      },
      scope: $scope,
      size: 'sm',
    })
  }

  $scope.listCandidates();

});
