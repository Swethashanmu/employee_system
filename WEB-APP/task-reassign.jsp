<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<table border="1">
    <tr>
        <th>Task ID</th>
        <th>Task Name</th>
        <th>Employee ID</th>
        <th>Status</th>
    </tr>
    <c:forEach var="task" items="${taskList}">
     <c:if test="${task.status ne 'COMPLETED'}">
        <tr>
            <td>${task.taskid}</td>
            <td>${task.task_name}</td>
            <td>${task.emp_id}</td>
            <td>${task.status}</td>
        </tr>
         </c:if>
    </c:forEach>
</table>
<form action="task_reassign" method="post">
enter the task_id u want to reassign: <input type="number" name="task_id">
enter the emp_id u want to reassign: <input type="number" name="emp_id">
<button type="submit">submit</button>
</form>
