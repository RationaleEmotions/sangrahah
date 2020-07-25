package com.rationaleemotions.scenarios.three;

import com.rationaleemotions.scenarios.SimpleUnitTestCase;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class UnitTestCase extends SimpleUnitTestCase {

  @Test
  public void testWithGroupsAtTestTagLevel() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClass.class, expected, "Example_Test");
  }

  @Test
  public void testWithGroupsAtSuiteLevel() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClassWithGroupsAtSuiteLevel.class, expected, "Example_Test");
  }

  @Test(enabled = false, description = "Broken at TestNG level")
  public void testWithGroupsDefinitionAtSuiteLevel() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClassWithGroupsDefinitionSpecified.class, expected,
        "Example_Test");
  }

  @Test
  public void testWithGroupsDefinitionAtSuiteLevelAtTestTagLevel() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClassWithGroupsDefinitionSpecifiedAtTestTagLevel.class, expected,
        "Example_Test");
  }

  @Test
  public void testWithGroupDependencies() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod"
    );
    runTest("Example_Suite", SuiteClassWithGroupDependencies.class, expected,
        "Example_Test");
  }

}
