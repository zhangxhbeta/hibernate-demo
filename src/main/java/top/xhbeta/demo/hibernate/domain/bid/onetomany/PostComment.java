package top.xhbeta.demo.hibernate.domain.bid.onetomany;

import javax.persistence.*;

@Entity
public class PostComment {

  @Id
  @GeneratedValue
  private Long id;

  private String review;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  public PostComment(String review) {
    this.review = review;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PostComment )) return false;
    return id != null && id.equals(((PostComment) o).id);
  }
  @Override
  public int hashCode() {
    return 31;
  }
}
