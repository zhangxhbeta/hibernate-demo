package top.xhbeta.demo.hibernate.domain.bid.onetomany;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @OneToMany(
    mappedBy = "post",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @Where(clause = "review != '1'")
  private List<PostComment> comments = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<PostComment> getComments() {
    return comments;
  }

  public void setComments(List<PostComment> comments) {
    this.comments = comments;
  }

  public Post(String title) {
    this.title = title;
  }

  public Post() {
  }

  public Post addComment(PostComment comment) {
    this.comments.add(comment);
    comment.setPost(this);
    return this;
  }

  public Post removeComment(PostComment comment) {
    comments.remove(comment);
    comment.setPost(null);
    return this;
  }
}
