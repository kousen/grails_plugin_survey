class SecurityFilters {
    def filters = {

        // Creating, modifying, or deleting a plugin requires the "Administrator"
        // role.
        pluginEditing(controller: "plugin", action: "(create|edit|save|update|delete)") {
            before = {
                accessControl {
                    role("Administrator")
                }
            }
        }

        // Showing a plugin requires the "Administrator" *or* the "User" roles.
        addComment(controller: "plugin", action: "(show|addComment)") {
            before = {
                accessControl {
                    role("Administrator") || role("User")
                }
            }
        }
    }
}