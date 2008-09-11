<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <g:javascript library="prototype" />
  <title>Register</title>
</head>
<body>
	<div class="body">
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <h1>Please Register Here</h1>
  <g:form action="register">
  	<div class="dialog">
    <table>
      <tbody>
        <tr class="prop">
          <td valign="top" class="name"><label for="username">Username:</label></td>
          <td valign="top" class="value"><g:textField name="username" value="${username}" /></td>
          <td><g:submitToRemote url="[action: 'isAvailable']" update="answer" value="Available?"/> <span id="answer"></span></td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name"><label for="password">Password:</label></td>
          <td valign="top" class="value"><g:passwordField name="password" /></td>
          <td></td>
        </tr>
        <tr class="prop">
          <td valign="top" class="name"><label for="confirmPassword">Password Confirmation:</label></td>
          <td valign="top" class="value"><g:passwordField name="confirmPassword" /></td>
          <td></td>
        </tr>
        <tr class="prop">
        	<td valign="top" class="name">Read this: <jcaptcha:jpeg name="imageCaptcha" height="20px" width="100px" /></td>
        	<td valign="top" class="value"><g:textField name="captcha" value="" /></td>
        	<td></td>
        </tr>
      </tbody>
    </table>
    </div>
    <div class="buttons">
    	<span class="button"><g:submitButton name="register" class="save" value="Register" /></span>
    </div>
  </g:form>
  </div>
</body>
</html>
