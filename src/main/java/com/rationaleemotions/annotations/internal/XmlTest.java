package com.rationaleemotions.annotations.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.testng.xml.XmlSuite.ParallelMode;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlTest {

  XmlGroup groups() default @XmlGroup();

  Class<?>[] testClasses() default {};

  XmlClass[] classDefinitions() default {};

  String name();

  boolean junitMode() default false;

  int verbose() default 2;

  ParallelMode parallel() default ParallelMode.NONE;

  int threadCount() default 5;

  int timeout() default Integer.MAX_VALUE;

  boolean skipFailedInvocationCounts() default false;

  boolean preserveOrder() default true;

  boolean groupByInstances() default false;

  boolean allowReturnValues() default false;

  XmlParameter[] parameters() default {};

}
