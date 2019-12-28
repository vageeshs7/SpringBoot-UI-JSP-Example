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
    ${todos} 
    <BR/>
    Size = <%= todoList.size() %>
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
    
     <table border="1" class="dataTable" width="100%">
            <tr>
                <th>Sl.no</th>
                <th>Name</th>
                <th>District</th>
                <th>Taluk</th>
                <th>Location</th>
                <th>Office</th>
            </tr>
            <% int i=1;
            for(EmployeeInvoiceDataReport eidReport : eidReportList) { %>
            <tr>
                <th><%= i++ %> </th>
                <th><%= eidReport.getVcaBo().getCandidate().getName() ></th>
                <th><%= eidReport.getVcaBo().getDistrict().getDistrictName() ></th>
                <th><%= eidReport.getVcaBo().getTaluk().getTalukName() ></th>
                <th><%= eidReport.getVcaBo().getLocation().getLocationName() ></th>
                <th><%= eidReport.getVcaBo().getOffice().getOfficeCode() ></th>
            </tr>
            <% } %>
        </table>
</body>

</html>