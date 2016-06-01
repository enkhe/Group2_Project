package testView;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthorUITest.class, ManagementSystemTest.class,
		ProgramChairUITest.class, ReviewerUITest.class,
		SubProgramChairUITest.class })
public class AllViewTests {

}
