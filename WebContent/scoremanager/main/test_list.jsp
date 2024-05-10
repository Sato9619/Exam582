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

	<h2><b>成績参照</b></h2>

	<form action = "TestListSubjectExecute.action" method="get">
<div>科目情報
		<th>入学年度
			<select name="f1">
				<option value="0">--------</option>
				<c:forEach var="year" items="${ent_year_set}">
					<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
		</th>
		<th>クラス
			<select name="f2">
			<option value="0">--------</option>
			<c:forEach var="num" items="${class_num_set}">
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
			</c:forEach>
		</select></th>

		<th>科目
			<select name="f3">
			<option value="0">--------</option>
			<c:forEach var="listsubject" items="${listsubject}">
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${listsubject.subject_cd}" <c:if test="${listsubject==f3}">selected</c:if>>${listsubject.subject_name}</option>
			</c:forEach>
		</select></th>
		<button>検索</button>
		</div>

	</form>

	<form action = "TestListStudentExecute.action" method="get">
		<label>学生情報</label>
		<div>学生番号
		<input type="text" name="f4" maxlength="10" placeholder="学生番号を入力してください"
			autocomplete="off" required="True">

		<button>検索</button>
		</div>

	</form>


</body>
</html>