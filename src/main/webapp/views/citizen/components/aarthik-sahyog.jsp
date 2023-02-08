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
            <th>विवरण देखें </th>
            
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
            <c:if test="${allAnnouncement.grandTotal>0 }">
            
            <td >
          <form method="post">
           
            <button type="button" class="btn btn-sm btn-primary" id="myModal" 
            onclick="openModal('${allAnnouncement.name}','${allAnnouncement.mobile}')">
            Show Details
            </button>
            </form>
           
            </td>
            </c:if>
            <c:if test="${allAnnouncement.grandTotal==0 }">
            <td>
            All Pending
            </td>
            </c:if>
        </tr>
        </c:forEach>
    </tbody>
</table>
</div>
</section>
 <script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">

function openModal(name,mobile) {
	
    //var name = $("#name").val();
    //var marks = $("#marks").val();
    
    $(".modal-title").html(name);
    
    $.ajax({
        type : "POST",
        //dataType: "json",
        url : "/getAllArthikSahyogAnnounceByMobileNo",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(mobile),
        success : function(response) {
            console.log( response );
        },
        error : function() {
            alert("not working");
        }
    });
   
   $(".modal-body").html(mobile);
    $("#exampleModal").modal('show');
};

$(document).ready( function () {
    $('#myTable').DataTable();
    
    

} );
</script>
