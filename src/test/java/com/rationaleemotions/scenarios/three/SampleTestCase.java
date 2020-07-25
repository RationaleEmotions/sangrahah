package com.rationaleemotions.scenarios.three;

import org.testng.annotations.Test;

public class SampleTestCase {
  @Test(groups = "run")
  public void testMethod() {}

  @Test(groups = "dontrun")
  public void anotherTestMethod() {}

}
