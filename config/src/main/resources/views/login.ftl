<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Security Example </title>
</head>
<body>
<#if RequestParameters["error"]??>
<div>
    Invalid username and password.
</div>
</#if>
<#if RequestParameters["logout"]??>
<div>
    You have been logged out.
</div>
</#if>
<#if RequestParameters["invalid"]??>
<div>
    You have been invalid.
</div>
</#if>
<form action="${springMacroRequestContext.contextPath}/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>