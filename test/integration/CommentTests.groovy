class CommentTests extends GroovyTestCase {

    def c
    def p
    
    void setUp() {
        p = new Plugin(name:'test_plugin',ver:'0.1',
            description:'Test Plugin',
            link:'http://grails.org/Test+Plugin',
            rating:3,numVotes:2)

        c = new Comment(contributor:'contrib',comment:'test_comment')
        p.addToComments(c)
        p.save()
    }

    void testToString() {
        def s = "test_comment by contrib"
        assertToString(c,s)
    }

    void testPluginToComment() {
        def plugin = Plugin.findByName('test_plugin')
        assertNotNull(plugin)

        def comments = plugin.comments
        assertNotNull(comments)
        assertEquals(1, comments.size())
        assertTrue(comments.contains(c))
    }

    void testCommentToPlugin() {
        def comment = Comment.findByComment('test_comment')
        assertNotNull(comment)

        def plugin = comment.plugin
        assertEquals(plugin,p)
    }

    void testValidComment() {
        assertTrue(c.validate())
    }

    void testEmptyContributor() {
        c.contributor = ''
        assertFalse(c.validate())
    }

    void testEmptyComment() {
        c.comment = ''
        assertFalse(c.validate())
    }

    void testCascadeDelete() {
        assertNotNull(p)
        assertNotNull(p.comments)
        p.delete(flush:true)
        assertNull(Plugin.findByName('test_plugin'))
        assertNull(Comment.findByComment('test_comment'))
    }

    void tearDown() {
        p?.delete()
    }
}
