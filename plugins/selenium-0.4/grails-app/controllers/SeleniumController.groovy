import groovy.xml.MarkupBuilder

class SeleniumController {
	
	def suite = {
		def baseDir = new File(servletContext.getRealPath("/selenium/tests"))

		def testList = []
		def suiteList = []
                def helper = new SeleniumHelper(baseDir)

		helper.findAllSuites(baseDir, suiteList)
		helper.findTests(params.dir ? new File(baseDir, params.dir):baseDir , testList, params.dir==null)
		
		testList = testList.sort()
		suiteList= suiteList.sort()
		
		render(view:'suite', model:[tests:testList, suites:suiteList])
	}

        /**
         * Returns a collection representing each line from the given file.
         * This is used for pipe delimited files which are converted to
         * HTML in the GSP.
         * <p>
         * Requires request parameters:
         * <ul>
         * <li>file - path to file, relative to the base directory (selenium/tests)
         * </ul>
         */
	def view = {
		def baseDir = getBaseDir(servletContext)
		def file = new File(baseDir, params.file)
                def lines = new SeleniumHelper(baseDir).fileToLines(file)
		render(view:'view', model:[name:file.name,rows:lines])
	}

        def postResults = {
            if(params.file) {
                new File(params.file).write(resultsAsXml(params))
                println "saving file"
            }
            render(view:'postResults')            
        }

        private File getBaseDir(def servletContext) {
            return new File(servletContext.getRealPath("/selenium/tests"))
        }

        private String resultsAsXml(params) {
            def writer = new StringWriter()
            def xml = new MarkupBuilder(writer)
            xml.results() {
                result(params.result)
                summary() {
                    totalTime(params.totalTime)
                    numTestPasses(params.numTestPasses)
                    numCommandPasses(params.numCommandPasses)
                    numTestFailures(params.numTestFailures)
                    numCommandFailures(params.numCommandFailures)
                    numCommandErrors(params.numCommandErrors)
                }
                suite(params.suite)
                tests() {
                    def i=0
                    while(params."testTable.${++i}") {
                        test(params."testTable.${++i}")
                    }                
                }
            }
            return writer.toString()
        }
}