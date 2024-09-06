<!doctype html>
<html lang="en">
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="form-style-3">
    <form method="post" action="/sign-in">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>
            <legend>Sign In</legend>
            <#if error??>
                <h1>Incorrect email or password</h1>
            </#if>
            <label for="email">
                <span>Email <span class="required">*</span></span>
                <input type="email" class="input-field" name="email" value=""/>
            </label>
            <label for="password">
                <span>Password<span class="required">*</span></span>
                <input type="password" class="input-field" name="password" value=""/>
            </label>
            <label for="remember-me">
                <input type="checkbox" name="remember-me"/> Remember Me
            </label>
        </fieldset>
        <fieldset>
            <label>
                <span> </span>
                <input type="submit" value="Submit"/>
            </label>
        </fieldset>
    </form>

</div>
</body>
</html>