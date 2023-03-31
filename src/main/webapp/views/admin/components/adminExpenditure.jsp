<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

<table id="myTable" class="display">
    <thead>
        <tr>
            <th>क्रम संख्या</th>
            <th>प्राप्तकर्ता का नाम </th>
            <th>बाउचर न </th>
            <th>Details </th>
            <th>Amount  </th>
            <th>दिनांक </th>
            <th>Category </th>
            <th>Action </th>
         </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${allExpenditure}" var="allExpenditure"
												varStatus="loop">
    
    
        <tr>
            <td>${loop.index+1}</td>
            <td class="name">${allExpenditure.receiverName }</td>
            <td>${allExpenditure.expdReceiptNo }</td>
            <td>${allExpenditure.expdDetail }</td>
            <td>${allExpenditure.expdAmount }</td>
            <td>${allExpenditure.expdDate }</td>
            <td>${allExpenditure.expenditureCatEntity.catName }</td>
            <%-- <td><button onclick="editGairAarthik('${allGairAarthik.id }','${allGairAarthik.name }',
            '${allGairAarthik.address }','${allGairAarthik.mobile }','${allGairAarthik.sahyogDetail}')">Edit</button></td> --%>
       <td>
       
     <button class="btn btn-secondary" onclick="editEx('${allExpenditure.expdId }',
     '${allExpenditure.receiverName }',
            '${allExpenditure.expdReceiptNo }',
            '${allExpenditure.expdDetail }','${allExpenditure.expdAmount }',
            '${allExpenditure.expdDate}',
            '${allExpenditure.expenditureCatEntity.catId}')"> Edit </button> 
       
      
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

function editEx(id, name, receipt,detail, amount, date,catId) {
	$("#nameE").val(name);
	var x = document.createElement("INPUT");
	x.setAttribute("type", "hidden");
	x.setAttribute("value", id);
	x.setAttribute("name", "expdId");
	editExpenditure.appendChild(x);
	$("#expdDetailE").val(detail);
	$("#amountE").val(amount);
	$("#expdDateE").val(date);
	$("#receiptE").val(receipt);
	$("#catIdE").val(catId);
	$("#modaltitle").html("Update Expenditure");

	$('#addExpdModal').modal('show');
}


</script>
