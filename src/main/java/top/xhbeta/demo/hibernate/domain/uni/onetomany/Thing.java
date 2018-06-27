package top.xhbeta.demo.hibernate.domain.uni.onetomany;

import javax.persistence.*;

@Entity
public class Thing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  String name;

  @Column
  String kg;

  public Thing() {
  }

  public Thing(String name, String kg) {
    this.name = name;
    this.kg = kg;
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

  public String getKg() {
    return kg;
  }

  public void setKg(String kg) {
    this.kg = kg;
  }
}
