package com.rationaleemotions.scenarios.two;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test", testClasses = {
            SampleTestCase.class,
            SampleTestCase2.class
        })
    }
)
public class SuiteClass {

}
