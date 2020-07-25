package com.rationaleemotions.annotations.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlMethod {

  String include() default "";

  String exclude() default "";

  XmlParameter[] parameters() default {};
}
