

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Plugin List</title>
    </head>
    <body>
        <div class="body">
            <h1>Plugin List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="ver" title="Ver" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="rating" title="Rating" />
                        
                   	        <g:sortableColumn property="link" title="Plugin Page" />
                   	        <th>Comments</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${pluginList}" status="i" var="plugin">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${plugin.id}">${fieldValue(bean:plugin, field:'name')}</g:link></td>
                        
                            <td>${fieldValue(bean:plugin, field:'ver')}</td>
                        
                            <td>${fieldValue(bean:plugin, field:'description')}</td>
                        
                            <td>${fieldValue(bean:plugin, field:'rating')}</td>
                        
                            <td><a href="${plugin.link}">${fieldValue(bean:plugin, field:'link')}</a></td>
                            <td><g:link action="show" id="${plugin.id}">${plugin?.comments ? plugin.comments.size() : 0}</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Plugin.count()}" />
            </div>
        </div>
    </body>
</html>
