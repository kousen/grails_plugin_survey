class Comment {
    String contributor
    String comment

    static belongsTo = [plugin:Plugin]

    static constraints = {
        contributor(blank:false)
        comment(blank:false)
    }

    String toString() {
        "$comment by $contributor"
    }
}
