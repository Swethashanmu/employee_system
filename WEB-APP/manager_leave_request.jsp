<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:forEach var="leave" items="${list}">
  <p>
    Employee ${leave.empId} |
    ${leave.fromDate} → ${leave.toDate}

    <a href="approveLeave?leaveId=${leave.leaveId}">
      Approve
    </a>
  </p>
</c:forEach>