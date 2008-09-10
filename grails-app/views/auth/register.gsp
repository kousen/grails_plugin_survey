<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <g:javascript library="prototype" />
  <title>Register</title>
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <h1>Please Register Here</h1>
  <g:form action="register">
    <table>
      <tbody>
        <tr>
          <td><label for="username">Username:</label></td>
          <td><g:textField name="username" value="${username}" /></td>
          <td><g:submitToRemote url="[action: 'isAvailable']" update="available" value="Available?"/> <span id="available"></span></td>
        </tr>
        <tr>
          <td><label for="password">Password:</label></td>
          <td><g:passwordField name="password" /></td>
          <td></td>
        </tr>
        <tr>
          <td><label for="confirmPassword">Password Confirmation:</label></td>
          <td><g:passwordField name="confirmPassword" /></td>
          <td></td>
        </tr>
        <tr>
          <td><g:submitButton name="register" value="Register" /></td>
          <td></td>
          <td></td>
        </tr>
      </tbody>
    </table>
  </g:form>
</body>
</html>
