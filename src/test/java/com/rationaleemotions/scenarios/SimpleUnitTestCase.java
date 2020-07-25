package com.rationaleemotions.scenarios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class SimpleUnitTestCase {

  public final TestListenerAdapter runTest(String expectedSuiteName, Class<?> suiteClass, List<String> expected,
      String... testTagNames) {
    TestNG testng = new TestNG();
    testng.setTestClasses(new Class[]{suiteClass});
    TestListenerAdapter adapter = new TestListenerAdapter();
    testng.addListener(adapter);
    testng.run();
    assertThat(adapter.getPassedTests()).hasSize(expected.size());
    List<String> actual = adapter.getPassedTests()
        .stream().map(each -> each.getMethod().getQualifiedName())
        .collect(Collectors.toList());
    assertThat(actual).containsAll(expected);
    String[] actualTestTags = adapter.getTestContexts()
        .stream().map(ITestContext::getName)
        .sorted()
        .toArray(String[]::new);
    assertThat(testTagNames).containsExactlyInAnyOrder(actualTestTags);
    String actualSuite = adapter.getTestContexts().get(0).getCurrentXmlTest().getSuite().getName();
    assertThat(actualSuite).isEqualTo(expectedSuiteName);
    return adapter;
  }

}
