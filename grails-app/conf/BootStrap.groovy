class BootStrap {

     def init = { servletContext ->
        if (!JsecRole.findByName('Administrator')) {
          // Administrator user and role.
          def adminRole = new JsecRole(name: "Administrator").save()
          def adminUser = new JsecUser(username: "admin",
              passwordHash: new Sha1Hash("p@ssw0rd").toHex()).save()
          new JsecUserRoleRel(user: adminUser, role: adminRole).save()
        }
     }
     def destroy = {
     }
} 