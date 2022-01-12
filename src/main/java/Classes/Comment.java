package Classes;

public class Comment {
    private int commentID;
    private final int offerID;
    private final String commenterName;
    private String commentText;

    public Comment(int commentID, int offerID, String commenterName, String commentText) {
        this.commentID = commentID;
        this.offerID = offerID;
        this.commenterName = commenterName;
        this.commentText = commentText;
    }

    public Comment(int offerID, String commenterName, String commentText) {
        this.offerID = offerID;
        this.commenterName = commenterName;
        this.commentText = commentText;
    }

    public int getCommentID() {
        return commentID;
    }

    public int getOfferID() {
        return offerID;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
