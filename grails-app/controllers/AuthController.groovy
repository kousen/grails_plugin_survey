import org.jsecurity.authc.AuthenticationException
import org.jsecurity.authc.UsernamePasswordToken
import org.jsecurity.SecurityUtils
import org.jsecurity.crypto.hash.Sha1Hash

class AuthController {
    def jsecSecurityManager

    def index = { redirect(action: 'login', params: params) }

    def login = {
        return [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri ]
    }
    
    def register = {
        if (params.username) {
            if (JsecUser.findByUsernameIlike(params.username)) {
                flash.message = "That id is already taken"
            } else if (!params.username || !params.password) {
                flash.message = "You must supply a username and password"
            } else if (!(params.password.equals(params.confirmPassword))) {
            	  flash.message = "Password and confirmation must match"
            } else {
                JsecRole userRole = JsecRole.findByName("User")
                def newUser = new JsecUser(username: params.username, passwordHash: new Sha1Hash(params.password).toHex()).save(flush: true)
                new JsecUserRoleRel(user: newUser, role: userRole).save(flush: true)

                // and log them in...
                def authToken = new UsernamePasswordToken(params.username, params.password)
                jsecSecurityManager.login(authToken)

                redirect(uri:"/plugin/index")
            }
            [ username: params.username, password: params.password ]
        }


    }

    def isAvailable = {
        println "Available for: ${params.username}"
        if (JsecUser.findByUsernameIlike(params.username)) {
            render "<span style='color: red'>No</span>"
        } else {
            render "<span style='color: green'>Yes</span>"
        }
    }

    def signIn = {
        def authToken = new UsernamePasswordToken(params.username, params.password)

        // Support for "remember me"
        if (params.rememberMe) {
            authToken.rememberMe = true
        }

        try{
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            this.jsecSecurityManager.login(authToken)

            // If a controller redirected to this page, redirect back
            // to it. Otherwise redirect to the root URI.
            def targetUri = params.targetUri ?: "/"

            log.info "Redirecting to '${targetUri}'."
            redirect(uri: targetUri)
        }
        catch (AuthenticationException ex){
            // Authentication failed, so display the appropriate message
            // on the login page.
            log.info "Authentication failure for user '${params.username}'."
            flash.message = message(code: "login.failed")

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [ username: params.username ]
            if (params.rememberMe) {
                m['rememberMe'] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m['targetUri'] = params.targetUri
            }

            // Now redirect back to the login page.
            redirect(action: 'login', params: m)
        }
    }

    def signOut = {
        // Log the user out of the application.
        SecurityUtils.subject?.logout()

        // For now, redirect back to the home page.
        redirect(uri: '/')
    }

    def unauthorized = {
        render 'You do not have permission to access this page.'
    }
}
