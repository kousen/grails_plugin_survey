<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <title>Login</title>
</head>
<body>
	<div class="body">
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:form action="signIn">
    <input type="hidden" name="targetUri" value="${targetUri}" />
  	<div class="dialog">
    <table>
      <tbody>
        <tr class="prop">
          <td valign="top" class="name">Username:</td>
          <td valign="top" class="value"><input type="text" name="username" value="${username}" /></td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">Password:</td>
          <td valign="top" class="value"><input type="password" name="password" value="" /></td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name">Remember me?</td>
          <td valign="top" class="value"><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
        </tr>
      </tbody>
    </table>
    </div>
      <div class="buttons">
        <span class="button"><g:actionSubmit class="save" value="Sign In" /></span>
      </div>
  </g:form>
  </div>
</body>
</html>
