<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>
      HotelMIS
    </title>
    <!-- 
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
     -->
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/logincssmini.css" />
  </head>

  <body>

    <div 
        id="loginDiv"
        style="margin-top: -200px;position: absolute;top: 50%;left: 50%;height: 200px;width: 400px;margin-left: -200px">
      <h3>
        <font color=red>
          <s:property value="#request.msg" />
        </font>
      </h3>
      <s:form action="loginAction" namespace="/" id="loginForm" method="post">
        <fieldset id="inputs">
          <input id="username" name="username" type="text" placeholder="用户名" autofocus required value="admin"/>
        </br>
        <input id="password" name="password" type="password" placeholder="密码" required value="admin"/>
      </fieldset>
      <fieldset id="actions">
        <input id="submit" type="submit" value="登录" />
        <input id="reset" type="reset" value="重置" />
      </fieldset>

    </s:form>
  </div>

</body>
</html>
