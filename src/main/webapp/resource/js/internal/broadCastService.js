angular.module("serviceModule")
        .factory("broadCastService", function ($rootScope,$sce) {
            function broadCastAlert(type, msg, append) {
                if (!append) {
                    broadCastRemoveAlerts();
                }
                $rootScope.$broadcast("broadCastAlert", {
                    type: type, msg: $sce.trustAsHtml(msg)
                });
            }
            function broadCastRemoveAlerts() {
                $rootScope.$broadcast("broadCastRemoveAlerts", {});
            }
            function broadCastGroup(type, title, messages, append) {
                var groupMessage = "<div class='panel panel-danger'>\n\
                                            <div class='panel-heading'>" + title + " <span class='badge'>" + messages.length + "</span></div>";
                groupMessage += "<ul class='list-group'>";
                angular.forEach(messages, function (msg) {
                    groupMessage += "<li class='list-group-item'>" + msg + "</li>";
                });
                groupMessage += "</ul></div>";
                broadCastAlert(type, groupMessage, append);
            }
            return {
                broadCastAlertSuccess: function (msg, append) {
                    broadCastAlert('success', msg, append);
                },
                broadCastAlertDanger: function (msg, append) {
                    broadCastAlert('danger', msg, append);
                },
                broadCastAlertInfo: function (msg, append) {
                    broadCastAlert('info', msg, append);
                },
                broadCastAlertWarning: function (msg, append) {
                    broadCastAlert('warning', msg, append);
                },
                broadCastAlertDangerGroup: function (title, messages, append) {
                    broadCastGroup('danger', title, messages, append);
                },
                broadCastAlertInfoGroup: function (title, messages, append) {
                    broadCastGroup('info', title, messages, append);
                },
                broadCastAlertSuccessGroup: function (title, messages, append) {
                    broadCastGroup('success', title, messages, append);
                },
                broadCastAlertWarningGroup: function (title, messages, append) {
                    broadCastGroup('warning', title, messages, append);
                },
                broadCastAlert: function (type, msg, append) {
                    broadCastAlert(type, msg, append);
                },
                removeAlerts: function () {
                    broadCastRemoveAlerts();
                }
            };
        });