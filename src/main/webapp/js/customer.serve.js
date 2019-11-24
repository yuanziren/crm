
function saveCustomerServe () {
    $('#fm').form('submit', {
        url: ctx + '/customerServe/saveOrUpdateCustomerServe',
        onSubmit: function () {
            return $(this).form('validate');	// 返回false终止表单提交
        },
        success: function (data) {
            data = JSON.parse(data);// 手动解析json
            if(data.code==200){

                $.messager.alert('来自crm',data.msg,'info',function () {
                    // 清空表单
                    $('#fm').form('clear')
                });
            }else{
                $.messager.alert('来自crm',data.msg,'error');
            }
        }
    });
}


function openCustomerServeAssignDialog () {
    openModifyDialog('dg','fm','dlg','分配')
}
function addCustomerServeAssign () {
    saveOrUpdateData('fm',ctx + '/customerServe/saveOrUpdateCustomerServe','dlg',function () {
        closeDlgData('dlg')
        $('#dg').datagrid('load')
    } )
}

function openCustomerServeProceDialog () {
    openModifyDialog('dg','fm','dlg','处理')
}

function addCustomerServeProce () {
    addCustomerServeAssign();
}

function openCustomerServeFeedBackDialog () {
    openModifyDialog('dg','fm','dlg','反馈')
}

function addCustomerServeFeedBack () {
    addCustomerServeAssign();
}