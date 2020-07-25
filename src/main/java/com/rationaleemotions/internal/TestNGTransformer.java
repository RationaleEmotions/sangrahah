package com.rationaleemotions.internal;

import com.rationaleemotions.annotations.internal.XmlParameter;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.testng.ITestObjectFactory;
import org.testng.internal.ClassHelper;
import org.testng.internal.InstanceCreator;
import org.testng.util.Strings;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public final class TestNGTransformer {

  private TestNGTransformer() {
  }

  public static XmlSuite asTestNGSuite(com.rationaleemotions.annotations.XmlSuite annotatedSuite) {
    XmlSuite xmlSuite = new XmlSuite();
    xmlSuite.setName(annotatedSuite.name());
    xmlSuite.setVerbose(annotatedSuite.verbose());
    xmlSuite.setParallel(annotatedSuite.parallel());
    xmlSuite.setThreadCount(annotatedSuite.threadCount());
    xmlSuite.setDataProviderThreadCount(annotatedSuite.dataProviderThreadCount());
    xmlSuite.setConfigFailurePolicy(annotatedSuite.configFailurePolicy());
    xmlSuite.setAllowReturnValues(annotatedSuite.allowReturnValues());
    xmlSuite.setGroupByInstances(annotatedSuite.groupByInstances());
    xmlSuite.setJUnit(annotatedSuite.junitMode());
    xmlSuite.setTimeOut(Integer.toString(annotatedSuite.timeout()));
    xmlSuite.setSkipFailedInvocationCounts(annotatedSuite.skipFailedInvocationCounts());
    xmlSuite.setPreserveOrder(annotatedSuite.preserveOrder());
    xmlSuite.setParameters(Arrays.stream(annotatedSuite.parameters())
        .collect(Collectors.toMap(XmlParameter::name, XmlParameter::value)));

    String value = annotatedSuite.parentModule();
    if (Strings.isNotNullAndNotEmpty(value)) {
      xmlSuite.setParentModule(annotatedSuite.parentModule());
    }
    value = annotatedSuite.guiceStage();
    if (Strings.isNotNullAndNotEmpty(value)) {
      xmlSuite.setGuiceStage(value);
    }
    value = annotatedSuite.objectFactory();
    if (Strings.isNotNullAndNotEmpty(value)) {
      xmlSuite.setObjectFactory(getObjectFactory(value));
    }
    if (isBothClassesAndTestsProvided(annotatedSuite)) {
      Logger.getAnonymousLogger().warning("Both test classes and xmlTests were specified. Ignoring xmlTests.");
      addTestNGTest(xmlSuite, annotatedSuite.testClasses());
    } else {
      if (annotatedSuite.testClasses().length != 0) {
        addTestNGTest(xmlSuite, annotatedSuite.testClasses());
      }
      if (annotatedSuite.classDefinitions().length != 0) {
        addTestNGTest(xmlSuite, annotatedSuite.classDefinitions());
      }
      if (annotatedSuite.tests().length != 0) {
        addTestNGTest(xmlSuite, annotatedSuite.tests());
      }
    }
    GroupsHelper.addTestNGGroupsIfProvided(xmlSuite, annotatedSuite.groups());
    return xmlSuite;
  }

  private static void addTestNGTest(XmlSuite xmlSuite,
      com.rationaleemotions.annotations.internal.XmlClass[] classes) {
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName(xmlSuite.getName() + "_test");
    ClassesHelper.addTestNGClassDefinitionsIfProvided(xmlTest, classes);
  }

  private static void addTestNGTest(XmlSuite xmlSuite, Class<?>[] classes) {
    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setName(xmlSuite.getName() + "_test");
    xmlTest.setXmlClasses(Arrays.stream(classes).map(XmlClass::new).collect(Collectors.toList()));
  }

  private static boolean isBothClassesAndTestsProvided(com.rationaleemotions.annotations.XmlSuite xmlSuite) {
    return xmlSuite.testClasses().length != 0 && xmlSuite.tests().length != 0;
  }

  private static ITestObjectFactory getObjectFactory(String cls) {
    return (ITestObjectFactory) InstanceCreator.newInstance(ClassHelper.forName(cls));
  }

  private static void addTestNGTest(XmlSuite xmlSuite,
      com.rationaleemotions.annotations.internal.XmlTest[] xmlTests) {
    if (xmlTests.length == 0) {
      return;
    }
    for(com.rationaleemotions.annotations.internal.XmlTest each : xmlTests) {
      addTest(each, xmlSuite);
    }
  }

  private static void addTest(com.rationaleemotions.annotations.internal.XmlTest xmlTest,
      XmlSuite xmlSuite) {
    XmlTest result = new XmlTest(xmlSuite);
    result.setName(xmlTest.name());
    result.setVerbose(xmlTest.verbose());
    result.setParallel(xmlTest.parallel());
    result.setThreadCount(xmlTest.threadCount());
    result.setAllowReturnValues(xmlTest.allowReturnValues());
    result.setGroupByInstances(xmlTest.groupByInstances());
    result.setJUnit(xmlTest.junitMode());
    result.setTimeOut(xmlTest.timeout());
    result.setSkipFailedInvocationCounts(xmlTest.skipFailedInvocationCounts());
    result.setPreserveOrder(xmlTest.preserveOrder());
    Map<String, String> parameters = Arrays.stream(xmlTest.parameters())
        .collect(Collectors.toMap(XmlParameter::name, XmlParameter::value));
    result.setParameters(parameters);
    GroupsHelper.addTestNGGroupsIfProvided(result, xmlTest.groups());
    ClassesHelper.addTestNGClassDefinitionsIfProvided(result, xmlTest.classDefinitions());
    ClassesHelper.addTestNGClassesIfProvided(result, xmlTest.testClasses());
  }

}
