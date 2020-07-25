package com.rationaleemotions.scenarios.one;

import com.rationaleemotions.annotations.XmlSuite;

@XmlSuite(name = "Example_Suite",
    testClasses = {
        SampleTestCase.class,
        SampleTestCase2.class
    }
)
public class SuiteClass {
}
