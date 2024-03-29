<!DOCTYPE HTML>
<html>
<head>
    <title>Cellar</title>
    <link rel="stylesheet" href="css/styles.css" />
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/main.js"></script>
</head>

<body>
<h2>Welcome to the WineCellar Application</h2>


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
