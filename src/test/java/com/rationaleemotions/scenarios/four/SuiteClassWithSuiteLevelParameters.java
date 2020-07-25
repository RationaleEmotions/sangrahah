package com.rationaleemotions.scenarios.four;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlParameter;

@XmlSuite(
    name = "Example_Suite",
    parameters = {
        @XmlParameter(name = "age", value = "24")
    },
    testClasses = {SampleTestCase.class}
)
public class SuiteClassWithSuiteLevelParameters {

}
