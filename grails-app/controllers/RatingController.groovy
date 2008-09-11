class RatingController {

    def rate = {
    	def rating = params.rating
    	def plugin = Plugin.get( params.id )
    	def average = (rating.toDouble() + 
            plugin.rating*plugin.totalVotes)/(plugin.totalVotes + 1)
    	plugin.rating = average
    	plugin.totalVotes += 1
    	plugin.save()
      session.voted[plugin.name] = true
    	render(template: "/plugin/rate", model: [plugin: plugin, rating: average])
    }
}
