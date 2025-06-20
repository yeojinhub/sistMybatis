<%@page import="day0613.SelectService6"%>
<%@page import="kr.co.sist.domain.CpEmp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#frm").submit();
		
		});//click
		
});//ready
</script>

<h2>dynamic set</h2>
<div>
	<h3>사원번호를  선택해 주세요</h3>
	<input type="hidden" name="url" value="${ param.url }"/>
<%
SelectService6 ss = new SelectService6();
pageContext.setAttribute("empnoList", ss.searchAllEmpno());
%>
<c:forEach var="empno" items="${ empnoList }">
<a href="index.jsp?url=${ param.url }&empno=${ empno }">
	<c:out value="${ empno }"></c:out>
</a>
</c:forEach>
</div>
<div>
<c:if test="${ not empty param.empno }">
<%
String paramEmpno=request.getParameter("empno");
if( paramEmpno != null ){
pageContext.setAttribute("empDomain", ss.searchOneEmp(Integer.parseInt(paramEmpno)));
} // endif
%>
		<h2>사원정보</h2>
		<form id="frm" action="index.jsp">
			<input type="hidden" name="url" value="${ param.url }" />
			<input type="hidden" name="empno" value="${ empDomain.empno }" />
			<input type="hidden" name="updateFlag" value="true" />

			<table class="table table-bordered">
				<tr>
					<td><strong>사원명</strong></td>
					<td><input type="text" name="ename" value="${ empDomain.ename }" /></td>
				</tr>
				<tr>
					<td><strong>직무</strong></td>
					<td><input type="text" name="job" value="${ empDomain.job }" /></td>
				</tr>
				<tr>
					<td><strong>연봉</strong></td>
					<td><input type="text" name="sal" value="${ empDomain.sal }" /></td>
				</tr>
				<tr>
					<td><strong>보너스</strong></td>
					<td><input type="text" name="comm" value="${ empDomain.comm }" /></td>
				</tr>
				<tr>
					<td><strong>입사일</strong></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${ empDomain.hiredate }" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="button" id="btn" value="변경" class="btn btn-success btn-sm" />
					</td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${ not empty param.updateFlag }">
	<jsp:useBean id="ceDTO" class="day0602.CpEmpDTO" scope="page" />
	<jsp:setProperty property="*" name="ceDTO" />
	<%
	pageContext.setAttribute("updateFlag", ss.modifyEmp(ceDTO));
	%>
	<script type="text/javascript">
	var msg="사원 정보가 변경되지 않았습니다.";
	
	if( ${ pageScope.updateFlag} ){
		msg="사원 정보가 변경되었습니다.";
	} //end if
	alert(msg);
	</script>
	</c:if>
</div>