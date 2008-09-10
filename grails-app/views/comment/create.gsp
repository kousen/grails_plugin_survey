

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Comment</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Comment List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Comment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${comment}">
            <div class="errors">
                <g:renderErrors bean="${comment}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contributor">Contributor:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:comment,field:'contributor','errors')}">
                                    <input type="text" id="contributor" name="contributor" value="${fieldValue(bean:comment,field:'contributor')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment">Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:comment,field:'comment','errors')}">
                                    <input type="text" id="comment" name="comment" value="${fieldValue(bean:comment,field:'comment')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="plugin">Plugin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:comment,field:'plugin','errors')}">
                                    <g:select optionKey="id" from="${Plugin.list()}" name="plugin.id" value="${comment?.plugin?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
