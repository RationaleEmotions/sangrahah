package com.rationaleemotions.scenarios.two;

import com.rationaleemotions.scenarios.SimpleUnitTestCase;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class UnitTestCase extends SimpleUnitTestCase {

  @Test
  public void testWithOneTestTag() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClass.class, expected, "Example_Test");
  }

  @Test
  public void testWithTwoTestTags() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClassWithTwoTestTags.class, expected, "Example_Test_One",
        "Example_Test_Two");
  }
}
