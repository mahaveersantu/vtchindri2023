<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%--  <section id="topbar" class="d-flex align-items-center">
    <div class="container d-flex justify-content-center justify-content-md-between">
      <div class="contact-info d-flex align-items-center">
        <!-- <i class="bi bi-envelope d-flex align-items-center"><a href="mailto:contact@example.com">veertejasangaliya@gmail.com</a></i> -->
        <i class="bi bi-phone d-flex align-items-center ms-4"><span>9829699996 ,8079058228, 9057567917</span></i>
      </div>
      <div class="social-links d-none d-md-flex align-items-center">
        <!-- <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
        <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
        <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
        <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></i></a> -->
       Total Visitors: <%=String.valueOf(session.getAttribute("visitors"))%>
      </div>
    </div>
  </section> --%>

  <!-- ======= Header ======= -->
  <header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <h1 class="logo">ADMIN HOME<span>.</span></a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt=""></a>-->

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="${pageContext.request.contextPath}/adminHome">होम</a></li>
          
          <!-- <li><a class="nav-link scrollto" href="#services">Samiti </a></li> -->
         
         
          <li class="dropdown"><a href="#"><span>सहयोग</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="${pageContext.request.contextPath}/adminShowAarthikSahyog">आर्थिक सहयोग</a></li>
              <!-- <li class="dropdown"><a href="#"><span>Deep Drop Down</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
              <li><a href="#">Drop Down 2</a></li>
              <li><a href="#">Drop Down 3</a></li> -->
              <li><a href="${pageContext.request.contextPath}/showGairAarthikSahyog">गैर आर्थिक</a></li>
            </ul>
          </li>
          
          <li class="dropdown"><a  href="#"><span>खर्च</span><i class="bi bi-chevron-down"></i></a>
          <ul>
          <li><a class="nav-link scrollto " href="${pageContext.request.contextPath}/adminShowAllCat">खर्च श्रेणिया</a></li>
          <li><a class="nav-link scrollto " href="${pageContext.request.contextPath}/showKharcha">खर्च विवरण</a></li>
        </ul>
          </li>
          
          
           <li class="dropdown"><a href="#"><span>ट्रस्ट</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
          <li><a class="nav-link scrollto " href="${pageContext.request.contextPath}/showSamitiMember">ट्रस्ट सदस्य</a></li>
          <li><a class="nav-link scrollto " href="${pageContext.request.contextPath}/showUdeshy">ट्रस्ट उदेश्य</a></li>
        </ul>
        </li>
         <li><a class="nav-link scrollto" href="#about">गेलेरी</a></li>
         <li><a class="nav-link scrollto" href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->