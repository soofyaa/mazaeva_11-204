<!DOCTYPE html>
<html>
<head>
    <title>Hello World Page</title>
</head>
<body>
<h2>Hello World!</h2>

<form action="http://localhost:8080/servlets/name" method="GET">
    <input type="submit" value="Go to /name">
</form>
<form action="http://localhost:8080/servlets/createPet" method="GET">
    <input type="submit" value="Go to /createPet">
</form>
<form action="http://localhost:8080/servlets/getPets" method="GET">
    <input type="submit" value="Go to /getPets">
</form>
<form action="http://localhost:8080/servlets/removePet" method="GET">
    <input type="submit" value="Go to /removePet">
</form>
<form action="http://localhost:8080/servlets/updatePet" method="GET">
    <input type="submit" value="Go to /updatePet">
</form>

</body>
</html>