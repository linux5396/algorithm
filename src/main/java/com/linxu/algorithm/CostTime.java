package com.linxu.algorithm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author linxu
 * @date 2019/9/21
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@Target(ElementType.METHOD)
public @interface CostTime {
    String value() default "0";
}
