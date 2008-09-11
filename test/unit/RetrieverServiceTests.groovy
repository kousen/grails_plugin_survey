class RetrieverServiceTests extends GroovyTestCase {
    def retrieverService = new RetrieverService()
    def acegi = new Plugin(name:'acegi',ver:'0.3',
        description:'Grails Spring Security 2.0 Plugin')

	
    void testRetrievePlugins() {
        def entries = retrieverService.remoteList
        assertTrue(entries.size() > 0)
    }

    void testPluginLinks() {
        def link = retrieverService.getLink(acegi)
        assertEquals('http://grails.org/AcegiSecurity+Plugin',link)
    }
}
