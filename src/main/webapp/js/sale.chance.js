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