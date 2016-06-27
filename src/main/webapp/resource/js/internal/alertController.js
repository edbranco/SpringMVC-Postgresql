angular.module('TelesulApp')
        .controller('alertController', function ($scope) {
//            $scope.alerts = [
//                {type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.'},
//                {type: 'success', msg: 'Well done! You successfully read this important alert message.'}
//            ];
//            $(document).ready(function () {
//                var div = $($element).context;
//                var e = angular.element(div);
//                $(e).find("div").fadeOut(1000);
//            })
            $scope.alerts = [];
            $scope.$on("broadCastAlert", function (event, args) {
                $scope.addAlert(args.type, args.msg);
            });
            $scope.$on("broadCastRemoveAlerts", function (event, args) {
                $scope.alerts = [];
            });

            $scope.addAlert = function (type, msg) {
                $scope.alerts.push({type: type, msg: msg});
            };

            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
            $scope.removeAllAlerts = function () {
                $scope.alerts = [];
            };
        });