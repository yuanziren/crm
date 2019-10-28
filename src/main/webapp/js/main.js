function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

function logout() {
    $.messager.confirm('来自crm','您确定要退出系统吗？',function (r) {
        if(r){
            //清除Cookie
            $.removeCookie("userIdStr");
            window.location.href=ctx+'/index';
        }
    })

}

function openPasswordModifyDialog() {
    $('#dlg').dialog('open');   //显示弹窗
}

function modifyPassword() {
    $('#fm').form('submit', {
        url: ctx + '/user/updateUserPwd',
        onSubmit: function () {
            return $(this).form('validate');	// 返回false终止表单提交
        },
        success: function (data) {
            data = JSON.parse(data);// 手动解析json
            //console.log(data);
            if(data.code==200){
                // 清除cookie, 跳转登陆页
                $.messager.alert('来自crm',data.msg,'info',function () {
                    // 清cookie
                    $.removeCookie("userIdStr");
                    window.location.href = ctx + '/index';
                });
            }else{
                $.messager.alert('来自crm',data.msg,'error');
            }
        }
    });
}

function closePasswordModifyDialog() {
    $('#dlg').dialog('close');   //关闭弹窗
}
