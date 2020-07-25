package com.rationaleemotions.scenarios.four;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlParameter;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(
    name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test_Three",
            parameters = {
                @XmlParameter(name = "age", value = "24")
            },
            testClasses = {SampleTestCase.class}
        )
    }
)
public class SuiteClassWithTestTagLevelParameters {

}
