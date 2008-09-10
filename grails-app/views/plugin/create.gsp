

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Plugin</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Plugin List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Plugin</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${plugin}">
            <div class="errors">
                <g:renderErrors bean="${plugin}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:plugin,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ver">Ver:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'ver','errors')}">
                                    <input type="text" id="ver" name="ver" value="${fieldValue(bean:plugin,field:'ver')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:plugin,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rating">Rating:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'rating','errors')}">
                                    <input type="text" id="rating" name="rating" value="${fieldValue(bean:plugin,field:'rating')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="link">Link:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'link','errors')}">
                                    <input type="text" id="link" name="link" value="${fieldValue(bean:plugin,field:'link')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalVotes">Total Votes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:plugin,field:'totalVotes','errors')}">
                                    <input type="text" id="totalVotes" name="totalVotes" value="${fieldValue(bean:plugin,field:'totalVotes')}" />
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
