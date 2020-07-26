# Sangrahah (means *Collection*).

This library is an answer to a [TestNG defect](https://github.com/cbeust/testng/issues/2331).

**Sangrahah** lets you define a suite via a class using annotations as a means to creating an aggregation for execution. 

Its similar to what already exists in [JUnit](https://github.com/junit-team/junit4/wiki/aggregating-tests-in-suites).

Instead of creating a TestNG suite via an xml file, **Sangrahah** lets you define a suite file via a bunch of annotations.

## Pre-requisites:

* JDK8
* TestNG `7.1.0` (or) higher.

To consume this library add the below as a maven dependency:

```xml
<dependency>
  <groupId>com.rationaleemotions</groupId>
  <artifactId>sangrahah</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Some examples are as below:

#### A simple example to start with

```java
@XmlSuite(name = "Example_Suite",
    testClasses = {
        SampleTestCase.class,
        SampleTestCase2.class
    }
)
public class SuiteClass {
}
```

That's it. This class now doubles up as a full fledged TestNG suite xml. This class be directly executed via the surefire plugin (by configuring surefire plugin to pick up this class without a suite xml file)

#### An example of a suite file with detained class definitions.

```java
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
```

#### An example of a suite file with two different `<test>` tags.

```java
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
```

### An example of a suite with groups

```java
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
```

### An example of a suite with group definitions

```java
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
```

### An example of specifying parameters at `<suite>` level

```java
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

```

### An example of specifying parameters at `<test>` level

```java
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

```

### An example of specifying parameters for individual classes

```java
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
        )
    }
)
public class SuiteClassWithClassLevelParameters {

}

```
