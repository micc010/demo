<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Hello World!</title>
</head>
<body>
<h1 inline="text">Hello world!</h1>
<form action="${springMacroRequestContext.contextPath}/logout" method="post">
    <input type="submit" value="Sign Out"/>
</form>
</body>
</html>