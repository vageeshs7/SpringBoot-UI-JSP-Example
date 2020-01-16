<%@ page import="java.util.List,
				com.ot.springboot.uitest.dom.*"
%>

<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todos");
%>

<html>

<head>
<title>First Web Application</title>
<link rel="stylesheet" type="text/css" href="/testapp/viewFormat.css">
</head>

<body>
    Here are the list of your todos:
    <br/>
    <% if(todoList.size() > 0) {%>
    <table border="1" width="70%" class="dataTable">
    	<tr>
    		<th>ID</th>
    		<th>Description</th>
    		<th>Target Date</th>
    		<th>Is Done</th>
    	</tr>
    	<% for(Todo todo : todoList) {%>
    		<tr>
    			<td><%= todo.getId() %></td>
    			<td><%= todo.getDesc() %></td>
    			<td><%= todo.getTargetDate() %></td>
    			<td><%= todo.isDone() %></td>
    		</tr>
    	<% } %>
    </table>
    
    
    <% } %>
    
    <br/>
    Your Name is : ${name}
</body>

</html>