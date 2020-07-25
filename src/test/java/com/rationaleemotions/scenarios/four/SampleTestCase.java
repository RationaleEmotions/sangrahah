package com.rationaleemotions.scenarios.four;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTestCase {

  @Parameters("age")
  @Test
  public void testMethod(int age) {}

}
