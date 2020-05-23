$(function () {
    //对form表单添加提交事件
    $('form').submit(function () {
        var user = {
            userQqNumber: $(this).find("input[name='user_qq_number']").val(),
            userName: $(this).find("input[name='user_name']").val(),
            userNickName: $(this).find("input[name='user_nick_name']").val(),
            userPassword_one: $(this).find("input[name='user_password_one']").val(),
            userPassword_two: $(this).find("input[name='user_password_two']").val(),
            userEmail: $(this).find("input[name='user_email']").val(),
            userBirthday: $(this).find("input[name='user_birthday']"),
            userTelephoneNumber: $(this).find("input[name='user_telephone_number']").val(),
            personalSignature: $(this).find("input[name='personal_signature']").val()
        };
        var emailReg = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
        var telephoneReg = /0?(13|14|15|18)[0-9]{9}/;
        var qqReg = /[1-9]([0-9]{4,10})/;
        if (qqReg.test(user.userQqNumber)) {
            styleUtil.errorShow(null, "请输入正确的QQ号码");
            return false;
        } else if (user.userName === "" || user.userName === null) {
            styleUtil.errorShow(null, "用户名不可以为空");
            return false;
        } else if (user.userPassword_one.length <= 8 || user.userPassword_one > 15) {
            styleUtil.errorShow(null, "密码必须大于八位小于等于15位");
            return false;
        } else if (user.userPassword_one !== user.userPassword_two) {
            styleUtil.errorShow(null, "两次输入密码不相同");
            return false;
        } else if (user.userNickName === "" || user.userNickName === null) {
            styleUtil.errorShow(null, "用户昵称不可以为空");
            return false;
        } else if (emailReg.test(user.userEmail)) {
            styleUtil.errorShow(null, "不属于邮箱规则");
            return false;
        } else if (telephoneReg.test(user.userTelephoneNumber)) {
            styleUtil.errorShow(null, "请输入国内手机号");
            return false;
        } else if (user.userBirthday === "" || user.userBirthday === null) {
            styleUtil.errorShow(null, "请选择出生日期");
        }

        if (user.personalSignature === "" || user.personalSignature === null) {
            user.personalSignature = "这个人很懒，什么都没留下";
        }
        $.ajax({
            type: "GET",
            url: "/user/edit/save",
            date: user,
            dateType: "json",
            success: function (data) {
                if (date.success) {
                    alert("保存用户信息成功");
                    location.href = "/user/personalPage"
                }
            },
            error: function (data) {

            }
        })

    })

    //设置图片链接
    // $.get("",function (data) {
    //     $("#test").attr("src","data:image/png;base64,"+data.pic);
    // },"json")
    //图片上传
    function uploadImage(fileDom) {
        //获取文件
        var file = fileDom.files[0];
        //判断类型
        var imageType = /^image\//;
        if (file === undefined || !imageType.test(file.type)) {
            alert("请选择图片！");
            return;
        }
        //判断大小
        if (file.size > 512000) {
            alert("图片大小不能超过500K！");
            return;
        }
        //清空值
        $(fileDom).val('');
        var formData = new FormData();
        formData.append("file", file);
        //上传图片
        $.ajax({
            url: "/user/uploadUserHearImage",
            type: "post",
            data: formData,
            contentType: false,
            processData: false,
            dataType: "json",
            mimeType: "multipart/form-data",
            success: function (data) {
                if (data.success) {
                    //给头像路径更换
                    if (data.picture === null) {
                        alert("用户头像上传失败,请重新上传");
                    } else {
                        $("#test").attr("src", "data:image/png;base64," + data.picture);
                    }
                } else {
                    alert("图片上传异常！");
                }
            },
            beforeSend: function () {
            },
            error: function () {

            }
        });
    }
})