	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

		<div class="row">
			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">	
				<div class="icon-box" data-aos="fade-up" data-aos-delay="100">
					
					
								<table class="table table-striped">
						
						<tbody>
							<tr>
								
								<th>कुल प्राप्ति</th>
								<td>${totalReceived }</td>
								
								
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="200">
					
								<table class="table table-striped">
						
						<tbody>
							<tr>
								
								<th>कुल घोषणा</th>
								<td>${totalAnnounce}</td>
								
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="300">
					
								<table class="table table-striped">
						
						<tbody>
							<tr>
								<th scope="row">कुल बकाया</th>
								<th scope="row">${totalAnnounce-totalReceived }</th>
								
								
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>

<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="300">
					
								<table class="table table-striped">
						
						<tbody>
							<tr>
								<th scope="row">कुल रशीद</th>
								<th scope="row">${totalReceipt }</th>
								
								
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			

		</div>

	</div>
</section>
<!-- End Featured Services Section -->