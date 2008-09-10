class CommentTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testCommentListNewDelete()
        // add tests for more operations here
    }
    
    def testCommentListNewDelete() {
        webtest('Comment basic operations: view list, create new entry, view, edit, delete, view') {
            group(description:'create a plugin destination for the comment') {
                invoke      'plugin'
                verifyText  'Home'

                clickLink   'New Plugin'
                verifyText  'Create Plugin'

                // Need to populate fields that satisfy validation
                setInputField(name: "name", value: "test_plugin")
                setInputField(name: "ver", value: "0.1")
                setInputField(name: "description", value: "test plugin")
                setInputField(name: "rating", value: "1")
                setInputField(name: "link", value: "http://grails.org/Plugins/Test+Plugin")
                setInputField(name: "totalVotes", value: "2")

                clickButton 'Create'
                verifyText  'Show Plugin', description:'Detail page'
            }

            group(description:'add a comment to the test plugin') {
                clickButton "Edit"
                clickLink "Add Comment"
                setInputField(name: "contributor", value: "contrib")
                setInputField(name: "comment", value: "test_comment")
                clickButton "Create"
                verifyTitle "Show Comment"
            }
            
            
            group(description:'delete the only element') {
                clickLink "Home"
                clickLink "PluginController"
                clickLink "1"
                clickButton "Delete"
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify Comment list view with $size row(s)") {
            verifyText  'List'
            verifyXPath xpath:      ROW_COUNT_XPATH,
            text:       size,
            description:"$size row(s) of data expected"
        }
    }

    def showFirstElementDetails() {
        ant.clickLink   '1', description:'go to detail view'
    }
}