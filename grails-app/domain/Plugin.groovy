class Plugin {
    String name
    String ver
    String description
    String link
    double rating
    int totalVotes
	
    static constraints = {
        name(blank:false)
        ver(matches:/^\d+(\.\d+)+.*/)
        description()
        rating(min:0.0d,max:5.0d)
        link(nullable:true,url:true)
        totalVotes(min:0)
    }

    static hasMany = [comments:Comment]
	
    String toString() { "$name ($ver) $description" }

}
