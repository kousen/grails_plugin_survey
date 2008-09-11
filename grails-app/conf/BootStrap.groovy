import org.jsecurity.crypto.hash.Sha1Hash

class BootStrap {

     def init = { servletContext ->
        if (!JsecRole.findByName('Administrator')) {
          // Administrator user and role.
          def adminRole = new JsecRole(name: "Administrator").save()
          def adminUser = new JsecUser(username: "admin",
              passwordHash: new Sha1Hash("p@ssw0rd").toHex()).save()
          new JsecUserRoleRel(user: adminUser, role: adminRole).save()
        }
        
        if (!JsecRole.findByName('User')) {
					def normalRole = new JsecRole(name: "User").save()
					def normalUser = new JsecUser(username:"grails",
							passwordHash: new Sha1Hash("groovy").toHex()).save()
					new JsecUserRoleRel(user: normalUser, role: normalRole).save()
        }
        
//        if (!Plugin.findByName('test')) {
//        	new Plugin(name:'test',ver:'0.1',description:'test plugin',
//        			rating:3,totalVotes:2).save()
//        }
     }
     
     def destroy = {
     }
} 