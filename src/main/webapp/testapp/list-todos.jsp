<%@ page import="java.util.List,
				com.ot.springboot.uitest.dom.*,
				java.text.SimpleDateFormat"
%>

<%
	List<Todo> todoList = (List<Todo>)request.getAttribute("todos");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <title>Todo List</title>
  </head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Todo App</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link active" href="/list-todos">Todo List</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="/add-new-todo">Add New</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
<div class="container justify-content-center">
	<h2>Todo List for ${name}</h2>
    <br/>
    <% if(todoList.size() > 0) {%>
    <table border="1" width="70%" class="table table-hover">
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
	<a href="/add-new-todo" class="btn btn-primary">Add New</a>
	<br/>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>

</html>