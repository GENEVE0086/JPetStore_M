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
			</tr><font color="red"></font>
		</table>${sessionScope.message}

		<%@ include file="IncludeAccountFields.jsp"%>

		<input type="submit" value="Save Account Information">

	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>