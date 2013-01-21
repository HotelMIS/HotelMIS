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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>HotelMIS</title>

    <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="app/desktop/css/desktop.css">
    <script type="text/javascript" src="ext/ext-all.js"></script>
    <script type="text/javascript" src="app/desktop/App.js"></script>
    <!-- </x-bootstrap> -->
    <script type="text/javascript">
        Ext.Loader.setPath({
            'Ext.ux.desktop': 'desktop/js',
            MyDesktop: 'app'
        });

        Ext.require('MyDesktop.App');

        var myDesktopApp;
        Ext.onReady(function () {
            myDesktopApp = new MyDesktop.App();
        });
    </script>
    <!-- </x-compile> -->
</head>

<body>

<a href="" target="_blank" alt="Powered by Ext JS"
   id="poweredby"><div></div></a>

</body>
</html>
