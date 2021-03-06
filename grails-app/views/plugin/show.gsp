<resource:rating />

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <g:javascript library="prototype" />
    <title>Show Plugin</title>
  </head>
  <body>
    <div class="body">
      <h1>Show Plugin</h1>
      <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
      </g:if>
      <div class="dialog">
        <table>
          <tbody>


            <tr class="prop">
              <td valign="top" class="name">Id:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'id')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Name:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'name')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Ver:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'ver')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Description:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'description')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Rating:</td>

              <td valign="top" class="value"><g:render template="rate" model='[plugin: plugin, rating: "${plugin.rating}"]' /></td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Link:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'link')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Total Votes:</td>

              <td valign="top" class="value">${fieldValue(bean:plugin, field:'totalVotes')}</td>

            </tr>

            <tr class="prop">
              <td valign="top" class="name">Comments:</td>

              <td  valign="top" style="text-align:left;" class="value">
                <div id="comments">
                  <g:render template="comments" model="[comments:plugin?.comments]"/>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <h1>Add Comment</h1>
      <g:formRemote url="[action:'addComment']" name="addComment" update="comments">
        <g:hiddenField name="id" value="${plugin.id}" />
				Comment: <g:textField name="comment" value="" />
      </g:formRemote>
		<jsec:hasRole name="Administrator">
      <div class="buttons">
        <g:form>
        <input type="hidden" name="id" value="${plugin?.id}" />
        <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
        <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
        </g:form>
      </div>
    </jsec:hasRole>
    </div>
  </body>
</html>
