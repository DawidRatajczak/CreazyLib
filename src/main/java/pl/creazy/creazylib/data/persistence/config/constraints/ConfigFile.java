package pl.creazy.creazylib.data.persistence.config.constraints;

import pl.creazy.creazylib.data.persistence.config.Config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigFile {
  String name();
  String path() default Config.DEFAULT_PATH;
}
