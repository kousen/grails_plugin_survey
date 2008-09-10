<ul>
   <g:each var="c" in="${comments}">
      <li>${c?.encodeAsHTML()}</li>
   </g:each>
</ul>