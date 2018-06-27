package top.xhbeta.demo.hibernate.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.xhbeta.demo.hibernate.domain.bid.manytomany.Employee;
import top.xhbeta.demo.hibernate.domain.bid.manytomany.Project;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BidManyToManyTest {

  @Autowired
  private TestEntityManager em;

  @Test
  public void givenData_whenInsert_thenCreatesMtoMrelationship() {
    String[] employeeData = { "Peter Oven", "Allan Norman" };
    String[] projectData = { "IT Project", "Networking Project" };
    Set<Project> projects = new HashSet<>();

    for (String proj : projectData) {
      projects.add(new Project(proj));
    }

    for (String emp : employeeData) {
      Employee employee = new Employee(emp.split(" ")[0],
        emp.split(" ")[1]);

      assertEquals(0, employee.getProjects().size());

      employee.setProjects(projects);

      em.persist(employee);

      assertNotNull(employee);
    }

    em.flush();

  }

}
