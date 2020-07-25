package com.rationaleemotions.listeners;

import com.rationaleemotions.internal.TestNGTransformer;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;

public class RuntimeSuiteBuilder implements IAlterSuiteListener {

  @Override
  public void alter(List<XmlSuite> suites) {
    List<XmlSuite> result = new LinkedList<>();
    for (XmlSuite suite : suites) {
      List<XmlClass> classesInSuite = extractClasses(suite);
      if (!isIDETriggeredExecution(classesInSuite)) {
        result.add(suite);
      }
      extract(classesInSuite).stream()
          .map(TestNGTransformer::asTestNGSuite)
          .forEach(result::add);
    }
    suites.clear();
    suites.addAll(result);
//    suites.forEach(each -> System.err.println(each.toXml()));
  }

  private boolean isIDETriggeredExecution(List<XmlClass> classes) {
    //Lets find if this suite was created by IDE when user right clicks the test class and runs it.
    if (classes.size() > 1) {
      return false;
    }
    return Objects.nonNull(classes.get(0).getSupportClass().getAnnotation(
        com.rationaleemotions.annotations.XmlSuite.class));
  }

  private List<XmlClass> extractClasses(XmlSuite suite) {
    return suite.getTests().stream()
        .flatMap(xmlTest -> xmlTest.getClasses().stream())
        .collect(Collectors.toList());
  }

  private List<com.rationaleemotions.annotations.XmlSuite> extract(List<XmlClass> xmlClasses) {
    return xmlClasses.stream()
        .map(this::hasSuiteAnnotation)
        .filter(Objects::nonNull).collect(Collectors.toList());
  }

  private com.rationaleemotions.annotations.XmlSuite hasSuiteAnnotation(XmlClass xmlClass) {
    return xmlClass.getSupportClass().getAnnotation(
        com.rationaleemotions.annotations.XmlSuite.class);
  }
}
