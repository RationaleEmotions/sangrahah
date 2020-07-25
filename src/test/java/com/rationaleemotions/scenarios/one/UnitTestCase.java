package com.rationaleemotions.scenarios.one;

import com.rationaleemotions.scenarios.SimpleUnitTestCase;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class UnitTestCase extends SimpleUnitTestCase {

  @Test
  public void happyFlow() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClass.class, expected, "Example_Suite_test");
  }

  @Test
  public void happyFlowWithClassDefinitions() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase3.class.getName() + ".anotherTestMethod"
    );
    runTest("Example_Suite", SuiteClassWithClassDefinitions.class, expected, "Example_Suite_test");
  }
}
