// 初始化
var sid = $('#saleChanceId').val();
$('#dg').edatagrid({
    url: ctx + '/cusDevPlan/queryCusDevPlansByParams?sid='+sid,
    saveUrl: ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    updateUrl: ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId='+sid,
    destroyUrl: ''
});

function addRow() {
    $('#dg').edatagrid('addRow')
}

function saveOrUpdateCusDevPlan () {
    $('#dg').edatagrid('saveRow')
}

function delCusDevPlan() {
    var rows = $('#dg').datagrid('getSelections');
    //console.log(rows);
    if(rows.length<1){
        $.messager.alert('来自crm', '请选择一条记录进行删除','info')
        return;
    }
    if(rows.length>=1){
        $.messager.confirm('来自crm', '是否删除'+rows.length+'条记录?',function (r) {
            if(r){
                //传参格式: ?ids=1&ids=2
                var ids = '';
                for(var i=0;i<rows.length;i++){
                    ids += 'ids='+rows[i].id+'&';
                }
                //console.log(ids);
                $.ajax({
                    url: ctx + '/cusDevPlan/deleteCusDevPlanBatch?'+ids,
                    type: 'post',
                    success:function (data) {
                        if(data.code==200){
                            $.messager.alert('来自crm',data.msg,'info',function () {
                                // 刷新数据
                                $('#dg').datagrid('load')
                            });
                        }else{
                            $.messager.alert('来自crm',data.msg,'error');
                        }
                    }
                });
            }
        })
    }
}

//updateDevResult
function updateSaleChanceDevResult(devResult) {
    $.ajax({
        url: ctx + '/saleChance/updateDevResult',
        data:{
            id: sid,
            devResult: devResult
        },
        type: 'post',
        success:function (data) {
            if(data.code==200){
                $.messager.alert('来自crm',data.msg,'info',function () {
                    // 隐藏操作条
                    $('#toolbar').hide();
                    // 表格置为不可编辑
                    $('#dg').edatagrid('disableEditing')
                });
            }else{
                $.messager.alert('来自crm',data.msg,'error');
            }
        }
    })
}

$(function () {

    var devResult = $('#devResult').val();
    if(devResult==2 || devResult==3){
        // 隐藏操作条
        $('#toolbar').hide();
        // 表格置为不可编辑
        $('#dg').edatagrid('disableEditing')
    }
});