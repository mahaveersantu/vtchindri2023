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
            <th>मोबाइल न </th>
            <th>सहयोग विवरण </th>
            <th>Action </th>
         </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${allGairAarthik}" var="allGairAarthik"
												varStatus="loop">
    
    
        <tr>
            <td>${loop.index+1}</td>
            <td class="name">${allGairAarthik.name }</td>
            <td>${allGairAarthik.address }</td>
            <td>${allGairAarthik.mobile }</td>
            <td>${allGairAarthik.sahyogDetail }</td>
            <td><button class="btn btn-primary" 
            onclick="editGairAarthik('${allGairAarthik.id }','${allGairAarthik.name }',
            '${allGairAarthik.address }','${allGairAarthik.mobile }','${allGairAarthik.sahyogDetail}')">Edit</button></td>
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
function editGairAarthik(id,name,address,mobile,sahyog)
{
	$("#nameE").val(name);
	$("#addressE").val(address);
	$("#mobileE").val(mobile);
	$("#detailsE").val(sahyog);
	$("#idE").val(id);
	$("#modaltitle").html("Update Gair Aarthik");
	 $('#addGairModal').modal('show'); 
	}

</script>
