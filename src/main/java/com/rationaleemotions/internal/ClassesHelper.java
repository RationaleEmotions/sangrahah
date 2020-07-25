package com.rationaleemotions.internal;

import com.rationaleemotions.annotations.internal.XmlClass;
import com.rationaleemotions.annotations.internal.XmlMethod;
import com.rationaleemotions.annotations.internal.XmlParameter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.testng.collections.Lists;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlTest;

public class ClassesHelper {

  private ClassesHelper() {
  }


  public static void addTestNGClassDefinitionsIfProvided(XmlTest xmlTest, XmlClass[] xmlClasses) {
    List<org.testng.xml.XmlClass> testngClasses = Arrays.stream(xmlClasses)
        .map(xmlClass -> {
          org.testng.xml.XmlClass result = new org.testng.xml.XmlClass(xmlClass.classToRun());
          handleMethodDefinitions(result, xmlClass.methods());
          for (XmlParameter p : xmlClass.parameters()) {
            result.getLocalParameters().put(p.name(), p.value());
          }
          return result;
        }).collect(Collectors.toList());
    xmlTest.setXmlClasses(testngClasses);
  }

  private static void handleMethodDefinitions(org.testng.xml.XmlClass result,
      XmlMethod[] xmlMethods) {
      AtomicInteger index = new AtomicInteger(0);
    for (XmlMethod xmlMethod : xmlMethods) {
      result.getExcludedMethods().add(xmlMethod.exclude());
      XmlInclude xi = new XmlInclude(xmlMethod.include(), index.getAndIncrement());
      for (XmlParameter p : xmlMethod.parameters()) {
        xi.getLocalParameters().put(p.name(), p.value());
      }
      result.getIncludedMethods().add(xi);
    }
  }

  public static void addTestNGClassesIfProvided(XmlTest xmlTest, Class<?>[] classes) {
    List<org.testng.xml.XmlClass> testngClasses = Arrays.stream(classes)
        .map(org.testng.xml.XmlClass::new)
        .collect(Collectors.toList());
    if (!testngClasses.isEmpty()) {
      xmlTest.setXmlClasses(testngClasses);
    }
  }

  private static List<Integer> stringToList(String in) {
    String[] numbers = in.split(" ");
    List<Integer> result = Lists.newArrayList();
    for (String n : numbers) {
      result.add(Integer.parseInt(n));
    }
    return result;
  }

}
