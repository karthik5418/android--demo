package karthik.app.demo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by NiCK on 1/2/2018.
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
