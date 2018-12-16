<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="login" method="post">

		<p>Please enter your username and password.</p>
		<p>Username:
			<input type="text" name="username" value="${sessionScope.username}">
			<br/>
		Password:
			<input type="password" name="password">
      <br/>
			verify code:
			<input  type="text" name="checkCode"/>
      <br/>
			<img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>/verify"/>
			<a href="javascript:reloadCode();">change one </a>
			<br>
		</p>

		<font color="red">${sessionScope.message}</font>
		<input type="submit" name="signon" value="Login">
	</form>

	Need a user name and password?
	<a href="reigsterForm">
		Register Now!
	</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>

