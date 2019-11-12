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

    doGrant(rows[0].id);// 角色id
}

function doGrant(roleId) {
    $.ajax({
        url: ctx + '/module/queryAllModuleByRoleId?roleId=' + roleId,
        success: function (data) {
            var setting = {
                check: {
                    enable: true,
                    chkboxType: { "Y" : "ps", "N" : "ps" }
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };

            var zNodes =[{"id":1,"pId":null,"name":"营销管理","checked":false},{"id":2,"pId":1,"name":"营销机会管理","checked":false},{"id":3,"pId":2,"name":"营销机会管理查询","checked":false},{"id":4,"pId":2,"name":"营销机会管理添加修改","checked":false},{"id":5,"pId":2,"name":"营销机会管理删除","checked":false},{"id":6,"pId":1,"name":"客户开发计划","checked":false},{"id":7,"pId":6,"name":"查看详情","checked":false},{"id":8,"pId":null,"name":"客户管理","checked":false},{"id":9,"pId":8,"name":"客户信息管理","checked":false},{"id":10,"pId":9,"name":"创建","checked":false},{"id":11,"pId":9,"name":"修改","checked":false},{"id":12,"pId":8,"name":"客户流失管理","checked":false},{"id":13,"pId":12,"name":"暂缓流失","checked":false},{"id":14,"pId":null,"name":"统计报表","checked":false},{"id":15,"pId":14,"name":"客户贡献分析","checked":false},{"id":16,"pId":null,"name":"服务管理","checked":false},{"id":17,"pId":null,"name":"基础数据管理","checked":false},{"id":18,"pId":null,"name":"系统管理","checked":false},{"id":19,"pId":9,"name":"删除","checked":false},{"id":26,"pId":18,"name":"用户管理","checked":false},{"id":27,"pId":18,"name":"角色管理","checked":false},{"id":28,"pId":18,"name":"资源管理","checked":false},{"id":29,"pId":18,"name":"修改密码","checked":false},{"id":30,"pId":18,"name":"安全退出","checked":false},{"id":34,"pId":16,"name":"服务创建","checked":false},{"id":35,"pId":16,"name":"服务分配","checked":false},{"id":36,"pId":16,"name":"服务处理","checked":false},{"id":37,"pId":16,"name":"服务反馈","checked":false},{"id":38,"pId":16,"name":"服务归档","checked":false},{"id":39,"pId":14,"name":"客户构成分析","checked":false},{"id":40,"pId":14,"name":"客户服务分析","checked":false}];

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            openAddOrUpdateDlg('permissionDlg', '授权')
        }
    })
}
