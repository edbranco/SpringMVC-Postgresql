angular.module("serviceModule")
        .factory("restService", function ($http) {
            return {
                fireGet: function (url, params, success, failure) {
                    $http({
                        url: url,
                        method: 'GET',
                        async: true,
                        cache: false,
                        headers: {'Accept': 'application/json', 'Pragma': 'no-cache'}
                    }).success(function (data) {
                        success(data);
                    }).error(function (error, status) {
                        failure(error);
                    }).finally(function () {
                    }).catch(function (error) {
                    });
                },
                firePost: function (url, postdata, success, failure) {
                    $http({
                        url: url,
                        method: 'POST',
                        data: JSON.stringify(postdata),
                        async: true,
                        cache: false,
                        contentType: "application/json; charset=utf-8",
                        headers: {'Accept': 'text/plain;charset=utf-8', 'Pragma': 'no-cache'}
                    }).success(function (data) {
                        success(data);
                    }).error(function (error, status) {
                        failure(error);
                    }).finally(function () {
                    }).catch(function (error) {
                    });
                },
                firePostAcceptText: function (url, postdata, success, failure) {
                    $http({
                        url: url,
                        method: 'POST',
                        data: JSON.stringify(postdata),
                        async: true,
                        cache: false,
                        headers: {'Accept': 'text/plain', 'Pragma': 'no-cache'}
                    }).success(function (data) {
                        success(data);
                    }).error(function (error, status) {
                        failure(error);
                    }).finally(function () {
                    }).catch(function (error) {
                    });
                }
            };
        });