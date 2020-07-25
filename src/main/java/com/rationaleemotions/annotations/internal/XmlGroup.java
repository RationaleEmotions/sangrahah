package com.rationaleemotions.annotations.internal;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlGroup {

  XmlDefine define() default @XmlDefine(name = "");

  XmlRun run() default @XmlRun();

  XmlGroupDependency[] dependencies() default {};

}
