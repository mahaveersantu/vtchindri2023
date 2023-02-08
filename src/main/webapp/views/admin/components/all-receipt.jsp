<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

<table id="myTable" class="display">
    <thead>
        <tr>
            <th>क्रम संख्या</th>
            <th>रशीद संख्या </th>
            <th>रशीद दिनांक </th>
            <th>जमा राशि </th>
            <th>सहयोगकर्ता का नाम </th>
            <th>पता </th>
            
           
        </tr>
    </thead>
    <tbody>
    
     <c:forEach items="${allReceipt}" var="allReceipt"
												varStatus="loop">
					
					<tr>	
									
					<td>${loop.index+1}</td>
					<td>${allReceipt.receiptNo }</td>
					<td>${allReceipt.receiptDate }</td>
					<td>${allReceipt.amount }</td>
					<td>${allReceipt.aarthikSahyogAnnouncementEntity.name }</td>
					<td>${allReceipt.aarthikSahyogAnnouncementEntity.address }</td>
					
					
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
