package top.xhbeta.demo.hibernate.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import top.xhbeta.demo.hibernate.domain.bid.onetomany.Post;
import top.xhbeta.demo.hibernate.domain.bid.onetomany.PostComment;
import top.xhbeta.demo.hibernate.domain.uni.onetomany.Desktop;
import top.xhbeta.demo.hibernate.domain.uni.onetomany.Thing;
import top.xhbeta.demo.hibernate.domain.uni.onetomany.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSave() {

      Thing phone = new Thing("Phone", "0.01");
      Thing cup = new Thing("cup", "0.03");

      Desktop home = new Desktop("Home");
      home.addThing(phone).addThing(cup);

      Thing monitor = new Thing("Monitor", "0.38");
      Thing paper = new Thing("Paper", "0.8");

      Desktop work = new Desktop("Work");
      work.addThing(monitor).addThing(paper);

      User user = new User("Bob", "2018-06-06");
      user.addDesktop(home).addDesktop(work);

      user = userRepository.saveAndFlush(user);

      user.getDesktops().remove(0);

      user = userRepository.saveAndFlush(user);

    }

  @Test
  public void testMergeObjectToNull() {
    User bob = new User("Bob", "2018-06-06");
    bob.addDesktop(new Desktop("Home"));
    bob.addDesktop(new Desktop("Work"));

    bob = userRepository.saveAndFlush(bob);

    // Now we have a User which have two Desktops
    assertEquals(2, bob.getDesktops().size());
    Long ids1 = bob.getDesktops().get(0).getId();
    Long ids2 = bob.getDesktops().get(1).getId();

    // Now we create a detached bob
    User bobDetached = new User();
    bobDetached.setId(bob.getId());

    Desktop home = new Desktop();
    home.setId(ids1);

    Desktop work = new Desktop();
    work.setId(ids2);

    bobDetached.addDesktop(home).addDesktop(work);

    bobDetached = userRepository.saveAndFlush(bobDetached);

    // Now both User have same Desktops
    assertEquals(bobDetached.getDesktops().get(0), bob.getDesktops().get(0));
    assertEquals(bobDetached.getDesktops().get(1), bob.getDesktops().get(1));

    assertNull(bobDetached.getName());
    assertNull(bob.getName());

  }

  @Test
  public void testMergeObjectRefrenceAnotherEntityId() {
    User bob = new User("Bob", "2018-06-06");
    bob.addDesktop(new Desktop("Home"));
    bob.addDesktop(new Desktop("Work"));

    bob = userRepository.saveAndFlush(bob);

    // Now we have a User which have two Desktops
    assertEquals(2, bob.getDesktops().size());
    Long ids1 = bob.getDesktops().get(0).getId();
    Long ids2 = bob.getDesktops().get(1).getId();

    // Now we save a new User that have same reference desktops
    User ali = new User("Ali", "2012-09-09");
    Desktop home = new Desktop();
    home.setId(ids1);
    home = entityManager.find(Desktop.class, home.getId());

    Desktop work = new Desktop();
    work.setId(ids2);
    work = entityManager.find(Desktop.class, work.getId());

    ali.addDesktop(home).addDesktop(work);

    ali = userRepository.saveAndFlush(ali);

    // Now both User have same Desktops
    assertEquals(ali.getDesktops().get(0), bob.getDesktops().get(0));
    assertEquals(ali.getDesktops().get(1), bob.getDesktops().get(1));
  }

    @Test
    public void testEnvSetted() {
        assertNotNull(jdbcTemplate);
        assertNotNull(userRepository);
        assertNotNull(entityManager);

        int rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "user");
        assertEquals(0, rowCount);
    }
}
