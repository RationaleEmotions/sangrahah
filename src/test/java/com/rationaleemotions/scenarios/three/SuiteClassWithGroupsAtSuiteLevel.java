package com.rationaleemotions.scenarios.three;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlGroup;
import com.rationaleemotions.annotations.internal.XmlRun;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(name = "Example_Suite",
    groups = @XmlGroup(
        run = @XmlRun(include = "run")
    ),
    tests = {
        @XmlTest(name = "Example_Test",
            testClasses = {
                SampleTestCase.class,
                SampleTestCase2.class
            }
        )
    }
)
public class SuiteClassWithGroupsAtSuiteLevel {

}
