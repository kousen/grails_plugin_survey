class RatingControllerTests extends GroovyTestCase {

	def ratingController
	def plugin
	
	void setUp() {
		// create the test plugin and rating controller
		ratingController = new RatingController()
		plugin = new Plugin(name:'test',ver:'0.1',
			description:'test plugin',rating:2,totalVotes:3)
		plugin.save()
		
		// set up session variables
		ratingController.session.voted = [:]
		ratingController.session.voted['test'] = false
	}
	
    void testRate() {
    	// input params to find the plugin and new rating
    	ratingController.params.id = plugin.id
    	ratingController.params.rating = '4'
    	
    	ratingController.rate()
    	
    	assertTrue ratingController.session.voted[plugin.name]
    	// new rating = (4 + 2*3)/4 = 2.5
    	assertEquals 2.5, plugin.rating
    }
    
    void tearDown() {
    	ratingController = null
    	plugin.delete()
    }
}
