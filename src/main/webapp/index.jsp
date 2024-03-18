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
    <label>Id:</label>
    <input id="wineId" name="Id" type="text" disable />

    <label>Name:</label>
    <input id="name" name="name" type="text" disable />

    <label>Grapes:</label>
    <input id="grapes" name="grapes" type="text" disable />

    <label>Country:</label>
    <input id="country" name="country" type="text" disable />

    <label>Region:</label>
    <input id="region" name="region" type="text" disable />

    <label>Year:</label>
    <input id="year" name="year" type="text" disable />
</div>

<div class="rightArea">
<img id="pic" height="300" />

<label>Notes:</label>
<textarea id="description" name="description"></textarea>
</div>
</body>
</html>
