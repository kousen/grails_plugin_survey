class @artifact.name @{
    def cronExpression = "0 0 5 * * ?"
    def retrieverService

    def execute() {
        println "Updating started at " + new Date()
        retrieverService.updateSavedList()
        println "Updating finished at " + new Date()
    }
}
