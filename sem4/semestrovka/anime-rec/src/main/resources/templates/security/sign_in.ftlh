<!doctype html>
<html lang="en">
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="/static/css/security/security.css">
</head>
<body>
<div class="form-style-3">
    <form method="post" action="/sign-in">
        <fieldset>
            <legend>ВХОД</legend>

            <#if error??>
                <h1>Некорректный логин или пароль</h1>
            </#if>

            <label for="username">
                <span>ЛОГИН<span class="required">*</span></span>
                <input type="text" class="input-field" name="username" value=""/>
            </label>

            <label for="password">
                <span>ПАРОЛЬ<span class="required">*</span></span>
                <input type="password" class="input-field" name="password" value=""/>
            </label>

            <label for="remember-me">
                <input type="checkbox" name="remember-me"/>ЗАПОМНИТЬ МЕНЯ
            </label>

        </fieldset>

        <fieldset>
            <label>
                <span> </span>
                <input type="submit" value="ВХОД"/>
            </label>
        </fieldset>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    </form>
</div>
</body>
</html>