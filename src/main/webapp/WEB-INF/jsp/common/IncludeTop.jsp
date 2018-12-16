<%--
  Created by IntelliJ IDEA.
  User: GENEV
  Date: 2018/12/11
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.net.URLDecoder"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
        media="screen"/>

  <meta name="generator"
        content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org"/>
  <title>My PetStore</title>
  <meta content="text/html; charset=windows-1252"
        http-equiv="Content-Type"/>
  <meta http-equiv="Cache-Control" content="max-age=0"/>
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <meta http-equiv="expires" content="0"/>
  <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
  <meta http-equiv="Pragma" content="no-cache"/>

  <script>
    function reloadCode() {
      var time = new Date().getTime();
      document.getElementById("imagecode").src = "<%= request.getContextPath()%>/verify?d=" + time;
    }
  </script>
</head>

<body>

<div id="Header">

  <div id="Logo">

    <div id="LogoContent">
      <a href="main"><img src="images/logo-topbar.gif"/></a>
    </div>

  </div>

  <div id="Menu">

    <div id="MenuContent">
      <a href="viewCart"><img align="middle" name="img_cart" src="images/cart.gif"/></a>
      <img align="middle" src="images/separator.gif"/>
      <c:if test="${sessionScope.account == null}">
        <a href="signonForm">Sign In</a>
      </c:if>

      <c:if test="${sessionScope.account != null}">
        <a href="signoff">
          Sign Out</a>
        <img align="middle" src="images/separator.gif"/>
        <a href="editAccountForm">
          My Account
        </a>
        <a href="#">
          History
        </a>
      </c:if>
      <img align="middle" src="images/separator.gif"/>
      <a href="help.html">?</a>
    </div>

  </div>

  <div id="Search">

    <div id="SearchContent">
      <form action="searchProduct" method="post">
        <input type="text" name="keyword" size="14"/>
        <input type="submit" name="searchProducts" value="Search"/>
      </form>
    </div>

  </div>

  <div id="QuickLinks">
    <a href="viewCategory?categoryId=FISH"><img src="images/sm_fish.gif"/></a>
    <img src="images/separator.gif"/>
    <a href="viewCategory?categoryId=DOGS"><img src="images/sm_dogs.gif"/></a>
    <img src="images/separator.gif"/>
    <a href="viewCategory?categoryId=REPTILES"><img src="images/sm_reptiles.gif"/></a>
    <img src="images/separator.gif"/>
    <a href="viewCategory?categoryId=CATS"><img src="images/sm_cats.gif"/></a>
    <img src="images/separator.gif"/>
    <a href="viewCategory?categoryId=BIRDS"><img src="images/sm_birds.gif"/></a>
  </div>

</div>

<div id="Content">
