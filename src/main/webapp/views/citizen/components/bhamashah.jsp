 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <!-- ======= Team Section ======= -->
    <section id="team" class="team section-bg">
      <div class="container" data-aos="fade-up">

        <div class="section-title">
          <h2>भामाशाह</h2>
          <h3>हमारे उच्चतम <span>भामाशाह</span></h3>
          <p>प्रेम से बड़ा कोई धर्म नहीं होता हैं,
दान से बड़ा कोई कर्म नहीं होता हैं.</p>
        </div>


        <div class="row">

<c:forEach items="${topTen}" var="TopTenBhamashah" varStatus="loop">
          <div class="col-lg-3 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="100">
            <div class="member">
              <div class="member-img">
                <!-- <img src="assets/img/team/dummy.png" class="img-fluid" alt=""> -->
                <img src="data:image/img;base64,${TopTenBhamashah.photo} " class="img-fluid" alt="">
                <div class="social">
                  <h4>${TopTenBhamashah.address}</h4>
                </div>
              </div>
              <div class="member-info">
                <h4>${TopTenBhamashah.name} </h4>
                <h5 style="color: black;"><b>सहयोग राशि :-  ${TopTenBhamashah.grandTotal}</b></h5>
              </div>
            </div>
          </div>
         </c:forEach>
     </div>

      </div>
    </section><!-- End Team Section -->