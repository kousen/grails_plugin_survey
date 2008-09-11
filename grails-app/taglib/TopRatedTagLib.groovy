import java.text.NumberFormat

class TopRatedTagLib {
    static namespace = "top"

    def topRated = {
        def plugins = Plugin.listOrderByRating(max:5,order:'desc')

        out << render(template:"/plugin/top_rated", model:["plugins":plugins])
    }

    def topVoted = {
        def plugins = Plugin.listOrderByTotalVotes(max:5,order:'desc')

        out << render(template:"/plugin/top_voted", model:["plugins":plugins])
    }
}
