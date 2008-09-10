class PluginTest extends grails.util.WebTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testPluginListNewDelete()
        // add tests for more operations here
    }

    def testPluginListNewDelete() {
        webtest('Plugin basic operations: view list, create new entry, view, edit, delete, view') {
            
            invoke      'plugin'
            verifyText  'Home'

            verifyListSize 0

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
            clickLink   'List', description:'Back to list view'

            verifyListSize 1

            group(description:'edit the one element') {
                showFirstElementDetails()
                clickButton 'Edit'
                verifyText  'Edit Plugin'
                clickButton 'Update'
                verifyText  'Show Plugin'
                clickLink   'List', description:'Back to list view'
            }

            verifyListSize 1

            group(description:'delete the only element') {
                showFirstElementDetails()
                clickButton 'Delete'
                verifyXPath xpath:  "//div[@class='message']",
                            text:   /.*Plugin.*deleted.*/,
                            regex:  true
            }

            verifyListSize 0
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def verifyListSize(int size) {
        ant.group(description:"verify Plugin list view with $size row(s)") {
            verifyText  'Plugin List'
            verifyXPath xpath:      ROW_COUNT_XPATH,
                        text:       size,
                        description:"$size row(s) of data expected"
        }
    }

    def showFirstElementDetails() {
        ant.clickLink   '2', description:'go to detail view'
    }
}