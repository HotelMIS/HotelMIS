<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	function addPerson() {
		window.location.href = '<%=path%>/frames/student/add.jsp';
	}

	function updatePerson() {

	}
</script>
</head>

<body>
	<form id="form1" name="form1" method="post">
		<center>
			<table width="500px" border="1" align="center" cellpadding="0"
				cellspacing="0" bordercolor="#97B0D9">
				<tr>
					<td><input type="button" id="addperson" value="添加用户"
						onclick="addPerson()" />
					</td>
				</tr>
			</table>

			<table width="500px" border="1" align="center" cellpadding="0"
				cellspacing="0" bordercolor="#97B0D9">
				<tbody>
					<s:if test="studentList!=null&&studentList.size>0">
						<tr>
							<td class="thead">id</td>
							<td class="thead">name</td>
							<td class="thead">age</td>
							<td class="thead">cardid</td>
							<td class="thead">cardname</td>
							<td class="thead">update</td>
							<td class="thead">delete</td>
						</tr>
						<s:iterator value="studentList" var="student" status="status">
							<tr>
								<td><s:property value='id' />
								</td>
								<td><s:property value='name' />
								</td>
								<td><s:property value='age' />
								</td>
								<td><s:property value='cardid' />
								</td>
								<td><s:property value='cardname' />
								</td>
								<td><a href="javascript:updatePerson()">修改</a>
								</td>
								<td><a href="javascript:deletePerson()">删除</a>
								</td>
							</tr>
						</s:iterator>

					</s:if>
					<s:else>
						<tr>
							<td class="thead">对不起，没有数据！</td>
						</tr>
					</s:else>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>
