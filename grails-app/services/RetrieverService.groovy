class RetrieverService {

    boolean transactional = true

    def getRemoteList() {
        def plugins = []
    	def out = "cmd /c grails list-plugins".execute().text
    	out.split("\n").each { line ->
            if (line =~ /<(.*)>/) {
                line = line.replaceAll(/</,' ')
                def spaceSplit = line.split()
                def tokenSplit = line.split('--')
                def p = new Plugin()
                p.name = spaceSplit[0]
                p.ver = spaceSplit[1] - '>'
                p.description = tokenSplit[-1].trim()
                // p.link = getLink(p)
                if (p.ver =~ /\d+(\.\d+)+.*/) {
                    plugins << p
                }
            }
    	}
        return plugins
    }
	
    def updateSavedList() {
        def plugins = getRemoteList()
        plugins.each { p ->
            def existing = Plugin.findByName(p.name)
            if (!existing) {
            		p.link = getLink(p)
                p.save()
                println "Saved $p"
            } else if (existing.ver != p.ver) {
            		p.link = getLink(p)
                existing.ver = p.ver
                existing.description = p.description
                existing.link = p.link
                log.info("Updated ${existing.name}")
                println "Updated ${existing.name}"
            }
        }
    }

    def getLink(p) {
        def info = "cmd /c grails plugin-info ${p.name}".execute().text
        info.eachMatch(/\b(.*\+Plugin)\b/) { line ->
            p.link = line[0].split()[-1]
        }
        println ("${p.name} now linked to ${p.link}")
        return p.link
    }
}
