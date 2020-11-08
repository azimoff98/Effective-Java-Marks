# Prefer dependency injection to hardwiring resources

**_Static utility classes and singletons are inappropriate for
classes whose behavior is parameterized by an underlying resource._**

A simple pattern that satisfies this requirement is **_to
pass the resource into the constructor when creating a new instance_**. This is
one form of dependency injection: the dictionary is a dependency of the spell
checker and is injected into the spell checker when it is created.

Dependency injection is equally
applicable to constructors, static factories, and builders.

In summary, do not use a singleton or static utility class to implement a class
that depends on one or more underlying resources whose behavior affects that of
the class, and do not have the class create these resources directly