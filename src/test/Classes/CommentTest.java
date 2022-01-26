package Classes;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CommentTest extends TestCase {

    public void testGetCommentID() {
        Comment cmt = new Comment(3, 33, "burak", "ale burak");
        Assert.assertEquals(3, cmt.getCommentID());
    }

    public void testGetOfferID() {
        Comment cmt = new Comment(3, 33, "burak", "ale burak");
        Assert.assertEquals(33, cmt.getOfferID());
    }

    public void testGetCommenterName() {
        Comment cmt = new Comment(3, 33, "burak", "ale burak");
        Assert.assertEquals("burak", cmt.getCommenterName());
    }

    public void testGetCommentText() {
        Comment cmt = new Comment(3, 33, "burak", "ale burak");
        Assert.assertEquals("ale burak", cmt.getCommentText());
    }
}