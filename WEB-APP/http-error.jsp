<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>⚠ Request Failed</h2>
<p>
    <b>Error Code:</b> ${pageContext.errorData.statusCode}
</p>
<p>
    <b>Message:</b>
    <%
        int code = pageContext.getErrorData().getStatusCode();
        if (code == 404) out.print("Page not found.");
        else if (code == 403) out.print("Access denied.");
        else if (code == 400) out.print("Bad request.");
        else out.print("Server error.");
    %>
</p>
<a href="index.html">Go Back</a>
</body>
</html>
