<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>

<h2>Registration Form</h2>
<#if errors??>
    <div>
        <ul>
            <#list errors as error>
                <li>${error.defaultMessage}</li>
            </#list>
        </ul>
    </div>
</#if>

<form action="/register" method="post">

    <label for="name">Name:</label> <br>
    <input id="name" type="text" name="name" value="${user.name!}"/>
    <br><br>

    <label for="age">Age:</label><br>
    <input id="age" type="text" name="age" value="${(user.age!)}"/>
    <br><br>

    <label for="email">Email:</label><br>
    <input id="email" type="email" name="email" value="${user.email!}"/>
    <br><br>

    <label for="phoneNumber">Phone Number:</label><br>
    <input id="phoneNumber" type="text" name="phoneNumber" value="${user.phoneNumber!}" placeholder="+7-XXX-XXX-XX-XX"/>
    <br><br>

    <label for="password">Password:</label><br>
    <input id="password" type="password" name="password" />
    <br><br>

    <label for="confirmPassword">Confirm Password:</label><br>
    <input id="confirmPassword" type="password" name="confirmPassword" />
    <br><br>

    <input type="checkbox" id="terms" name="terms" />
    <label for="terms">I agree with the terms</label>
    <br><br>

    <button type="submit" class="btn">Register</button>
</form>

</body>
</html>
