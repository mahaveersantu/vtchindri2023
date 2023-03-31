<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Veer Teja Mandir Chindri-Aarthik Sahyog</title>
<meta content="" name="description">
<meta content="" name="keywords">
<%-- <%@include file="../common/js.jsp"%> --%>
<%@include file="../common/css.jsp"%>
</head>

<body>
	<%@include file="../common/admin-navbar.jsp"%>

	<main id="main">
		<div class="mt-1"></div>
		<section id="featured-services" class="featured-services">
<div class="d-flex justify-content-center"><h3 style="color: blue;" >-:आर्थिक सहयोग:-</h3></div>
<div class="text-center">
<a href="${pageContext.request.contextPath}/adminAllReceipt" class="btn btn-success ml-4">-:सभी रशीद देखे:-</a>
<a  href="${pageContext.request.contextPath}/adminRefresh" class="btn btn-success ml-4">-:रिफ्रेश करें:-</a>
</div>
</section>
		<%@include file="../components/adminAddAnnouncement.jsp"%> 
		<%@include file="../components/adminAddReceipt.jsp"%> 
		 <%@include file="../components/aarthik-sahyog-detail.jsp"%>  
		<%-- <div class="mt-1"></div>
		<%@include file="../components/aarthik-sahyog.jsp"%>  --%>
	 </main>	
	<%@include file="../common/js.jsp"%>  
</body>

</html>