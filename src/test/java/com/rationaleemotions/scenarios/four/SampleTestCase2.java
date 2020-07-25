package com.rationaleemotions.scenarios.four;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTestCase2 {

  @Parameters("flag")
  @Test
  public void testMethod(boolean flag) {}

}
