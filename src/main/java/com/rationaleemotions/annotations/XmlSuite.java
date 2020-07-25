package com.rationaleemotions.annotations;

import com.rationaleemotions.annotations.internal.XmlClass;
import com.rationaleemotions.annotations.internal.XmlGroup;
import com.rationaleemotions.annotations.internal.XmlParameter;
import com.rationaleemotions.annotations.internal.XmlTest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.testng.xml.XmlSuite.FailurePolicy;
import org.testng.xml.XmlSuite.ParallelMode;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlSuite {

  String name();

  boolean junitMode() default false;

  int verbose() default 2;

  ParallelMode parallel() default ParallelMode.NONE;

  String parentModule() default "";

  String guiceStage() default "";

  String objectFactory() default "";

  FailurePolicy configFailurePolicy() default FailurePolicy.SKIP;

  int threadCount() default 5;

  int dataProviderThreadCount() default 10;

  XmlGroup groups() default @XmlGroup();

  int timeout() default Integer.MAX_VALUE;

  boolean skipFailedInvocationCounts() default false;

  boolean preserveOrder() default true;

  boolean groupByInstances() default false;

  boolean allowReturnValues() default false;

  XmlTest[] tests() default {};

  Class<?>[] testClasses() default {};

  XmlParameter[] parameters() default {};

  XmlClass[] classDefinitions() default {};
}
