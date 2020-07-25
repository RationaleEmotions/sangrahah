package com.rationaleemotions.scenarios.four;

import com.rationaleemotions.scenarios.SimpleUnitTestCase;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

public class UnitTestCase extends SimpleUnitTestCase {

  @Test
  public void testSuiteLevelParameters() {
    List<String> expected = Collections.singletonList(SampleTestCase.class.getName() + ".testMethod");
    TestListenerAdapter adapter = runTest("Example_Suite",
        SuiteClassWithSuiteLevelParameters.class, expected,
        "Example_Suite_test");
    List<Object[]> exp = Collections.singletonList(new Object[]{24});
    List<Object[]> act = adapter.getPassedTests()
        .stream()
        .map(ITestResult::getParameters)
        .collect(Collectors.toList());
    Assertions.assertThat(act).containsAll(exp);
  }

  @Test
  public void testTestTagLevelParameters() {
    List<String> expected = Collections.singletonList(SampleTestCase.class.getName() + ".testMethod");
    TestListenerAdapter adapter = runTest("Example_Suite",
        SuiteClassWithTestTagLevelParameters.class, expected,
        "Example_Test_Three");
    List<Object[]> exp = Collections.singletonList(new Object[]{24});
    List<Object[]> act = adapter.getPassedTests()
        .stream()
        .map(ITestResult::getParameters)
        .collect(Collectors.toList());
    Assertions.assertThat(act).containsAll(exp);
  }

  @Test
  public void testClassLevelParameters() {
    List<String> expected = Arrays.asList(
        SampleTestCase.class.getName() + ".testMethod",
        SampleTestCase2.class.getName() + ".testMethod",
        SampleTestCase.class.getName() + ".testMethod"
    );
    TestListenerAdapter adapter = runTest("Example_Suite", SuiteClassWithClassLevelParameters.class, expected,
        "Example_Test_One", "Example_Test_Two", "Example_Test_Three");
    List<Object[]> exp = Arrays.asList(
        new Object[]{42},
        new Object[]{false},
        new Object[]{24}
    );
    List<Object[]> act = adapter.getPassedTests()
        .stream()
        .map(ITestResult::getParameters)
        .collect(Collectors.toList());
    Assertions.assertThat(act).containsAll(exp);
  }

  @Test
  public void testMethodLevelParameters() {
    List<String> expected = Collections.singletonList(
        SampleTestCase3.class.getName() + ".testMethod");
    TestListenerAdapter adapter = runTest("Example_Suite",
        SuiteClassWithDiffMethodParameters.class, expected, "Example_Test_One");
    List<Object[]> exp = Collections.singletonList(new Object[]{42});
    List<Object[]> act = adapter.getPassedTests()
        .stream()
        .map(ITestResult::getParameters)
        .collect(Collectors.toList());
    Assertions.assertThat(act).containsAll(exp);
  }

}
