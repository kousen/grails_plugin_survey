class PluginTests extends GroovyTestCase {

    def p
    
    void setUp() {
        p = new Plugin(name:'test',ver:'0.1',
            description:'Test Plugin',
            link:'http://grails.org/Test+Plugin',
            rating:3,numVotes:2).save()
    }

    void testValidPlugin() {
        assertTrue(p.validate())
    }

    void testBlankName() {
        p.name = ""
        assertFalse(p.validate())
    }

    void testBadVersion() {
        p.ver = 'not yet released'
        assertFalse(p.validate())
    }

    void testBadRatings() {
        p.rating = 6
        assertFalse(p.validate())

        p.rating = -1
        assertFalse(p.validate())
    }

    void testNegativeTotalVotes() {
        p.totalVotes = -1
        assertFalse(p.validate())
    }
    
    void tearDown() {
        p?.delete()
    }
    
    void testToString() {
        def s = "test (0.1) Test Plugin"
        assertToString(p,s)
    }
}
