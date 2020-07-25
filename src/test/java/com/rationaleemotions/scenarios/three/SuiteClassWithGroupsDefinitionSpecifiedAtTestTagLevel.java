package com.rationaleemotions.scenarios.three;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlDefine;
import com.rationaleemotions.annotations.internal.XmlGroup;
import com.rationaleemotions.annotations.internal.XmlRun;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test",
            groups = @XmlGroup(
                define = @XmlDefine(
                    name = "runtime",
                    include = "run"
                ),
                run = @XmlRun(
                    include = "runtime"
                )
            ),
            testClasses = {
                SampleTestCase.class,
                SampleTestCase2.class
            }
        )
    }
)
public class SuiteClassWithGroupsDefinitionSpecifiedAtTestTagLevel {

}
