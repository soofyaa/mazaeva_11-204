<!doctype html>
<html lang="en">
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/static/css/security/security.css">
    <script src="/static/js/email-validation.js" defer></script>

</head>
<body>
<div class="form-style-3">
    <form method="post" onsubmit="return validateForm()">
        <fieldset>
            <legend>РЕГИСТРАЦИЯ</legend>

            <#if error??>
                <h1>Некорректное имя пользователя или почта</h1>
            </#if>

            <label for="username">
                <span>ИМЯ<span class="required">*</span></span>
                <input type="text" class="input-field" name="username" value=""/>
            </label>

            <label for="email">
                <span>ПОЧТА<span class="required">*</span></span>
                <input type="email" class="input-field" name="email" id="email" value=""/>
                <span id="emailError" style="color: red; display: none;">Неверный формат email</span>
            </label>

            <label for="password">
                <span>ПАРОЛЬ<span class="required">*</span></span>
                <input type="password" class="input-field" name="password" value=""/>
            </label>
        </fieldset>

        <fieldset>
            <label><span> </span><input type="submit" value="РЕГИСТРАЦИЯ"/></label>
        </fieldset>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    </form>
</div>

</body>
</html>
