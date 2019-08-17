<%@ page import="java.util.List,
				com.ot.springboot.uitest.dom.*"
%>

<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todos");
%>

<html>

<head>
<title>First Web Application</title>
</head>

<body>
    Here are the list of your todos:
    ${todos} 
    <BR/>
    Size = <%= todoList.size() %>
    <br/>
    <% if(todoList.size() > 0) {%>
    <table>
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