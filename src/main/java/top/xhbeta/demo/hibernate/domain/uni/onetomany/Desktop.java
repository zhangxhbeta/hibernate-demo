package top.xhbeta.demo.hibernate.domain.uni.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Desktop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String name;

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @JoinColumn(name = "desktop_id")
  private List<Thing> things = new ArrayList<>();

  public Desktop() {
  }

  public Desktop(String name) {
    this.name = name;
  }

  public Desktop addThing(Thing thing) {
    this.things.add(thing);
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
