package com.rationaleemotions.scenarios.four;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTestCase3 {

  @Parameters("age")
  @Test
  public void testMethod(int age) {
  }

  @Parameters("flag")
  @Test
  public void anotherTestMethod(boolean flag) {
  }

}
