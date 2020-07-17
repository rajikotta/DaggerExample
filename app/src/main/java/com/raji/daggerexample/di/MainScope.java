package com.raji.daggerexample.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Raji on 7/17/20.
 * Golden Scent
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MainScope {
}
