<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 下記の一文の宣言がないとJSTLを使用できない -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>

	<h2>科目管理</h2>
	<a href="SubjectCreate.action">新規登録</a>


			<table class="table table-hover">
				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="subject" items="${subject}">
					<tr>
						<td>${subject.subject_cd}</td>
						<td>${subject.subject_name}</td>
						<td class="text-center">

						</td>
						<td><a href="#">変更</a></td>
						<td><a href="#">削除</a></td>
					</tr>
				</c:forEach>
			</table>


</body>
</html>