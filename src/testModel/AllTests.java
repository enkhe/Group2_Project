package testModel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testView.AllViewTests;

@RunWith(Suite.class)
@SuiteClasses({ AllModelTests.class, AllViewTests.class })
public class AllTests {

}
