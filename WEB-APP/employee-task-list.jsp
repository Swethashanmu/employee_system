<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Status</th>
    <th>Action</th>
  </tr>
  <c:if test="${not empty taskList}">
    <c:forEach var="task" items="${taskList}">
      <tr>
        <td>${task.taskid}</td>
        <td>${task.task_name}</td>
        <td>${task.status}</td>
        <td>
          <a href="${pageContext.request.contextPath}/taskDetails?taskId=${task.taskid}">
            View / Update
          </a>
        </td>
      </tr>
    </c:forEach>
  </c:if>
</table>
