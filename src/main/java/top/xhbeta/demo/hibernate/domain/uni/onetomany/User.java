package top.xhbeta.demo.hibernate.domain.uni.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String name;

  @Column
  String birthDate;


  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  @JoinColumn(name = "user_id")
  private List<Desktop> desktops = new ArrayList<>();

  public User() {
  }

  public User(String name, String birthDate) {
    this.name = name;
    this.birthDate = birthDate;
  }

  public User addDesktop(Desktop desktop) {
    this.desktops.add(desktop);
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

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public List<Desktop> getDesktops() {
    return desktops;
  }

  public void setDesktops(List<Desktop> desktops) {
    this.desktops = desktops;
  }
}
