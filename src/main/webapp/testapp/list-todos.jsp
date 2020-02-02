<%@ page import="java.util.List,
				com.ot.springboot.uitest.dom.*,
				java.text.SimpleDateFormat"
%>

<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todos");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
%>

<html>

<head>
<title>First Web Application</title>
<link rel="stylesheet" type="text/css" href="/testapp/viewFormat.css">
</head>

<body>
<div style="align-items: center;">
	<h2>Todo List for ${name}</h2>
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
    			<td><%= dateFormat.format(todo.getTargetDate()) %></td>
    			<td><%= todo.isDone() %></td>
    		</tr>
    	<% } %>
    </table>
    
    
    <% } %>
	<br/>
	<a href="/add-new-todo"><button value="">Add New</button></a>
	<br/>
</div>
</body>

</html>