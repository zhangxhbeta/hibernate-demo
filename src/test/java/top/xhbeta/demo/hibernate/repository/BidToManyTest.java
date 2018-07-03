package top.xhbeta.demo.hibernate.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.xhbeta.demo.hibernate.domain.bid.onetomany.Post;
import top.xhbeta.demo.hibernate.domain.bid.onetomany.PostComment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BidToManyTest {
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testSaveBid() {
    Post post = new Post("First post");

    post.addComment(
      new PostComment("My first review")
    );
    post.addComment(
      new PostComment("My second review")
    );
    post.addComment(
      new PostComment("My third review")
    );

    post.addComment(
      new PostComment("1")
    );

    post = entityManager.persistAndFlush(post);
    entityManager.clear();

    Post findedPost = entityManager.find(Post.class, post.getId());

    // Verify the PostComment's id is not null
    assertEquals(3, findedPost.getComments().size());

    PostComment firstComment = findedPost.getComments().get(0);
    assertNotNull(firstComment.getPost());

    // Will read from cache
    Long id = firstComment.getId();
    PostComment findFirstComment = entityManager.find(PostComment.class, id);

    assertEquals(findFirstComment, firstComment);
    assertNotNull(findFirstComment.getPost());
    assertSame(firstComment, findFirstComment);
  }
}
