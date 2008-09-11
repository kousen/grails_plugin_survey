<table>
  <tr>
  	<th>Votes</th>
  	<th>Name</th>
  </tr>
  <g:each in="${plugins}" var="p">
  <tr>
    <td>${p.totalVotes}</td>
    <td><g:link controller="plugin" 
    	action="show" id="${p.id}">${p.name.encodeAsHTML()}</g:link></td>
  </tr>
  </g:each>
</table>