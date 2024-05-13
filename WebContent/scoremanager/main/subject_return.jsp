<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>
<h2>削除済み科目</h2>



			<table class="table table-hover">
				<tr>
					<th>科目コード</th>
					<th>科目名</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="subject" items="${subjects}">
					<tr>
						<td>${subject.subject_cd}</td>
						<td>${subject.subject_name}</td>
						<td class="text-center">


						</td>
						<td><a href="SubjectReturnExecute.action?no=${subject.subject_cd}">元に戻す</a></td>

					</tr>
				</c:forEach>
			</table>



</body>
</html>