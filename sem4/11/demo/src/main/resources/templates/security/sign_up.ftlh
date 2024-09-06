<!doctype html>
<html lang="en">
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="form-style-3">
    <form method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <fieldset>
            <legend>Sign Up</legend>

            <label for="firstName">
                <span>First Name<span class="required">*</span></span>
                <input type="text" class="input-field" name="firstName" value=""/>
            </label>

            <label for="lastName">
                <span>Last Name <span class="required">*</span></span>
                <input type="text" class="input-field" name="lastName" value=""/>
            </label>

            <label for="email">
                <span>Email <span class="required">*</span></span>
                <input type="email" class="input-field" name="email" value=""/>
            </label>

            <label for="password">
                <span>Password<span class="required">*</span></span>
                <input type="password" class="input-field" name="password" value=""/>
            </label>
        </fieldset>

        <fieldset>
            <label><span> </span><input type="submit" value="Submit"/></label>
        </fieldset>
    </form>
</div>

</body>
</html>