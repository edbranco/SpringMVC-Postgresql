/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module("TelesulApp");
app.controller("callClassificationController", function ($scope, restService, broadCastService, commonUtils) {
    $scope.dataEntity = {};
    commonUtils.setDateTimePicker();
    $scope.classifications = [
        "Caixa Postal",
        "Telefone Indisponível",
        "Telefone Inexistente",
        "Ligação Não Completada",
        "Contato Inválido"
    ];

    $scope.select = function (classification) {
        $scope.dataEntity.classification = classification;
    };
    function analyse() {
        if (commonUtils.isUndefinedOrNullOrEmpty($scope.dataEntity.classification)) {
            broadCastService.broadCastAlertDanger("Por favor escolha uma opção");
            return false;
        }
        if (commonUtils.isUndefinedOrNullOrEmpty($scope.dataEntity.loginID)) {
            broadCastService.broadCastAlertDanger("Por favor, dar o seu login id");
            return false;
        }
        if ($scope.inputTime !== true) {
            $scope.dataEntity.receivedDate = moment().format('DD-MM-YYYY HH:mm');
        } else {
            $scope.dataEntity.receivedDate = moment($("#CallTime").val(), 'DD-MM-YYYY HH:mm').format('DD-MM-YYYY HH:mm');
        }
        return true;
    }
    $scope.saveCallClassification = function () {
        if (analyse()) {
            restService.firePostAcceptText("callclassify/save", $scope.dataEntity, function (data, status, headers, config) {
                broadCastService.broadCastAlertSuccess(data);
            },
                    function (error, status) {
                        broadCastService.broadCastAlertDanger("Erro: Id ou Status incorretos");
                    });
        }
    };
});