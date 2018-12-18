<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
  <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

  <h2>History</h2>

  <table>
    <tr>
      <th>Item ID</th>
      <th>View Date</th>
    </tr>
    <c:forEach var="record" items="${sessionScope.recordList}">
      <tr>
        <td>
          <a href="viewItem?itemId=${record.item}">${record.item}</a>
        </td>
        <td>${record.date}</td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
