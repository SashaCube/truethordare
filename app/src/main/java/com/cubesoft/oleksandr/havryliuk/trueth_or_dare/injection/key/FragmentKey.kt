package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.key

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation class to identify view fragments by classname.
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(value = AnnotationRetention.RUNTIME)
@MapKey
internal annotation class FragmentKey(val value: KClass<out Fragment>)
