<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

<table id="myTable" class="display">
    <thead>
        <tr>
            <th>क्रम संख्या</th>
            <th>सहयोगकर्ता का नाम </th>
            <th>पता </th>
            <th>घोषणा राशि </th>
            <th>जमा राशि </th>
            <th>बकाया राशि </th>
            <th>Update </th>
            <th>Add Receipt </th>
            <th>रशीद विवरण </th>
           
            
        </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${allAnnouncement}" var="allAnnouncement"
												varStatus="loop">
    
    
        <tr>
            <td>${loop.index+1}</td>
            <td class="name">${allAnnouncement.name }</td>
            <td>${allAnnouncement.address }</td>
            <td>${allAnnouncement.announceAmount }</td>
            <td>${allAnnouncement.grandTotal }</td>
            <td>${allAnnouncement.pendingAmount }</td>
            <td>Update</td>
            <td>Add Receipt</td>
          <td>
          <table>
          <c:forEach items="${allAnnouncement.aarthikSahyogEntity}" var="allReceipt"
												varStatus="loop">
					
					<tr>
					<td>
					<td>${allReceipt.receiptNo }</td>
					<td>${allReceipt.receiptDate }</td>
					<td>${allReceipt.amount }</td>
					<td>Edit</td>
					<td>Delete</td>
					
					</td>
					</tr>					
												</c:forEach>
												</table>
          </td>
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
