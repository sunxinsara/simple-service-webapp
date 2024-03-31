<!DOCTYPE HTML>
<html>
<head>
    <title>Cellar</title>
    <link rel="stylesheet" href="css/styles.css" />
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/datatables.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/main.js"></script>
    <link rel="stylesheet" href="css/datatables.css"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/font-awesome.css"/>
    <link rel="stylesheet" href="css/styles.css"/>
</head>

<body>
<h1>Welcome to the WineCellar Application</h1>
<ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#home" role="tab" data-toggle="tab"> <span class="fa fa-car"></span>  Home</a></li>
    <li><a href="#products" role="tab" data-toggle="tab"><span class="fa fa-cog"></span>Products</a></li>
    <li><a href="#other" role="tab" data-toggle="tab"><span class="fa fa-spinner"></span> Other</a></li>
</ul>

<div class="leftArea">
    <ul id="wineList"></ul>
</div>
<div class="mainArea">
    <label for="searchKey">search for wine</label>
    <input type="text" id="searchKey" name="searchKey">
    <button type="button" id="btnSearch">Search</button>
    <button type="button" id="btnAdd">New Wine</button>

    <label>Id:</label>
    <input id="wineId" name="Id" type="text" disabled  />

    <label>Name:</label>
    <input id="name" name="name" type="text" disabled  />

    <label>Grapes:</label>
    <input id="grapes" name="grapes" type="text" disabled  />

    <label>Country:</label>
    <input id="country" name="country" type="text" disabled  />

    <label>Region:</label>
    <input id="region" name="region" type="text" disabled  />

    <label>Year:</label>
    <input id="year" name="year" type="text" disabled  />

    <button type="button" id="btnEdit">Edit</button>
    <button type="button" id="btnUpdate">Update</button>
    <button type="button" id="btnSave">Save</button>
    <button type="button" id="btnDelete">Delete</button>

</div>

<div class="rightArea">
<img id="pic" height="300" />

<label>Notes:</label>
<textarea id="description" name="description"></textarea>
</div>
</body>
</html>
