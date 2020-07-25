package com.rationaleemotions.scenarios.four;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlClass;
import com.rationaleemotions.annotations.internal.XmlParameter;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(
    name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test_One",
            classDefinitions = {
                @XmlClass(classToRun = SampleTestCase.class,
                    parameters = {
                        @XmlParameter(name = "age", value = "42")
                    }
                )
            }
        ),
        @XmlTest(name = "Example_Test_Two",
            classDefinitions = {
                @XmlClass(classToRun = SampleTestCase2.class,
                    parameters = {
                        @XmlParameter(name = "flag", value = "false")
                    }
                )
            }
        ),
        @XmlTest(name = "Example_Test_Three",
            parameters = {
                @XmlParameter(name = "age", value = "24")
            },
            testClasses = {SampleTestCase.class}
        )
    }
)
public class SuiteClassWithClassLevelParameters {

}
