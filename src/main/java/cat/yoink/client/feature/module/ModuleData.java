package cat.yoink.client.feature.module;

import org.lwjgl.input.Keyboard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleData
{
    String[] names();

    int bind() default Keyboard.KEY_NONE;

    boolean enabled() default false;
}
