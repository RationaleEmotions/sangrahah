package com.rationaleemotions.scenarios.one;

import com.rationaleemotions.annotations.XmlSuite;
import com.rationaleemotions.annotations.internal.XmlClass;
import com.rationaleemotions.annotations.internal.XmlMethod;
import com.rationaleemotions.annotations.internal.XmlParameter;

@XmlSuite(name = "Example_Suite",
    classDefinitions = {
        @XmlClass(classToRun = SampleTestCase.class),
        @XmlClass(classToRun = SampleTestCase3.class,
            methods = {
                @XmlMethod(include = "anotherTestMethod",
                    parameters = {
                        @XmlParameter(name = "flag", value = "false")
                    }
                )
            }
        )
    }
)
public class SuiteClassWithClassDefinitions {

}
