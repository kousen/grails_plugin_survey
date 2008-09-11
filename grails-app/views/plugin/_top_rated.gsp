<table>
	<tr>
		<th>Rating</th>
		<th>Name</th>
	</tr>
  <g:each in="${plugins}" var="p">
  <tr>
    <td><g:formatNumber number="${p.rating}" pattern="0.00"/></td>
    <td><g:link controller="plugin" 
    	action="show" id="${p.id}">${p.name.encodeAsHTML()}</g:link></td>
  </tr>
  </g:each>
</table>