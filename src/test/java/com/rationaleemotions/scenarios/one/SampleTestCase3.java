package com.rationaleemotions.scenarios.one;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTestCase3 {
  @Test
  @Parameters("flag")
  public void anotherTestMethod(boolean flag) {}

}
