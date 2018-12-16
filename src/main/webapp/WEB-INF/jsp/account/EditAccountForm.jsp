<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="confirmEdit" method="post">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="password" name="repeatedPassword" /></td>
		</tr>
	</table>
	<font color="red">${sessionScope.message}
	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="editAccount" value="Save Account Information" />

	</form>
	<a href="orderList">My Orders</a>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
