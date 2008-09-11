<div id="plugin${plugin.id}">
  <% def dynamic = !session.voted[plugin.name] %>
  <jsec:isNotLoggedIn><% dynamic = false %></jsec:isNotLoggedIn>
  <richui:rating dynamic="${dynamic.toString()}" id="${plugin.id}" units="5" 
  	rating="${rating}" updateId="plugin${plugin.id}" controller="rating" action="rate" />
  <p class="static">
    Rating ${java.text.NumberFormat.instance.format(plugin.rating)}
    based on ${plugin.totalVotes} vote<g:if test="${plugin.totalVotes != 1}">s</g:if>
  </p>
  <g:if test="${session.voted[plugin.name]}">
  	<div style="color: green;" id="vote${plugin.id}">Thanks for voting!</div>
  </g:if>
</div>