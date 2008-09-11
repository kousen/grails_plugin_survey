<resource:rating />
<%@ page import="org.springframework.util.ClassUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.SearchableUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.lucene.LuceneUtils" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title><g:if test="${params.q && params.q?.trim() != ''}">${params.q} - </g:if>Grails Searchable Plugin</title>
    <style type="text/css">
      * {
        font-family: Arial, sans-serif;
        padding: 0;
        margin: 0;
      }

      body {
        font-size: 0.9em;
        padding: .5em;
      }

      .title {
        margin: 1em 0;
        padding: .3em .5em;
        text-align: right;
        background-color: seashell;
        border-top: 1px solid lightblue;
      }

      .result {
        margin-bottom: 1em;
      }

      .result .displayLink {
        color: green;
      }

      .result .name {
        font-size: larger;
      }

      .paging a.step {
        padding: 0 .3em;
      }

      .paging span.currentStep {
        font-weight: bold;
      }

      ul {
        margin: 1em 2em;
      }

      li, p {
        margin-bottom: 1em;
      }
    </style>
    <script type="text/javascript">
        var focusQueryInput = function() {
            document.getElementById("q").focus();
        }
    </script>
  </head>
  <body onload="focusQueryInput();">
    <div id="header">
      <h1>Search for plugins</h1>
      <h3>(<a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene syntax</a>
        or see the <a href="http://grails.org/Searchable+Plugin">Searchable plugin page</a>)
        <g:form url='[controller: "searchable", action: "index"]' id="searchableForm" name="searchableForm" method="get">
        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
        </g:form>
      </h3>
    </div>
    <div style="clear: both; display: none;" class="hint">See <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for advanced queries</div>
    </div>
    <div id="main">
      <p>Sample queries: acegi, a*, a*i, security, etc.</p>
      <g:set var="haveQuery" value="${params.q?.trim()}" />
      <g:set var="haveResults" value="${searchResult?.results}" />
      <div class="title">
        <span style="float: left;">
          <g:link class="list" controller="plugin" action="list">Complete list of plugins</g:link>
        </span>
        <span>
          <g:if test="${haveQuery && haveResults}">
          Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
          results for <strong>${params.q}</strong>
          </g:if>
          <g:else>
          &nbsp;
          </g:else>
        </span>
      </div>

      <g:if test="${parseException}">
      <p>Your query - <strong>${params.q}</strong> - is not valid.</p>
      <p>Suggestions:</p>
      <ul>
        <li>Fix the query: see <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for examples</li>
        <g:if test="${LuceneUtils.queryHasSpecialCharacters(params.q)}">
        <li>Remove special characters like <strong>" - [ ]</strong>, before searching, eg, <em><strong>${LuceneUtils.cleanQuery(params.q)}</strong></em><br />
          <em>Use the Searchable Plugin's <strong>LuceneUtils#cleanQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.cleanQuery(params.q)]">Search again with special characters removed</g:link></em>
        </li>
        <li>Escape special characters like <strong>" - [ ]</strong> with <strong>\</strong>, eg, <em><strong>${LuceneUtils.escapeQuery(params.q)}</strong></em><br />
          <em>Use the Searchable Plugin's <strong>LuceneUtils#escapeQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.escapeQuery(params.q)]">Search again with special characters escaped</g:link></em><br />
          <em>Or use the Searchable Plugin's <strong>escape</strong> option: <em><g:link controller="searchable" action="index" params="[q: params.q, escape: true]">Search again with the <strong>escape</strong> option enabled</g:link></em>
        </li>
        </g:if>
      </ul>
      </g:if>
      <g:elseif test="${haveQuery && !haveResults}">
      <p>Nothing matched your query - <strong>${params.q}</strong></p>
      </g:elseif>
      <g:elseif test="${haveResults}">
      <div class="results">
        <g:each var="plugin" in="${searchResult.results}" status="index">
        <div class="result">
          <g:set var="link" value="${createLink(controller: 'plugin', action: 'show', id: plugin.id)}" />
          <div class="name"><a href="${link}">${plugin.name} (version ${plugin.ver})</a></div>
          <g:set var="desc" value="${plugin.description}" />
          <g:if test="${desc?.size() > 120}"><g:set var="desc" value="${desc[0..120] + '...'}" /></g:if>
          <div class="desc">${desc?.encodeAsHTML()}</div>
          <div><g:render template="/plugin/rate"
          model='[plugin: plugin, rating: "${plugin.rating}"]' /></div>
        </div>
        </g:each>
      </div>

      <div>
        <div class="paging">
          <g:if test="${haveResults}">
          Page:
          <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max)}" />
          <g:if test="${totalPages == 1}"><span class="currentStep">1</span></g:if>
          <g:else><g:paginate controller="searchable" action="index" params="[q: params.q]"
            total="${searchResult.total}" prev="&lt; previous" next="next &gt;"/></g:else>
          </g:if>
        </div>
      </div>
      </g:elseif>
    </div>
  </body>
</html>