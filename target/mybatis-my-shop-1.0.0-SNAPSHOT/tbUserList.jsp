<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/19 0019
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Dashboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="static/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="static/dist/css/skins/_all-skins.min.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="static/bower_components/morris.js/morris.css">
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <script src="static/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="static/bower_components/jquery-ui/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <!-- Bootstrap 3.3.7 -->
    <script src="static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Morris.js charts -->
    <script src="static/bower_components/raphael/raphael.min.js"></script>
    <script src="static/bower_components/morris.js/morris.min.js"></script>
    <!-- Sparkline -->
    <script src="static/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="static/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
    <!-- daterangepicker -->
    <script src="static/bower_components/moment/min/moment.min.js"></script>
    <script src="static/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- datepicker -->
    <script src="static/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
    <!-- Bootstrap WYSIHTML5 -->
    <script src="static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <!-- Slimscroll -->
    <script src="static/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="static/bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="static/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="static/dist/js/pages/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="static/dist/js/demo.js">
    </script>
    <%--    批量选中和取消方法--%>
    <script type="text/javascript">
        function selectAll() {
            var checks = document.getElementsByName("checkOne");
            var len = checks.length;
            var obj = document.getElementById("checkAll");
            if (obj.checked) {
                for (var i = 0; i < len; i++) {
                    checks[i].checked = true;
                }
            } else {
                for (var i = 0; i < len; i++) {
                    checks[i].checked = false;
                }
            }
        }
    </script>
    <%--    批量删除方法--%>
    <script src="static/sweetalert.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //获取全部控件checkbox
            var _checkbox = $('input[type="checkbox"].minimal');
            $("#delAllSel").click(function () {
                //定义一个数组
                var _idArray = new Array();
                //将选中的元素 id 放入数组中
                swal("2222");
                _checkbox.each(function () {
                    var _id = $(this).attr("id");
                    if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                        _idArray.push(_id);
                    }
                });

                if (_idArray.length === 0) {
                    swal("请先选择复选框！！！")
                } else {
                    swal({
                        title: "你确定要删除吗？",
                        text: "一旦删除，将无法恢复！！！",
                        icon: "warning",
                        buttons: ["取消", "确定删除！"],
                        dangerMode: true,
                    })
                        .then((willDelete) => {
                            if (willDelete) {

                                //ajax请求
                                $.post("/allDeleteServlet", {ids: _idArray.toString()}, function (data) {
                                    debugger
                                    if (data.message != '数据删除失败！！！') {
                                        swal(
                                            "删除成功!!!",
                                            data.message, {
                                                icon: "success",
                                            })

                                    } else {
                                        swal(
                                            "删除失败!!",
                                            data.message, {
                                                buttons: false,
                                                timer: 2000,
                                            })
                                    }
                                    window.location.reload();
                                })

                            }
                        })
                }
            })

        })
    </script>
    <%--    更新函数--%>
    <script type="text/javascript">
        function update01(param) {
            $.ajax({
                url: "/findTbUserByIdServlet?id=" + param,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    $("#userId").val(param);
                    $("#user01").val(data.userName);
                    $("#userPassword").val(data.passWord);
                    $("#userEmail").val(data.email);
                    $("#userPhone").val(data.phone);
                }
            })
        }

        function selectById01(param) {
            $.ajax({
                url: "/findTbUserByIdServlet?id=" + param,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    $("#userId01").html(param);
                    $("#userName01").html(data.userName);
                    $("#userPassword01").html(data.passWord);
                    $("#userEmail01").html(data.email);
                    $("#userPhone01").html(data.phone);
                }
            })
        }

    </script>
    <script type="text/javascript">
        /**
         * @Method:
         * @Description:    显示和隐藏高级查询的div
         * @Param:
         * @return:
         * @Author:        Mr.Vincent
         * @Date:          2019/6/1
         */
        $(function () {
            $("#toggle-advanced-search").click(function () {
                $("i", this).toggleClass("fa-angle-double-down fa-angle-double-up");
                $("#div-advanced-search").slideToggle("fast");
            })
        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
</div>
<%---------------头部信息----------------------%>
<jsp:include page="static/header.jsp"></jsp:include>
<%--------------------------左侧栏------------------------------%>
<jsp:include page="static/leftMenu.jsp"></jsp:include>

<%----------------------主体内容----------------%>

<!-- Content Header (Page header) -->
<div class="content-wrapper" style="min-height:916.3px;">
    <section class="content-header">
        <h1>
            Dashboard
            <small>Version 2.0</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
        </ol>
    </section>
    <%------------添加 批量删除----------------------%>
    <div class="row">
        <div class="col-md-8">
            <div class="col-md-2">
                <%-- <a href="">
                     <button type="button" class="btn  bg-maroon btn-flat margin btn-block btn-primary fa fa-user-plus">
                         &nbsp;添&nbsp;&nbsp;加
                     </button>
                 </a>--%>
                <a href="" type="button" onclick="update01(${tbUser.id})" data-toggle="modal"
                   data-target="#modal-danger01" class="btn  btn-flat margin btn-block btn-primary">
                    <i class="fa fa-user-plus">添&nbsp;&nbsp;加</i>
                </a>
            </div>
            <div class="col-md-2  col-md-offset-1">
                <button class="btn btn-block btn-primary bg-purple btn-flat margin" id="delAllSel">
                    <i class="fa fa-trash">批量删除</i>
                </button>
            </div>
        </div>
        <%--    联合查询--%>
        <div class="col-md-3">
            <div class="col-xs-4">
                <form action="/userSearchServlet" method="post">
                    <div class="input-group input-group-sm pull-right" style="width: 400px;">
                        <input type="text" name="search" class="form-control " placeholder="搜索" id="likeSearch">
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-default btn-sm" id="simple-search"><i
                                    class="fa fa-search"></i></button>
                            <button type="button" class="btn btn-default btn-sm" title="Advanced-Search"
                                    id="toggle-advanced-search">
                                <i class="fa fa-angle-double-down"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="row" style="display:none;margin: 10px" id="div-advanced-search">
            <div class="form-inline well no-margin">
                <form class="form-inline well no-margin" action="/userSearchServlet" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="userName" class="col-xs-3 control-label">用户名</label>

                            <div class="col-xs-8">
                                <%--<form:input path="username" cssClass="form-control" placeholder="Enter username" />--%>
                                <input id="userName" name="userName" class="form-control" placeholder=" 用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-xs-3 control-label">手机号</label>

                            <div class="col-xs-8">
                                <%--<form:input path="phone" cssClass="form-control" placeholder="Enter phone" />--%>
                                <input id="phone" name="phone" class="form-control" placeholder=" 手机号"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-xs-3 control-label">邮箱</label>

                            <div class="col-xs-8">
                                <%--<form:input path="email" cssClass="form-control" placeholder="Enter email" />--%>
                                <input id="email" name="email" class="form-control" placeholder=" 邮箱"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6 col-sm-offset-6">
                                <button type="submit" class="btn btn-default btn-sm" id="advanced-search"><i
                                        class="fa fa-search"></i> 高级搜索
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <%--    提示消息框--%>
    <div class="login-box-body">
        <c:if test="${message != null}">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> 提示!</h4>
                    ${message}
            </div>
        </c:if>
    </div>
    <%--------------------表格显示内容-------------%>
    <div>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">用户列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th><input type="checkbox" id="checkAll" onclick="selectAll()"/></th>
                            <th>用户编号</th>
                            <th>邮箱</th>
                            <th>用户名</th>
                            <th>联系电话</th>
                            <th>创建日期</th>
                            <th>修改日期</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${allTbUsers}" var="tbUser">
                            <tr>
                                <td><input type="checkbox" class="minimal" name="checkOne" id="${tbUser.id}"/></td>
                                <td>${tbUser.id}</td>
                                <td>${tbUser.email}</td>
                                <td>${tbUser.userName}</td>
                                <td>${tbUser.phone}</td>
                                <td><fmt:formatDate value="${tbUser.created}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                <td><fmt:formatDate value="${tbUser.updated}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                <td>
                                    <div class="btn-group box-body">
                                        <a href="" type="button" onclick="selectById01(${tbUser.id})"
                                           data-toggle="modal"
                                           data-target="#modal-warning" class="btn btn-warning ">
                                            <i class=" fa fa-search">详情</i>
                                        </a>
                                        <a href="" type="button" onclick="update01(${tbUser.id})" data-toggle="modal"
                                           data-target="#modal-danger" class="btn  btn-danger">
                                            <i class="fa fa-edit (alias)">修改</i>
                                        </a>
                                        <a href="/deleteTbUserById?id=${tbUser.id}">
                                            <button type="button" class="btn  btn-warning   fa fa-trash-o"
                                                    onclick="del()">删除
                                            </button>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <li><a href="#">«</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">»</a></li>
                    </ul>
                </div>
                <!-- /.box -->

            </div>
        </section>
    </div>
</div>
</div>
<!---------------------------脚部内容----------------------->
<footer class="main-footer">
    <div class="pull-right hidden-xs">
        <b>Version</b> 2.4.0
    </div>
    <strong>Copyright © 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
    reserved.
</footer>
<%-------------弹框-        --%>
<%--<div class="box-body">
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-danger">
        Launch Danger Modal
    </button>
</div>--%>

<%-- 修改页面弹出框的内容--%>
<div class="modal modal-danger fade in" id="modal-danger" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/updateTbUserById" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">修改页面</h4>
                </div>
                <div class="modal-body">
                    <%--                sdfsdfsdf--%>
                    <table class="table table-condensed">
                        <input type="text" id="userId" name="userId" hidden/>
                        <tr>
                            <td>1.</td>
                            <td>邮箱</td>
                            <td><input type="email" id="userEmail" name="userEmail"></td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <td>用户名</td>
                            <td><input type="text" id="user01" name="userName"></td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <td>密码</td>
                            <td><input type="text" id="userPassword" name="userPassword"></td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <td>电话</td>
                            <td><input type="text" id="userPhone" name="userPhone"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline" data-dismiss="modal">取消</button>
                    </a>
                    <button type="submit" class="btn btn-outline">修改</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%-- 增加页面弹出框的内容--%>
<div class="modal modal-danger fade in" id="modal-danger01" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/addTbUserServlet" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">增加页面</h4>
                </div>
                <div class="modal-body">
                    <%--                sdfsdfsdf--%>
                    <table class="table table-condensed">
                        <input type="text" name="userId" hidden/>
                        <tr>
                            <td>1.</td>
                            <td>邮箱</td>
                            <td><input type="email" name="userEmail"></td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <td>用户名</td>
                            <td><input type="text" name="userName"></td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <td>密码</td>
                            <td><input type="text" name="userPassword"></td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <td>电话</td>
                            <td><input type="text" name="userPhone"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-outline">重置</button>
                    </a>
                    <button type="submit" class="btn btn-outline">提交</button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--用户详情模态框01弹出内容--%>
<div class="modal modal-warning fade in" id="modal-warning" style="display: none; padding-right: 17px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">用户详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-condensed">
                    <input type="text" id="userId01" name="userId" hidden/>
                    <tr>
                        <td>1.</td>
                        <td>邮箱</td>
                        <td><span id="userEmail01"></span></td>
                    </tr>
                    <tr>
                        <td>2.</td>
                        <td>用户名</td>
                        <td><span id="userName01"></span></td>
                    </tr>
                    <tr>
                        <td>3.</td>
                        <td>密码</td>
                        <td><span id="userPassword01"></span></td>
                    </tr>
                    <tr>
                        <td>4.</td>
                        <td>电话</td>
                        <td><span id="userPhone01"></span></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline " data-dismiss="modal">确认</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</body>
</html>
