<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='../css/style.css'>
<title>得点管理システム</title>
</head>
<div class=header >
	<c:import url="/common/header.jsp" />
</div>
<body>
<div class=container >
	<div class="sidebar"><c:import url="/common/navigation.jsp" /></div>

<div class="main-content">
<h2>メニュー</h2>

<div class="left box1">
	<a href="StudentList.action">学生管理</a>
</div>

<div class="left box2">
	<div class="center">
	<a>成績管理</a>
	<a href="#">成績登録</a>
	<a href="TestList.action">成績参照</a>
	</div>
</div>

<div class="left box3">
	<a href="SubjectList.action">科目管理</a>
</div>

</div>
</div>
</body>
<div class="footer02">
	<c:import url="/common/footer.jsp" />
</div>
</html>