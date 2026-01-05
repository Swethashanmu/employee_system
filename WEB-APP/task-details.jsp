<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Task Details</title>
</head>
<body>
<h2>${task.task_name}</h2>
<p><b>Current Status:</b> ${task.status}</p>
<hr/>
<h3>Task History</h3>
<c:choose>
    <c:when test="${task.status eq 'COMPLETED'}">
        <p><i>Task is completed. History is hidden.</i></p>
    </c:when>
    <c:otherwise>
<c:choose>
    <c:when test="${empty history}">
        <p>No history available.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="h" items="${history}">
            <p>
                <b>Employee ID:</b> ${h.employeeId}<br/>
                <b>Status:</b> ${h.status}<br/>
                <b>Time:</b> ${h.createdAt}<br/>
                <b>Description:</b> ${h.description}
            </p>
            <hr/>
        </c:forEach>
           </c:otherwise>
      </c:choose>
    </c:otherwise>
</c:choose>
<c:if test="${task.status ne 'COMPLETED'}">
<h3>Update Task</h3>
<form action="updateTask" method="post">
    <input type="hidden" name="taskId" value="${task.taskid}" />
    <label>Status:</label><br/>
    <select name="status" required>
        <option value="IN_PROGRESS">IN_PROGRESS</option>
        <option value="COMPLETED">COMPLETED</option>
    </select>
    <br/><br/>
    <label>Description:</label><br/>
    <textarea name="description" rows="4" cols="50" required></textarea>
    <br/><br/>
    <button type="submit">Update Task</button>
</form>
</c:if>
<c:if test="${task.status eq 'COMPLETED'}">
    <p><b>This task is locked and cannot be updated.</b></p>
</c:if>
</body>
</html>

