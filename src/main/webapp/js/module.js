
function formateGrade(val) {
    if(val==0){
        return "根节点";
    }
    if(val==1){
        return "一级节点";
    }
    if(val==2){
        return "二级节点";
    }
}

function queryModulesByParams () {
    $('#dg').datagrid('load',{
        moduleName: $('#moduleName').val(),
        parentId: $('#pid').val(),
        grade: $('#grade').combobox('getValue'),
        optValue: $('#optValue').val()
    })
}

function openAddModuleDailog () {
    openAddOrUpdateDlg('dlg','添加模块')
}

$(function () {
    $('#parentMenu').hide();
    $('#grade02').combobox({
        onChange: function (newValue, oldValue) {
            if(newValue==0){
                $('#parentMenu').hide();
            }else{
                $('#parentMenu').show();

                $('#parentId02').combobox({
                    url:ctx + '/module/queryModuleByGrade?grade='+(newValue-1),
                    valueField:'id',
                    textField:'moduleName'
                });

            }
        }
    })
})

function saveOrUpdateModule () {
    saveOrUpdateData('fm',ctx + '/module/saveOrUpdateModule','dlg',queryModulesByParams)
}

function deleteModule () {
    deleteData('dg',ctx+'/module/deleteModule',queryModulesByParams)
}