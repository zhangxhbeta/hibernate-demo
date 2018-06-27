package top.xhbeta.demo.hibernate.domain.bid.manytomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {

  @Id
  @GeneratedValue
  private Long id;

  String title;

  @ManyToMany(mappedBy = "projects")
  private Set<Employee> employees = new HashSet<>();

  public Project addEmployee(Employee employee) {
    employees.add(employee);
    return this;
  }

  public Project(String title) {
    this.title = title;
  }

  public Project() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Project project = (Project) o;
    return Objects.equals(id, project.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
