<%-- 
    Document   : tab
    Created on : 17/06/2016, 15:51:10
    Author     : ebranco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="TelesulApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ZAP</title>
        <link rel="stylesheet" href="resource/css/jquery-ui.min.css">
        <link rel="stylesheet" href="resource/css/jquery-ui.theme.min.css">
        <script src="resource/js/moment.min.js" type="text/javascript"></script>
        <script src="resource/js/moment-with-locales.min.js" type="text/javascript"></script>
        <script src="resource/js/jquery-2.1.4.js" type="text/javascript"></script>
        <script src="resource/js/jquery-ui.js" type="text/javascript"></script>


        <script src="resource/js/angular.js" type="text/javascript"></script>

        <script src="resource/js/jquery-ui.min.js"></script>
        <script src="resource/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <script src="resource/js/ui-bootstrap-tpls-0.12.1.min.js" type="text/javascript"></script>


        <link href="resource/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/jquery-ui-timepicker-addon.min.css" rel="stylesheet" type="text/css"/>
        <!--<link href="resource/css/default.css" rel="stylesheet" type="text/css"/>-->
        <script src="resource/js/internal/serviceModule.js" type="text/javascript"></script>
        <script type="text/javascript">
            var projectApp = angular.module("TelesulApp", ["serviceModule", "ui.bootstrap"]);
        </script>
        <script src="resource/js/internal/restService.js" type="text/javascript"></script>
        <script src="resource/js/internal/commonUtils.js" type="text/javascript"></script>
        <script src="resource/js/internal/callClassificationController.js" type="text/javascript"></script>
        <script src="resource/js/internal/alertController.js" type="text/javascript"></script>

        <script src="resource/js/internal/broadCastService.js" type="text/javascript"></script>
        <script src="resource/js/internal/jquery-ui-timepicker-addon.min.js" type="text/javascript"></script>
        <style>
            input.datetimefield,input.datetimerange,
            input.datefield,input.daterange,
            input.timefield,input.timerange{
                display: inline-block;
                width: 80%;
            }
        </style>
    </head>
    <body ng-controller="callClassificationController">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h2>Status</h2>
                        </div>
                        <div class="panel-body">
                            <div ng-controller="alertController" id="allalerts">
                                <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</alert>
                            </div>
                            <form name="createContactForm" class="form-horizontal" role="form" novalidate>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="inputTime">Tempo Especificado</label>
                                    <div class="col-sm-2">
                                        <input type="checkbox" ng-model="inputTime">
                                    </div>
                                </div>
                                <div class="form-group" ng-show="inputTime">
                                    <label class="control-label col-sm-2" for="CallTime">Tempo de ligação</label>
                                    <div class="col-sm-3">
                                        <input id="CallTime" name="CallTime" ng-model="receivedDate" class="form-control datetimefield"></input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="loginID">Login ID</label>
                                    <div class="col-sm-3">
                                        <input id="loginID" name="loginID" ng-model="dataEntity.loginID" class="form-control"></input>
                                    </div>
                                    <label class="control-label col-sm-2" for="callClassification">Classificação de Ligação</label>
                                    <div class="col-sm-5">
                                        <div ng-repeat="classification in classifications">
                                            <label class="checkbox-inline ct">
                                                <input class="ct" type="radio" name="classification" ng-click="select(classification)">
                                                {{classification}}
                                            </label>
                                            </td>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group"> 
                                    <div class="col-sm-push-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary" ng-click="saveCallClassification()"><span class="glyphicon glyphicon-floppy-save"/> Salvar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 pull-right">
                    <p style="text-align: right">By <b>Lalit</b> &copy; 2016 Telesul Serviços</p>
                </div>
            </div>
        </div>

    </body>
</html>
