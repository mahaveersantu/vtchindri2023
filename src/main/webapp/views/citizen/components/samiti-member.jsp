<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

<table id="myTable" class="display">
    <thead>
        <tr>
            <th>क्रम संख्या</th>
            <th>सदस्य का नाम </th>
            <th>पता </th>
            <th>पद </th>
            <th>मोबाइल </th>
            
           
            
        </tr>
    </thead>
    <tbody>
    
    
     <c:forEach items="${samitiMember}" var="samitiMember"
												varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${samitiMember.memberName }</td>
            <td>${samitiMember.memberAddress }</td>
            <td>${samitiMember.memberDesig }</td>
            <td>${samitiMember.memberMobile }</td>
           
        </tr>
</c:forEach>
    </tbody>
</table>
</div>
</section>
 <script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>
