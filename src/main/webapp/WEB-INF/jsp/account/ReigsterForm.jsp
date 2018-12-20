<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
  <form action="newAccount" method="post">
    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td>
        <td><input type="text" name="username" value="${sessionScope.username}"/></td>
      </tr>
      <tr>
        <td>New password:</td>
        <td><input type="password" name="password" /></td>
      </tr>
      <tr>
        <td>Repeat password:</td>
        <td><input type="password" name="repeatedPassword" /></td>
      </tr>
    </table><font color="red">${sessionScope.message}</font>

    <%@ include file="IncludeAccountFields.jsp"%>
    <br/>
    verify code:
    <input  type="text" name="checkCode"/>
    <br/>
    <img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>/verify"/>
    <a href="javascript:reloadCode();">change one </a>
    <br>

    <input type="submit" value="Save Account Information">

  </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>