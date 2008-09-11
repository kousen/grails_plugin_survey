class PluginControllerTests extends GroovyTestCase {

    def pc
    def p
    
    void setUp() {
        pc = new PluginController()
        p = new Plugin(name:'test',ver:'0.1',
            description:'Test Plugin',
            link:'http://grails.org/Test+Plugin',
            rating:3,numVotes:2).save()
    }
    
    void tearDown() {
    		p?.delete()
    }
    
    void testNoVotedArrayBeforeIntercepting() {
        assertNull pc.session?.voted
    }
    
    void testVotedArrayExistsAfterIntercepting() {
        pc.beforeInterceptor()
        assertNotNull pc.session
        assertNotNull pc.session.voted
    }

    void testVotedArrayHasTestPlugin() {
        pc.beforeInterceptor()
        assertFalse pc.session.voted[p.name]
    }

    void tearDown() {
        pc = null
    }
}
