
var UPDATE_CONSTANTS = (function() {
    var private = {
        'ADD_GROUP': 'addGroup',
        'REMOVE_GROUPS': 'removeGroups',
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

var GROUP_ROLE = (function() {
    var private = {
        'ADMINISTRATOR':'administrator',
        'DRIVER': 'driver',
        'CUSTOMER': 'registered_customer'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var COLOR_CHOSEN = "#b2dba1";
var COLOR_NOT_CHOSEN = "dimgrey";

var SERVLET_NAME = "GroupServlet";
var pageNumber = 1;
var pageSize = 10;
var pageMaxNumber = 5;
var userIds = [];
var groupIds = [];
var groupName = "";
var role = "";

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

function manageGroups(servletName1, pageNumber1, updateAction,  selectAction, selectCountAction, ids1) {
    if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber = getPageNum(pageSize, pageMaxNumber, pageNumber1);
        var e = document.getElementById("groupRole");
        role = e.options[e.selectedIndex].value;
        $.post(servletName1, {pageSize: pageSize, pageNumber: 1, groupName : groupName, userEmail: $("#inputEmail").val(), ids: ids1.join(","), groupRole: role, updateAction : updateAction,  selectAction: selectAction, selectCountAction: selectCountAction}, function (responseJson) {
            if (action1 == UPDATE_CONSTANTS.get("REMOVE_GROUPS")) {
                getGroups(responseJson);
            } else {
                getUsers(responseJson);
            }
        });
    } else {
        pageNumber = 1;
    }
}

function getUsers(responseJson) {
    if (responseJson !== null) {
        $("#usersTable").find("tr:gt(0)").remove();
        var table2 = $("#usersTable");
        var previousUserGroup;
        $.each(responseJson, function (key, value) {
            if (value[USER_ATTRIBUTES.get('EMAIL')] !== "") {
                var rowNew = $("<tr class='active'><td></td><td></td><td></td><td></td><td><s/td><td></td><td></td></tr>");
                rowNew.children().eq(0).text(value[USER_ATTRIBUTES.get('USER_ID')]);
                rowNew.children().eq(1).text(value[USER_ATTRIBUTES.get('EMAIL')]);
                rowNew.children().eq(2).text(value[USER_ATTRIBUTES.get('PHONE')]);
                rowNew.children().eq(3).text(value[USER_ATTRIBUTES.get('IS_DRIVER')]);
                rowNew.children().eq(4).text(value[USER_ATTRIBUTES.get('IS_ADMIN')]);
                rowNew.children().eq(5).text(value[USER_ATTRIBUTES.get('GROUP_NAME')]);
                var currentUserGroup = value[USER_ATTRIBUTES.get('GROUP_NAME')];
                if (groupName === currentUserGroup) {
                    rowNew.children().eq(6).html('<div><button class="btn btn-default" type="button" onclick="addId(this)">REMOVE</button></div>');
                    previousUserGroup = currentUserGroup;
                } else {
                    rowNew.children().eq(6).html('<div><button class="btn btn-default" type="button" onclick="addId(this)">ADD</button></div>');
                    if (previousUserGroup === currentUserGroup) {
                        rowNew.appendTo($("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>"));
                        previousUserGroup = "";
                    }
                }
                rowNew.appendTo(table2);
            } else {
                if (value[USER_ATTRIBUTES.get('USER_ID')] === '-1') {
                    alert("THIS GROUP NAME IS NOT AVAILABLE");
                } else {
                    pageMaxNumber = value[USER_ATTRIBUTES.get('USER_ID')];
                }
            }
        });
    }
}

function addId(el) {
    var userId = el.parentNode.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    var index = userIds.indexOf(userId);
    if (index < 0) {
        userIds.push(userId);
    } else {
        userIds.splice(index, 1);
    }
    if (el.innerHTML === "ADD") {
        el.innerHTML = "REMOVE";
    } else {
        el.innerHTML = "ADD";
    }
    alert(userIds);
}

function getGroups(responseJson) {
    if (responseJson !== null) {
        $("#groupsTable").find("tr:gt(0)").remove();
        var table1 = $("#groupsTable");
        $.each(responseJson, function (key, value) {
            if (value[GROUP_ATTRIBUTES.get('GROUP_NAME')] != "") {
                var index = groupIds.indexOf(value[GROUP_ATTRIBUTES.get('GROUP_NAME')]);
                if (index >= 0) {
                    var rowNew = $("<tr style='color: #b2dba1' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
                } else {
                    var rowNew = $("<tr style='color: dimgrey' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
                }
                rowNew.children().eq(0).text(value[GROUP_ATTRIBUTES.get('GROUP_NAME')]);
                rowNew.children().eq(1).text(value[GROUP_ATTRIBUTES.get('COUNT_USERS')]);
                rowNew.children().eq(2).html('<button id="editGroup" class="btn btn-primary" onclick="onClickEditGroup(this)" data-toggle="modal" data-target="#largeModal" >Edit</button>');
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

$(document).ready(function () {
    getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), "", "", 1);
});
$(document).ready(function () {
    $("#removeGroups").hide();
});

function onClickEditGroup(el) {
    groupName = el.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    onClickGroup(el.parentNode.parentNode);
    $("#labelGroupName").text("Group: " + groupName);
    $("#labelGroupName").show();
    $("#groupNameInput").hide();
    //$("#groupRole").hide();
    getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, '', 1);
}

function onClickGroup(el) {
    var elem = el.getElementsByTagName("td")[0].innerHTML;
    var index = groupIds.indexOf(elem);
    if (index >= 0) {
        groupIds.splice( index, 1 );
        el.style.color = COLOR_NOT_CHOSEN;
    } else {
        groupIds.push(elem);
        el.style.color = COLOR_CHOSEN;
    }
    if (groupIds.length > 0) {
        $("#removeGroups").show();
    } else {
        $("#removeGroups").hide();
    }
}

function removeGroups() {
    manageGroups(SERVLET_NAME, pageNumber, UPDATE_CONSTANTS.get("REMOVE_GROUPS"),
        SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), groupIds);
}

function onClickAddGroup() {
    $("#labelGroupName").hide();
    $("#groupNameInput").show();
    $("#groupRole").show();
    getGroupUserData(SERVLET_NAME, SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, '', 1);
}

function onCansel() {
    groupName = '';
    $("#groupNameInput").text('');
    $("#inputEmail").text('');
    userIds = [];
}

function saveChanges() {
    if (userIds.length > 0) {
        if ((groupName === "")) {
            groupName = $("#groupNameInput").val();
            if (groupName !== "") {
                manageGroups(SERVLET_NAME, pageNumber, UPDATE_CONSTANTS.get("ADD_GROUP"), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), userIds);
                location.reload();
            } else {
                alert("input groupname");
            }
        } else {
            manageGroups(SERVLET_NAME, pageNumber, UPDATE_CONSTANTS.get("UPDATE_GROUP"), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), userIds);
            location.reload();
        }
    } else {
        alert("assign users for group");
    }
}