import junit.framework.TestSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class GameTestSuite {

    public static TestSuite suite() {
      TestSuite suite = new TestSuite("Test for com.chuidiang.ejemplos");
      suite.addTestSuite(EntityManagerTest.class);
      suite.addTestSuite(EventManagerTest.class);
      return suite;
    }
}
