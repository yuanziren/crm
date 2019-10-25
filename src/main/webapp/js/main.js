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

