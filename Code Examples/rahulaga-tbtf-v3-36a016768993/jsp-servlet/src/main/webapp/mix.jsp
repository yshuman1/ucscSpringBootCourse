<html>
<head></head>

<body>
<b>
This is plain HTML. Now some <%="jsp" %>
</b>
<%
String foo="bar";
int x=4;

%>

<br/>
<br/>
<% out.write("foo = "+foo); %>

</body>

</html>