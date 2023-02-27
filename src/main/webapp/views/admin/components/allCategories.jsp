<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>





<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

<table id="myTable" class="display">
    <thead>
        <tr>
            <th>क्रम संख्या</th>
            <th>श्रेणी का नाम </th>
            
            <th>Update </th>
            
            
            
           
            
        </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${allCategories}" var="allCategories"
												varStatus="loop">
    
    
        <tr>
            <td>${loop.index+1}</td>
            <td class="name">${allCategories.catName }</td>
            
            <td><button  id="updateCat" onclick="updateCat('${allCategories.catId }','${allCategories.catName }')">Update
		Category</button></td>
            
         
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
function updateCat(id,catName){
	
	 /* $("#catNameE").val(catName); */
	$("#catNameE").val(catName);
	$("#idE").val(id);
		$("#modaltitle").html("Update Category");
		$('#addCatModal').modal('show');
	
}
</script>
