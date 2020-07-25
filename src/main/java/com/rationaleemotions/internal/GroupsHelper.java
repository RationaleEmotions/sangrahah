package com.rationaleemotions.internal;

import com.rationaleemotions.annotations.internal.XmlGroup;
import com.rationaleemotions.annotations.internal.XmlGroupDependency;
import java.util.Arrays;
import java.util.Collections;
import org.testng.util.Strings;
import org.testng.xml.XmlDefine;
import org.testng.xml.XmlDependencies;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class GroupsHelper {
  private GroupsHelper() {

  }

  public static void addTestNGGroupsIfProvided(XmlSuite xmlSuite, XmlGroup xmlGroup) {
    XmlGroups xmlGroups = buildXmlGroups(xmlGroup);
    if (xmlGroups == null) {
      return;
    }
    xmlSuite.setGroups(xmlGroups);
  }

  public static void addTestNGGroupsIfProvided(XmlTest xmlTest, XmlGroup xmlGroup) {
    XmlGroups xmlGroups = buildXmlGroups(xmlGroup);
    if (xmlGroups == null) {
      return;
    }
    xmlTest.setGroups(xmlGroups);
  }

  private static XmlGroups buildXmlGroups(XmlGroup xmlGroup) {
    if (!isGroupsSpecified(xmlGroup)) {
      return null;
    }
    XmlGroups xmlGroups = new XmlGroups();
    addTestNGDefinesIfNeeded(xmlGroups, xmlGroup.define());
    addTestNGRunIfNeeded(xmlGroups, xmlGroup.run());
    addTestNGDepsIfNeeded(xmlGroups, xmlGroup.dependencies());
    return xmlGroups;
  }

  private static boolean isGroupsSpecified(XmlGroup xmlGroup) {
    boolean groupsDefinition = isXmlDefinePresent(xmlGroup.define());
    boolean runDefined = isXmlRunPresent(xmlGroup.run());
    boolean groupDeps = isXmlDependenciesPresent(xmlGroup.dependencies());
    return groupsDefinition || runDefined || groupDeps;
  }

  private static boolean isXmlDependenciesPresent(XmlGroupDependency[] xmlGroupDependencies) {
    return xmlGroupDependencies.length != 0;
  }

  private static boolean isXmlDefinePresent(
      com.rationaleemotions.annotations.internal.XmlDefine xmlDefine) {
    return Strings.isNotNullAndNotEmpty(xmlDefine.name()) && xmlDefine.include().length != 0;
  }

  private static boolean isXmlRunPresent(com.rationaleemotions.annotations.internal.XmlRun xmlRun) {
    return xmlRun.exclude().length != 0 || xmlRun.include().length != 0;
  }



  private static void addTestNGDepsIfNeeded(XmlGroups xmlGroups,
      XmlGroupDependency[] dependencies) {
    if (!isXmlDependenciesPresent(dependencies)) {
      return;
    }
    Arrays.stream(dependencies).forEach(dependency -> {
      XmlDependencies xmlDependency = new XmlDependencies();
      xmlDependency.onGroup(dependency.groupName(), dependency.dependsOnGroups());
      xmlGroups.setXmlDependencies(xmlDependency);
    });
  }

  private static void addTestNGRunIfNeeded(XmlGroups xmlGroups,
      com.rationaleemotions.annotations.internal.XmlRun xmlRun) {
    if (!isXmlRunPresent(xmlRun)) {
      return;
    }
    XmlRun run = new XmlRun();
    Arrays.stream(xmlRun.include()).forEach(run::onInclude);
    Arrays.stream(xmlRun.exclude()).forEach(run::onExclude);
    xmlGroups.setRun(run);
  }

  private static void addTestNGDefinesIfNeeded(XmlGroups xmlGroups,
      com.rationaleemotions.annotations.internal.XmlDefine xmlDefine) {
    if (!isXmlDefinePresent(xmlDefine)) {
      return;
    }
    XmlDefine result = new XmlDefine();
    result.setName(xmlDefine.name());
    xmlGroups.addDefine(result);
    Arrays.stream(xmlDefine.include()).forEach(result::onElement);
    xmlGroups.setDefines(Collections.singletonList(result));
  }
}
