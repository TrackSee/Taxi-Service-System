
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
        'IS_DRIVER': 'driver',
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

var SERVLETS = (function() {
    var private = {
        'GROUP_EDIT_SERVLET': 'group',
        'GROUP_COUNT_SERVLET': 'groupCount',
        'GROUP_EXIST_SERVLET': 'groupExist'
    };
    return {
        get: function (name) {
            return private[name];
        }
    };
})();

var COLOR_CHOSEN = "#00b3ee";
var COLOR_NOT_CHOSEN = "white";
var COUNT = "count";
var EXIST = "exist";
var FADE_OUT = 5000;
var DELAY = 150;

var INPUT_NAME_MESSAGE = "Please input correct name of the group(2 - 30 characters)";
var NOT_FREE_NAME_MESSAGE = "This group name is not free";
var ASSIGN_USERS_MESSAGE = "Please assing user for group";

var ID_ADMIN = 'id_admin';

var pageNumber = 1;
var pageSize = 5;
var pageMaxNumber = 999999999;
var userIds = [];
var groupIds = [];
var groupName = "";
var groupNameHelp = "";
var role = "";
var arrUpdateRoles = [];

var countGroupsBeforeAdd;

var currentdate = new Date();

$(document).ready(function () {
    $("#alert-danger").hide();
    $("#alert-danger-group-name").hide();
    getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), "", "", 1);
});
$("#removeGroups").hide();

function getPageNum(pageSize1, pageMaxNumber1, pageNumber1) {
    if (pageMaxNumber1 === 0) {
        return 1;
    }
    if (pageMaxNumber1 < pageNumber1*pageSize1) {
        if (pageMaxNumber1 === (pageNumber1 - 1)*pageSize1) {
            pageNumber = pageNumber1 - 1;
            return pageNumber;
        }
        pageNumber = Math.floor(pageMaxNumber1 / pageSize1) + 1;
        return pageNumber;
    } else {
        pageNumber = pageNumber1;
        return pageNumber;
    }
}

function addGroup() {
    $.get(SERVLETS.get('GROUP_EXIST_SERVLET'), {groupName : groupName}, function (responseJson) {
        $.each(responseJson, function (key, value) {
            if (value[EXIST] == true) {
                $("#alert-danger").html(NOT_FREE_NAME_MESSAGE);
                $("#alert-danger").show();
                $("#alert-danger").fadeOut(FADE_OUT);
                groupName = "";
            } else {
                manageGroups(SERVLETS.get('GROUP_EDIT_SERVLET'), pageNumber, UPDATE_CONSTANTS.get("ADD_GROUP"),
                    SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), userIds);
                location.reload();
            }
        });
    });
}

