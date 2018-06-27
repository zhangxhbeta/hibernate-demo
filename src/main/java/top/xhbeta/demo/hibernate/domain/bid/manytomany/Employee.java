package top.xhbeta.demo.hibernate.domain.bid.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {

  @Id
  @GeneratedValue
  private Long id;

  String firstName;

  String lastName;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
    name = "employee_project",
    joinColumns = { @JoinColumn(name = "employee_id") },
    inverseJoinColumns = { @JoinColumn(name = "project_id") }
  )
  Set<Project> projects = new HashSet<>();

  public Employee addProject(Project project) {
    projects.add(project);
    return this;
  }

  public Employee(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Employee() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
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
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
