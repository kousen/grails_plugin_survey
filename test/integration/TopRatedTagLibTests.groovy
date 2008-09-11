class TopRatedTagLibTests extends GroovyTestCase {

    void testTopRated() {
        def lib = new TopRatedTagLib()
        def table = lib.topRated()
        def doc = new XmlSlurper().parseText(table)
        assertEquals 10, doc.tr.size()

        def tds = doc.tr.td
        def ratings = []
        tds.eachWithIndex { value, i ->
            if (i % 2 == 0) ratings << value.toInteger()
        }
        def last = ratings[0]
        ratings[1..-1].each { r ->
            assertTrue(r <= last)
            last = r
        }
    }

    void testTopVoted() {
        def lib = new TopRatedTagLib()
        def table = lib.topVoted()
        def doc = new XmlSlurper().parseText(table)
        assertEquals 10, doc.tr.size()

        def tds = doc.tr.td
        def votes = []
        tds.eachWithIndex { value, i ->
            if (i % 2 == 0) votes << value.toInteger()
        }
        def last = votes[0]
        votes[1..-1].each { v ->
            assertTrue(v <= last)
            last = v
        }
    }
}
