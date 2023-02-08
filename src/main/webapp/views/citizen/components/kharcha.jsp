<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

		<div class="row">
			<div
				class="offset-md-2 col-md-4 col-lg-4 d-flex align-items-stretch mb-2 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="100">


					<table class="table table-striped">

						<tbody>
							<tr>

								<th>कुल खर्चा</th>
								<td>152200</td>


							</tr>

						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-4 col-lg-4 d-flex align-items-stretch mb-2 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="200">
					<form action="/getExpenditureByCategory" method="post">
					<h4 class="title">
						<a href="">श्रेणीवार खर्चा</a>
					</h4>
					<select class="form-select" name="catId">
						<option value="0" selected>All</option>

						<c:forEach items="${allCat}" var="allCat">
							<option value="${allCat.catId }">${allCat.catName }</option>

						</c:forEach>
					</select>
					<div class="text-center mt-3">
					
					<button type="submit" class="btn btn-primary">Search</button>
					</div>
					</form>
					
				</div>
			</div>


		</div>

	</div>
</section>

<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

		<table id="myTable" class="display">
			<thead>
				<tr>
					<th>क्रम संख्या</th>
					<th>बाउचर संख्या</th>
					<th>खर्च राशि</th>
					<th>भुगतान प्राप्तकर्ता का नाम</th>
					<th>खर्चा विवरण</th>
					<th>दिनांक</th>


				</tr>
			</thead>
			<tbody>


				<c:forEach items="${allKharcha}" var="allKharcha" varStatus="loop">

					<tr>
						<td>${loop.index+1}</td>
						<td>${allKharcha.expdReceiptNo}</td>
						<td>${allKharcha.expdAmount}</td>
						<td>${allKharcha.receiverName}</td>
						<td>${allKharcha.expdDetail}</td>
						<td>${allKharcha.expdDate}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myTable').DataTable();
	});
</script>
