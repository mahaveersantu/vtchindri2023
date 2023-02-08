<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ======= Featured Services Section ======= -->
<section id="featured-services" class="featured-services">
	<div class="container" data-aos="fade-up">

		<div class="row">
			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">	
				<div class="icon-box" data-aos="fade-up" data-aos-delay="100">
					
					 <h4 class="title"><a href=""> गूगल / फ़ोन पे</a></h4>
								<table class="table table-striped">
						
						<tbody>
							<tr>
								
								<td>Name</td>
								<td>VEER TEJAJI TRUST CHINDRI</td>
							</tr>
							<tr>
							
								<td>GooglePay No.</td>
								<td>7891387058</td>
							</tr>
							<tr>
								
								<td>PhonePay No</td>
								<td>7891387058</td>
							</tr>
							<tr>
								
								<td>Paytm</td>
								<td>7891387058</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="200">
					<h4 class="title"><a href=""> कुल प्राप्ति और खर्चा </a></h4>
								<table class="table table-striped">
						
						<tbody>
							<tr>
								
								<td>कुल घोषणा</td>
								<td>--->></td>
								
								<td>${totalAnnounce}</td>
							</tr>
							<tr>
								
								<td>कुल प्राप्ति</td>
								<td>--->></td>
								
								<td>${totalReceived}</td>
							</tr>
							<tr>
							
								<td>कुल खर्चा</td>
								<td>--->></td>
								
								<td>${totalExpd }</td>
							</tr>
							<tr>
							
								<td>कुल बचत</td>
							<td>--->></td>
								
								<td>${totalReceived-totalExpd}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="300">
					<h4 class="title"><a href="">गत मास का कुल प्राप्ति और खर्चा</a></h4>
								<table class="table table-striped">
						
						<tbody>
							<tr>
								<th scope="row">गत मास का कुल प्राप्ति</th>
								
								
							</tr>
							<tr>
								<th scope="row">${totalLastMonthReceived} </th>
								
								
							</tr>
							<tr>
								<th scope="row">गत मास का कुल खर्चा</th>
							
								
							</tr>
							<tr>
								<th scope="row">${totalExpndLastMonth }</th>
								
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div
				class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
				<div class="icon-box" data-aos="fade-up" data-aos-delay="400">
					<h4 class="title"><a href="">ट्रस्ट बैंक खाता विवरण</a></h4>
								<table class="table table-striped">
						<tbody>
							<tr>
								
								<td>खाता धारक</td>
								<td>CHINDRI SHRI VEER TEJAJI MANDIR VIKASH TRUST</td>
							</tr>
							<tr>
							
								<td>बैंक नाम</td>
								<td>ICICI BANK LTD</td>
							</tr>
							<tr>
								
								<td>खाता संख्या</td>
								<td>415305000209</td>
							</tr>
							<tr>
								
								<td>IFSC कोड</td>
								<td>ICIC0004153</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
</section>
<!-- End Featured Services Section -->
