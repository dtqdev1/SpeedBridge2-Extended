package io.tofpu.speedbridge2;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
//        typeImmutable = "*Impl", // No prefix or suffix for generated immutable classes
        from = "", // we don't want the #from method in the builder
        deepImmutablesDetection = true,
        allParameters = true
)
public @interface MyStyle {
}
