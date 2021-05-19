<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <title>Add New Todo</title>
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
					<li class="nav-item"><a class="nav-link" href="/list-todos">Todo List</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="/add-new-todo">Add New</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
    <div class="container justify-content-center">
		<form method="post">
			<div class="card">
				<h5 class="card-header">New Todo</h5>
				<div class="card-body">
					<div class="mb-3 row">
						<label for="inputDesc" class="col-sm-2 col-form-label">Description</label>
						<div class="col-sm-10">
							<input class="form-control form-control-lg" type="text" name="desc" id="inputDesc"/>
						</div>
					</div>
					<div class="mb-3 row">
						<label for="targetDateStr" class="col-sm-2 col-form-label">Target Date</label>
						<div class="col-sm-10">
							<input class="form-control form-control-lg" type="date" name="targetDateStr" id="targetDateStr"/>
						</div>
					</div>
					<div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                     	<input type="submit" class="btn btn-success" value="Add Item"/>
                    </div>
				</div>
			</div>
		</form>
	</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</body>

</html>