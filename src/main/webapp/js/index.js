
//登陆
function login() {
    // 1. 获取值
    var username = $('#username').val();
    var password = $('#password').val();
    // 2. 非空判断
    if(isEmpty(username)){
        alert("用户名为空");
        return;
    }
    if(isEmpty(password)){
        alert("密码为空");
        return;
    }
    // 3. 发送ajax请求
    $.ajax({
        url: ctx + "/user/login",
        type: 'post',
        data:{
            userName: username,
            userPwd: password
        },
        success:function (data) {
            //console.log(data);
            if(data.code==200){
                alert(data.msg);
                // 跳转到主页
                window.location.href = ctx + '/main';
            }else{
                alert(data.msg);
            }
        }
    });

}