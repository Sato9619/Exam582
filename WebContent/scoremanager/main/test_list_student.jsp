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
			<select name="f1"required>
				<option value="">--------</option>
				<c:forEach var="year" items="${ent_year_set}">
					<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
					<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
		</th>
		<th>クラス
			<select name="f2"required>
			<option value="">--------</option>
			<c:forEach var="num" items="${class_num_set}">
				<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
			</c:forEach>
		</select></th>

		<th>科目
			<select name="f3"required>
			<option value="">--------</option>
			<c:forEach var="listsubject" items="${listsubject}">
				<%-- 現在のlistsubjectと選択されていたf3が一致していた場合selectedを追記 --%>
				<option value="${listsubject.subject_cd}" <c:if test="${listsubject.subject_name==f3}">selected</c:if>>${listsubject.subject_name}</option>
			</c:forEach>
		</select></th>
		<button>絞込み</button>
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

	<c:choose>
		<c:when test="${testliststudent.size()>0}">
			<div>検索結果：${student.name}(${student.no})</div>

			<table class="table table-hover">
				<tr>
					<th>科目名</th>
					<th>科目コード</th>
					<th>回数</th>
					<th>点数</th>
				</tr>

			 	<c:forEach var="tliststudent" items="${testliststudent}">
					<tr>
						<td>${tliststudent.subjectName}</td>
						<td>${tliststudent.subjectCd}</td>
						<td>${tliststudent.time}</td>
						<td>${tliststudent.point}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<c:choose>
			<c:when test="${student != null}">
				<div>検索結果：${student.name}(${student.no})</div>
				<div>成績情報が存在しませんでした</div>
			</c:when>
			<c:otherwise>
				<div>成績情報が存在しませんでした</div>
			</c:otherwise>
			</c:choose>
			</c:otherwise>
	</c:choose>


</body>
</html>