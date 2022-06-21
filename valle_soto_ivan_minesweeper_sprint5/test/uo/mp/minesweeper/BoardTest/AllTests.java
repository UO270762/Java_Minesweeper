package uo.mp.minesweeper.BoardTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConstructorTest.class, FlagTest.class, GetStatusTest.class, 
	StepOnTest.class, UnflagTest.class,UnveilTest.class, UncoverWelcomeAreaTest.class })
public class AllTests {

}
