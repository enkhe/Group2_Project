package testModel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthorTest.class, ConferenceTest.class, ManuscriptTest.class,
		ProgramChairTest.class, ReviewerTest.class, ReviewTest.class,
		SubProgramChairTest.class })

public class AllModelTests {

}
