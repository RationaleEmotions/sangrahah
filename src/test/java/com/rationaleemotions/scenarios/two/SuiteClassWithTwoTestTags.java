package com.rationaleemotions.scenarios.two;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test_One", testClasses = {
            SampleTestCase.class,
        }),
        @XmlTest(name = "Example_Test_Two", testClasses = {
            SampleTestCase2.class,
        })
    }
)
public class SuiteClassWithTwoTestTags {
}
