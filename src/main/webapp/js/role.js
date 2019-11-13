function queryRolesByParams() {
    $('#dg').datagrid('load', {
        roleName: $('#roleName').val(),
        createDate: $('#time').datebox('getValue')
    })
}

function openAddRoleDailog() {
    openAddOrUpdateDlg('dlg', '添加角色');
}

function saveOrUpdateRole() {
    saveOrUpdateData('fm', ctx + '/role/saveOrUpdateRole', 'dlg', queryRolesByParams)
}

function openModifyRoleDialog() {
    openModifyDialog('dg', 'fm', 'dlg', queryRolesByParams)
}

function openRelationPermissionDialog() {
    var rows = $('#dg').datagrid('getSelections');
    //console.log(rows);
    if (rows.length < 1) {
        $.messager.alert('来自crm', '请选择一条记录进行授权', 'info')
        return;
    }
    if (rows.length > 1) {
        $.messager.alert('来自crm', '只能选择一条记录进行授权', 'info')
        return;
    }

    openPermissionTree(rows[0].id);// 角色id
}
/**
 var treeObj = $.fn.zTree.getZTreeObj("tree");
 var nodes = treeObj.getCheckedNodes(true);

 function zTreeOnCheck(event, treeId, treeNode) {
    alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
};
 var setting = {
	callback: {
		onCheck: zTreeOnCheck
	}
};

 * */

var treeObj;

function openPermissionTree(roleId) {
// 记录roleId到隐藏域
    $('#roleId').val(roleId);

    $.ajax({
        url: ctx + '/module/queryAllModuleByRoleId?roleId=' + roleId,
        success: function (data) {
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            var zNodes = data;
            treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            openAddOrUpdateDlg('permissionDlg', '授权')
        }
    })
}

function zTreeOnCheck(event, treeId, treeNode) {
    var nodes = treeObj.getCheckedNodes(true);
    //console.log(nodes);

    var moduleIds = '';

    for(var i=0;i<nodes.length;i++){
        moduleIds +='moduleIds='+nodes[i].id+'&';
    }
    //console.log(moduleIds);
    $('#moduleIds').val(moduleIds);
};

function doGrant() {
    $.ajax({
        url: ctx + '/role/doGrant?roleId='+$('#roleId').val()+'&'+$('#moduleIds').val(),
        success:function (data) {
            if(data.code==200){
                $.messager.alert('来自crm',data.msg,'info',function () {
                    // 刷新数据
                    $('#dg').datagrid('load')
                    // 关闭弹窗
                    closeDlgData("permissionDlg")
                });
            }else{
                $.messager.alert('来自crm',data.msg,'error');
            }
        }
    })
}

function deleteRole() {
    deleteData('dg',ctx + '/role/deleteRole',queryRolesByParams);
}