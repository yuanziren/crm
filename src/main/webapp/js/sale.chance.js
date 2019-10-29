/**
 * @param val 当前值
 * @param row 当前行的值
 * @param index 索引
 */
function formatState(val, row, index) {

    if(val==0){
        return "未分配";
    }
    if(val==1){
        return "已分配";
    }
}

function formatDev(val) {

    if(val==0){
        return "未开发";
    }
    if(val==1){
        return "开发中";
    }
    if(val==2){
        return "开发成功";
    }
    if(val==3){
        return "开发失败";
    }
}

//查询
function querySaleChancesByParams() {
    $('#dg').datagrid('load',{// 查询参数
        customerName: $('#customerName').val(),
        state: $('#state').combobox('getValue'),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    })
}

$(function () {
    // 页面加载完成后执行
    $('#dg').datagrid({
        rowStyler: function(index,row){
            var devResult = row.devResult;

            // if (devResult==0){
            //     return 'background-color:blue;';
            // }
            // if (devResult==1){
            //     return 'background-color:yellow;';
            // }
            // if (devResult==2){
            //     return 'background-color:green;';
            // }
            // if (devResult==3){
            //     return 'background-color:red;';
            // }

            if (devResult == 0) {
                return "background-color:#5bc0de;"; // 蓝色
            } else if (devResult == 1) {
                return "background-color:#f0ad4e;"; // 黄色
            } else if (devResult == 2) {
                return "background-color:#5cb85c;"; // 绿色
            } else if (devResult == 3) {
                return "background-color:#d9534f;"; // 红色
            }
        }
    });

});

function openAddSaleChacneDialog() {
    $('#dlg').dialog('open');
}

function saveOrUpdateSaleChance() {
    $('#fm').form('submit', {
        url: ctx + '/saleChance/saveOrUpdateSaleChance',
        onSubmit: function () {
            return $(this).form('validate');	// 返回false终止表单提交
        },
        success: function (data) {
            data = JSON.parse(data);// 手动解析json
            if(data.code==200){

                $.messager.alert('来自crm',data.msg,'info',function () {
                    // 关闭弹窗
                    $('#dlg').dialog('close')
                    // 刷新数据
                    $('#dg').datagrid('load')
                });
            }else{
                $.messager.alert('来自crm',data.msg,'error');
            }
        }
    });
}