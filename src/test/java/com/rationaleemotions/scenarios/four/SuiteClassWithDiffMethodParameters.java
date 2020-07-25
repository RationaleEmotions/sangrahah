package com.rationaleemotions.scenarios.four;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlClass;
import com.rationaleemotions.annotations.internal.XmlMethod;
import com.rationaleemotions.annotations.internal.XmlParameter;
import com.rationaleemotions.annotations.internal.XmlTest;

@XmlSuite(
    name = "Example_Suite",
    tests = {
        @XmlTest(name = "Example_Test_One",
            classDefinitions = {
                @XmlClass(classToRun = SampleTestCase3.class,
                    methods = @XmlMethod(
                        include = "testMethod",
                        parameters = {
                            @XmlParameter(name = "age", value = "42")
                        }
                    )
                )
            }
        )
    }
)
public class SuiteClassWithDiffMethodParameters {

}
