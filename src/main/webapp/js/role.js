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