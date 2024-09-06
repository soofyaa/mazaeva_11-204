<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
    <title>Ban</title>
</head>
<body>
<form class="form-style-3" action="/admin/ban-all" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <fieldset>
        <legend>Ban All Non-Admin Users</legend>
        <input type="submit" value="Click" />
    </fieldset>
</form>
</body>
</html>