function manageGroups(servletName1, pageNumber1, updateAction,  selectAction, selectCountAction, ids1) {
    if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber = getPageNum(pageSize, pageMaxNumber, pageNumber1);
        var e = document.getElementById("groupRole");
        role = e.options[e.selectedIndex].value;

        $.post(servletName1, {pageSize: pageSize, pageNumber: 1, groupName : groupName,
            userEmail: $("#inputEmail").val(), ids: ids1.join(","), groupRole: role,
            updateAction : updateAction,  selectAction: selectAction,
            selectCountAction: selectCountAction, updateRoleIds: JSON.stringify(arrUpdateRoles),
            id_admin: $("#id_admin").val()}, function (responseJson) {

            if (updateAction === UPDATE_CONSTANTS.get("REMOVE_GROUPS")) {
                getGroups(responseJson);
                onCansel();
                groupIds = [];
                $("#removeGroups").hide();
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
            var rowNew = $("<tr class='active'><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            var currentId = value[USER_ATTRIBUTES.get('USER_ID')];
            rowNew.children().eq(0).text(currentId);
            rowNew.children().eq(1).text(value[USER_ATTRIBUTES.get('EMAIL')]);
            rowNew.children().eq(2).text(value[USER_ATTRIBUTES.get('PHONE')]);

            var isDriver = value[USER_ATTRIBUTES.get('IS_DRIVER')];
            var isAdmin = value[USER_ATTRIBUTES.get('IS_ADMIN')];

            var roles = selectRol(isAdmin, isDriver, currentId);

            //rowNew.children().eq(3).html(selectRole(isDriver, currentId, 'isDriver'));
            //rowNew.children().eq(4).html(selectRoleAdmin(isAdmin, currentId, isDriver));
            rowNew.children().eq(3).html(roles[0]);
            rowNew.children().eq(4).html(roles[1]);

            var currentUserGroup = value[USER_ATTRIBUTES.get('GROUP_NAME')];
            rowNew.children().eq(5).text(currentUserGroup);

            rowNew.children().eq(6).html(selectActionForUser(currentId, currentUserGroup));

            rowNew.appendTo(table2);
        });
    }
}

function selectActionForUser(id, group) {
    if ((groupNameHelp === "") && (userIds.indexOf("" + id) < 0)) {
        return '<div><button class="btn btn-default" type="button" onclick="addId(this)">ADD</button></div>';
    }
    if ((userIds.indexOf("" + id) >= 0) && (groupNameHelp === group)) {
        return '<div><button class="btn btn-default" type="button" onclick="addId(this)">ADD</button></div>';
    } else if ((userIds.indexOf("" + id) >= 0) && (groupName !== group)) {
        return '<div><button class="btn btn-default" type="button" onclick="addId(this)">REMOVE</button></div>';
    } else if ((userIds.indexOf("" + id) < 0) && (groupNameHelp === group)) {
        return '<div><button class="btn btn-default" type="button" onclick="addId(this)">REMOVE</button></div>';
    } else {
        return '<div><button class="btn btn-default" type="button" onclick="addId(this)">ADD</button></div>';
    }
}

function selectRol(isAdmin, isDriver, id) {
    var rez = [];
    var unselected = "<input type='checkbox' onchange='updateRolesIds(this)'>";
    var selected = "<input type='checkbox' onchange='updateRolesIds(this)' checked>";
    var unselectedReadable = "<input type='checkbox' onchange='updateRolesIds(this)' disabled>";
    var selectedReadable = "<input type='checkbox' onchange='updateRolesIds(this)' checked disabled>";
    for (var o in arrUpdateRoles) {
        var obj = arrUpdateRoles[o];
        if (obj['id'] == id) {
            if (obj['isDriver'] == true) {
                rez.push(selected);
                rez.push(unselectedReadable);
            } else if(obj['isAdmin'] == true) {
                rez.push(unselectedReadable);
                rez.push(selected);
            } else {
                rez.push(unselected);
                rez.push(unselected);
            }
            return rez;
        }
    }
    if ($("#id_admin").val() == id) {
        rez.push(unselectedReadable);
        rez.push(selectedReadable);
    } else {
        if (isDriver == true) {
            rez.push(selected);
            rez.push(unselectedReadable);
        } else if(isAdmin == true) {
            rez.push(unselectedReadable);
            rez.push(selected);
        } else {
            rez.push(unselected);
            rez.push(unselected);
        }
    }
    return rez;
}

function updateRolesIds(el) {
    var id = el.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    var isAdmin = el.parentNode.parentNode.getElementsByTagName("td")[4].getElementsByTagName("input")[0].checked;
    var isDriver = el.parentNode.parentNode.getElementsByTagName("td")[3].getElementsByTagName("input")[0].checked;
    for (var o in arrUpdateRoles) {
        var obj = arrUpdateRoles[o];
        if (obj['id'] ==  id) {
            obj['isAdmin'] = isAdmin;
            obj['isDriver'] = isDriver;
        }
    }
    var roles = selectRol(isAdmin, isDriver, id);
    var tabl = el.parentNode.parentNode.getElementsByTagName("td");

    tabl[4].innerHTML=roles[1];
    tabl[3].innerHTML=roles[0];

    arrUpdateRoles.push({ id: id, isAdmin: isAdmin, isDriver: isDriver });
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
}

function getGroups(responseJson) {
    if (responseJson !== null) {
        $("#groupsTable").find("tr:gt(0)").remove();
        var table1 = $("#groupsTable");
        $.each(responseJson, function (key, value) {
            var index = groupIds.indexOf(value[GROUP_ATTRIBUTES.get('GROUP_NAME')]);
            if (index >= 0) {
                //var rowNew = $("<tr style='color: #0044cc' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
                var rowNew = $("<tr style='background-color: #00b3ee; color: black' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
            } else {
                //var rowNew = $("<tr style='color: dimgrey' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
                var rowNew = $("<tr style='color: white; color: black' onclick='onClickGroup(this)'><td></td><td></td><td></td></tr>");
            }
            rowNew.children().eq(0).text(value[GROUP_ATTRIBUTES.get('GROUP_NAME')]);
            rowNew.children().eq(1).text(value[GROUP_ATTRIBUTES.get('COUNT_USERS')]);
            rowNew.children().eq(2).html('<button id="editGroup" class="btn btn-primary" onclick="onClickEditGroup(this)"' +
                ' data-toggle="modal" data-target="#largeModal" >Edit</button>');
            rowNew.appendTo(table1);
        });
    }
}

function getGroupUserData(servletName, selectAction, selectCountAction, groupName, userEmail, pageNumber1) {
    if ((pageSize > 0) && (pageNumber > 0)) {
        pageNumber1 = getPageNum(pageSize, pageMaxNumber, pageNumber);
        $.get(SERVLETS.get('GROUP_COUNT_SERVLET'), {groupName: groupName, userEmail: userEmail,
            selectCountAction: selectCountAction}, function (responseJson) {
            $.each(responseJson, function (key, value) {
                pageMaxNumber = value[COUNT];
            });
        });
        $.get(servletName, {pageSize: pageSize, pageNumber: pageNumber1, groupName: groupName, userEmail: userEmail,
            selectAction: selectAction}, function (responseJson) {
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

function onClickEditGroup(el) {
    pageNumber = 1;
    groupName = el.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    groupNameHelp = groupName;
    onClickGroup(el.parentNode.parentNode);
    $("#labelGroupName").text("Group: " + groupName);
    $("#labelGroupName").show();
    $("#groupNameInput").hide();
    getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, '', 1);
}

function onClickGroup(el) {
    var elem = el.getElementsByTagName("td")[0].innerHTML;
    var index = groupIds.indexOf(elem);
    if (index >= 0) {
        groupIds.splice( index, 1 );
        el.style.backgroundColor = COLOR_NOT_CHOSEN;
        //el.style.color = COLOR_NOT_CHOSEN;
    } else {
        groupIds.push(elem);
        //el.style.color = COLOR_CHOSEN;
        el.style.backgroundColor = COLOR_CHOSEN;
    }
    if (groupIds.length > 0) {
        $("#removeGroups").show();
    } else {
        $("#removeGroups").hide();
    }
}

function removeGroups() {
    manageGroups(SERVLETS.get('GROUP_EDIT_SERVLET'), pageNumber, UPDATE_CONSTANTS.get("REMOVE_GROUPS"),
        SELECT_CONSTANTS.get('SELECT_GROUPS'), SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), groupIds);
}

function onClickAddGroup() {
    pageNumber = 1;
    $("#labelGroupName").hide();
    $("#groupNameInput").show();
    $("#groupRole").show();
    getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, '', 1);
    countGroupsBeforeAdd = pageMaxNumber;
}

function onCansel() {
    groupName = '';
    groupNameHelp = '';
    $("#alert-danger").hide();
    $("#groupNameInput").val('');
    $("#inputEmail").val('');
    $("#groupRole").val('none');
    userIds = [];
    arrUpdateRoles = [];
    pageNumber = 1;
    getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'),
        SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), pageNumber);
}

function saveChanges() {
    if (userIds.length > 0) {
        if (groupName == "") {
            groupName = $("#groupNameInput").val();
            if ((groupName.length > 1) && (groupName.length < 30)) {
                addGroup();
            } else {
                $("#alert-danger").html(INPUT_NAME_MESSAGE);
                $("#alert-danger").show();
                $("#alert-danger").fadeOut(FADE_OUT);
                groupName = "";
            }
        } else {
            manageGroups(SERVLETS.get('GROUP_EDIT_SERVLET'), pageNumber, UPDATE_CONSTANTS.get("UPDATE_GROUP"),
                SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), userIds);
            location.reload();
        }
    } else {
        if (groupNameHelp === "") {
            $("#alert-danger").html(ASSIGN_USERS_MESSAGE);
            $("#alert-danger").show();
            $("#alert-danger").fadeOut(FADE_OUT);
        } else {
            manageGroups(SERVLETS.get('GROUP_EDIT_SERVLET'), pageNumber, UPDATE_CONSTANTS.get("UPDATE_GROUP"),
                SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), userIds);
            location.reload();
        }
    }
}

function getUsersByInput() {
    //var inputStart = $('#inputEmail').val();
    //var date = new Date();
    //if (date - currentdate > DELAY) {
        getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'), SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), 1);
    //}
    //currentdate = new Date();
}

$(document).ready(function () {
    $("#userCountPage").change(function () {
        var e = document.getElementById("userCountPage");
        pageSize = e.options[e.selectedIndex].value;
        getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_USERS'),
            SELECT_COUNT_CONSTANTS.get('SELECT_USERS_COUNT'), groupName, $('#inputEmail').val(), 1);
    });
});
$(document).ready(function () {
    $("#groupCountPage").change(function () {
        var e = document.getElementById("groupCountPage");
        pageSize = e.options[e.selectedIndex].value;
        getGroupUserData(SERVLETS.get('GROUP_EDIT_SERVLET'), SELECT_CONSTANTS.get('SELECT_GROUPS'),
            SELECT_COUNT_CONSTANTS.get('SELECT_GROUPS_COUNT'), $('#input1').val(), pageNumber);
    });
});