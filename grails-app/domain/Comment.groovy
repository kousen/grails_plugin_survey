class Comment {
    String comment

    static belongsTo = [plugin:Plugin]

    static constraints = {
        comment(blank:false)
    }

    String toString() { comment }
}
