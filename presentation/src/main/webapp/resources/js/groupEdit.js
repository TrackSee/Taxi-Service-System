
var UPDATE_CONSTANTS = (function() {
    var private = {
        'ADD_GROUP': 'addGroup',
        'ADD_USERS_TO_GROUP': 'addUsersToGroup',
        'REMOVE_GROUP': 'removeGroup',
        'REMOVE_USERS_FROM_GROUP': 'removeUsersFromGroup',
        'UPDATE_GROUP': 'updateGroup'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var SELECT_CONSTANTS = (function() {
    var private = {
        'SELECT_GROUPS':'selectGroups',
        'SELECT_USERS': 'selectUsers'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var SELECT_COUNT_CONSTANTS = (function() {
    var private = {
        'SELECT_GROUPS_COUNT': 'selectGroupsCount',
        'SELECT_USERS_COUNT': 'selectUsersCount'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var GROUP_ATTRIBUTES = (function() {
    var private = {
        'GROUP_NAME': 'name',
        'COUNT_USERS': 'countUsers'
    }
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var USER_ATTRIBUTES = (function() {
    var private = {
        'USER_ID': 'userId',
        'EMAIL': 'email',
        'PASSWORD': 'password',
        'PHONE':'phone',
        'SEX': 'sex',
        'IS_DRIVER': 'isDriver',
        'IS_ADMIN': 'admin',
        'GROUP_NAME': 'groupName',
        'DRIVER_LICENSE': 'driverLicense',
        'IGNORED_TIMES': 'isnoredTimes',
        'ACTIVATED': 'activated',
        'REGISTRATION_DATE': 'registrationDate',
        'CAR_BY_CAR_NUMBER': 'carByCarNumber'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var SERVLET_NAME = "GroupServlet";
var pageNumber = 1;
var pageSize = 10;
var pageMaxNumber = 5;
var userIds = [];
var groupIds = [];

function removeGroup(el) {
    el = el.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    groupIds.push(el);
    manageGroup(el, SERVLET_NAME, pageSize, pageNumber, "removeGroup");
}

function getPageNum(pageSize1, pageMaxNumber1, pageNumber1) {
    if (pageMaxNumber1 <= pageNumber1*pageSize1) {
        if (pageMaxNumber1 = pageNumber1*pageSize1) {
            return pageNumber1;
        } else {
            return Math.floor(pageMaxNumber1/pageSize1) + 1;
        }
    } else {
        return pageNumber1;
    }
}

function manageGroup(event, servletName1, pageSize1, pageNumber1, action1) {
    if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber = getPageNum(pageSize, pageMaxNumber, pageNumber);
        $.post(servletName1, {pageSize: pageSize, pageNumber: 1, groupName : groupIds[0], action : action1}, function (responseJson) {
            alert("post");
            if (responseJson !== null) {
                $("#groupsTable").find("tr:gt(0)").remove();
                var table1 = $("#groupsTable");
                $.each(responseJson, function (key, value) {
                    if (value['name'] != "") {
                        var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
                        rowNew.children().eq(0).text(value['name']);
                        rowNew.children().eq(1).text(value['countUsers']);
                        rowNew.children().eq(2).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick = "opendialog()" type="button">Edit</button></div><!-- /.col-lg-6 -->');
                        rowNew.children().eq(3).html('<div class="row"><div class="col-lg-6"><button class="btn btn-default" onclick="removeGroup(this)" type="button">Delete</button></div><!-- /.col-lg-6 -->');
                        rowNew.appendTo(table1);
                    } else {
                        if (key === -1) {
                            alert("GROUP WITH THIS NAME EXISTS!!!");
                        } else {
                            pageMaxNumber = value['countUsers'];
                        }
                    }
                });
            }
        });
        $("#tablediv").show();
    } else {
        pageNumber = 1;
    }
    alert("namege group");
}

function getUsers(responseJson, groupName1) {
    if (responseJson !== null) {
        $("#usersTable").find("tr:gt(0)").remove();
        var table2 = $("#usersTable");
        var previousUserGroup;
        $.each(responseJson, function (key, value) {
            if (USER_ATTRIBUTES.get('EMAIL') != "") {
                var rowNew = $("<tr class='rows'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value[USER_ATTRIBUTES.get('USER_ID')]);
                rowNew.children().eq(1).text(value[USER_ATTRIBUTES.get('EMAIL')]);
                rowNew.children().eq(2).text(value[USER_ATTRIBUTES.get('PHONE')]);
                rowNew.children().eq(3).text(value[USER_ATTRIBUTES.get('IS_DRIVER')]);
                rowNew.children().eq(4).text(value[USER_ATTRIBUTES.get('IS_ADMIN')]);
                rowNew.children().eq(5).text(value[USER_ATTRIBUTES.get('GROUP_NAME')]);
                var currentUserGroup = value[USER_ATTRIBUTES.get('GROUP_NAME')];
                if (groupName1 === currentUserGroup) {
                    rowNew.children().eq(6).html('<div><button class="btn btn-default" type="button">ADD</button></div>');
                    previousUserGroup = currentUserGroup;
                } else {
                    rowNew.children().eq(6).html('<div><button class="btn btn-default" type="button">REMOVE</button></div>');
                    if (previousUserGroup === currentUserGroup) {
                        rowNew.appendTo($("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>"));
                        previousUserGroup = "";
                    }
                }
                rowNew.appendTo(table2);
            } else {
                alert("else");
                pageMaxNumber = value[USER_ATTRIBUTES.get('USER_ID')];
            }
        });
    }
}

function getGroups(responseJson) {
    if (responseJson !== null) {
        $("#groupsTable").find("tr:gt(0)").remove();
        var table1 = $("#groupsTable");
        $.each(responseJson, function (key, value) {
            if (value[GROUP_ATTRIBUTES.get('GROUP_NAME')] != "") {
                var rowNew = $("<tr><td></td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value[GROUP_ATTRIBUTES.get('GROUP_NAME')]);
                rowNew.children().eq(1).text(value[GROUP_ATTRIBUTES.get('COUNT_USERS')])
                rowNew.children().eq(2).html('<div class="row"><div><button id="editGroup" class="btn btn-primary" data-toggle="modal" data-target="#largeModal" type="button">Edit</button></div>');
                rowNew.appendTo(table1);
            } else {
                pageMaxNumber = value['countUsers'];
            }
        });
    }
}

function getGroupUserData(servletName, selectAction, selectCountAction, groupName, userEmail, pageNumber1) {
    if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber1 = getPageNum(pageSize, pageMaxNumber, pageNumber);
        $.get(servletName, {pageSize: pageSize, pageNumber: 1, groupName: groupName, userEmail: userEmail,
            selectAction: selectAction, selectCountAction: selectCountAction}, function (responseJson) {
            if (selectAction === SELECT_CONSTANTS.get('SELECT_GROUPS')) {
                getGroups(responseJson);
            } else if (selectAction === SELECT_CONSTANTS.get('SELECT_USERS')) {
                getUsers(responseJson);
            }
        });
        $("#tablediv").show();
    } else {
        pageNumber = 1;
    }
}

function manageGroup(servletName, selectAction, selectCountAction, groupName, userEmail, pageNumber1) {
    getGroupUserData(servletName, selectAction, selectCountAction, groupName, userEmail, pageNumber1);
}

$(document).ready(function () {
    $("#tablediv").hide();
    getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), "", "", 1);
});

$(document).ready(function () {
    $("#editGroup").click( function () {
        var groupName1 = el.parentNode.getElementsByTagName("td")[6].innerHTML;
        alert(groupName1 + "groupName");
        manageGroup(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName1, '', 1);
        $("#largeModal").modal('show');
    });
});
function editGroup(el){
    $(document).ready(function () {
        manageGroup(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), '', '', 1);
        $("#largeModal").modal('show');
    });
}