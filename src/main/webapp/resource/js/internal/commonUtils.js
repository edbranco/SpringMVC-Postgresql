angular.module("serviceModule")
        .factory("commonUtils", function () {
            var calenderImage = "resource/images/calendar_month.png";
            function cleanArray(sourceArray, indexArray) {
                var arr = $.grep(sourceArray, function (n, i) {
                    return $.inArray(i, indexArray) === -1;
                });
                return arr;
            }
            return {
                removeElementsSelected: function (sourceArray) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.selected) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                removeElementsAdded: function (sourceArray) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.added) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                removeElementsChecked: function (sourceArray) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.checked) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                markElementsCheckedToRemove: function (sourceArray) {
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.checked) {
                            item.markedtoremove = true;
                        }
                    });
                    return sourceArray;
                },
                selectAll: function (sourceArray) {
                    angular.forEach(sourceArray, function (item, index) {
                        item.checked = true;
                    });
                    return sourceArray;
                },
                unSelectAll: function (sourceArray) {
                    angular.forEach(sourceArray, function (item, index) {
                        item.checked = false;
                    });
                    return sourceArray;
                },
                unmarkElementsMarkedForRemoval: function (sourceArray) {
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.checked) {
                            item.markedtoremove = false;
                        }
                    });
                    return sourceArray;
                },
                removeElementsMarkedToRemove: function (sourceArray) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.markedtoremove) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                addElementsMarkedToAdd: function (into, from) {
                    angular.forEach(from, function (item) {
                        if (item.markedtoadd) {
                            var copy = angular.copy(item);
                            copy.id = null;
                            into.push(copy);
                        }
                    });
                },
                removeElementsCheckedAndReturnRemovedOnesAndRemainingOnes: function (sourceArray) {
                    var indexArray = [];
                    var toDeleteArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (item.checked) {
                            indexArray.push(index);
                            toDeleteArray.push(item);
                        }
                    });
                    var ret = {};
                    ret.remaining = cleanArray(sourceArray, indexArray);
                    ret.deleted = toDeleteArray;
                    return ret;
                },
                keepElement: function (sourceArray, keepIndex) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (index !== keepIndex) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                removeElementsAfterIndex: function (sourceArray, afterIndex) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (index > afterIndex) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                removeElementsBeforeIndex: function (sourceArray, beforeIndex) {
                    var indexArray = [];
                    angular.forEach(sourceArray, function (item, index) {
                        if (index < beforeIndex) {
                            indexArray.push(index);
                        }
                    });
                    return cleanArray(sourceArray, indexArray);
                },
                isUndefinedOrNullOrEmpty: function (val) {
                    return angular.isUndefined(val) || val === null || val === '';
                },
                isDefinedAndNotEmpty: function (val) {
                    return angular.isDefined(val) && val !== null && val !== '';
                },
                isArrayEmpty: function (array) {
                    if (angular.isArray(array)) {
                        if (array.length === 0)
                            return true;
                    }
                    return false;
                },
                isArrayNotEmpty: function (array) {
                    if (angular.isArray(array)) {
                        if (array.length > 0)
                            return true;
                    }
                    return false;
                },
                markIdsNull: function (array) {
                    angular.forEach(array, function (item) {
                        item.id = null;
                    });
                },
                setDateTimePicker: function () {
                    $("input.datetimefield").datetimepicker(
                            {
                                dateFormat: 'dd-mm-yy',
                                timeFormat: 'HH:mm',
                                changeMonth: true,
                                changeYear: true,
                                showAnim: "fold",
                                yearRange: "1900:2100",
                                beforeShow: function () {
                                    setTimeout(function () {
                                        $('.ui-datepicker').css('z-index', 99999999999999);
                                    }, 0);
                                },
                                showOn: "both",
                                buttonImage: calenderImage,
                                buttonImageOnly: true,
                                buttonText: "Select date time",
                                timeOnlyTitle: 'Escolha uma hora',
                                timeText: 'Hora',
                                hourText: 'Horas',
                                minuteText: 'Minutos',
                                secondText: 'Segundos',
                                millisecText: 'Milissegundos',
                                microsecText: 'Microssegundos',
                                timezoneText: 'Fuso horário',
                                currentText: 'Agora',
                                closeText: 'Fechar',
                                timeSuffix: '',
                                amNames: ['a.m.', 'AM', 'A'],
                                pmNames: ['p.m.', 'PM', 'P'],
                                isRTL: false
                            }
                    );
                }
            };
        });