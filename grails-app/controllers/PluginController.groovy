import org.jsecurity.SecurityUtils

class PluginController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ pluginList: Plugin.list( params ) ]
    }

    def addComment = {
        def plugin = Plugin.get( params.id )
        def comment = "${params.comment} (by ${SecurityUtils.subject.principal})"
        plugin.addToComments(comment: comment).save()

        render(template:'comments', model:[comments:plugin.comments])
    }

    def show = {
        def plugin = Plugin.get( params.id )

        if(!plugin) {
            flash.message = "Plugin not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ plugin : plugin ] }
    }

    def delete = {
        def plugin = Plugin.get( params.id )
        if(plugin) {
            plugin.delete()
            flash.message = "Plugin ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Plugin not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def plugin = Plugin.get( params.id )

        if(!plugin) {
            flash.message = "Plugin not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ plugin : plugin ]
        }
    }

    def update = {
        def plugin = Plugin.get( params.id )
        if(plugin) {
            plugin.properties = params
            if(!plugin.hasErrors() && plugin.save()) {
                flash.message = "Plugin ${plugin.name} updated"
                redirect(action:show,id:plugin.id)
            }
            else {
                render(view:'edit',model:[plugin:plugin])
            }
        }
        else {
            flash.message = "Plugin not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def plugin = new Plugin()
        plugin.properties = params
        return ['plugin':plugin]
    }

    def save = {
        def plugin = new Plugin(params)
        if(!plugin.hasErrors() && plugin.save()) {
            flash.message = "Plugin ${plugin.id} created"
            redirect(action:show,id:plugin.id)
        }
        else {
            render(view:'create',model:[plugin:plugin])
        }
    }
}
