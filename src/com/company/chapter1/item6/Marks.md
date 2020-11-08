# Avoid creating unnecessary objects

**_While String.matches is the easiest way to check if a string matches
a regular expression, it’s not suitable for repeated use in performance-critical
situations_**. The problem is that it internally creates a Pattern instance for the
regular expression and uses it only once, after which it becomes eligible for
garbage collection. Creating a Pattern instance is expensive because it requires
compiling the regular expression into a finite state machine.

When an object is immutable, it is obvious it can be reused safely, but there
are other situations where it is far less obvious, even counterintuitive. Consider the
case of adapters [Gamma95], also known as views. An adapter is an object that
delegates to a backing object, providing an alternative interface. Because an
adapter has no state beyond that of its backing object, there’s no need to create
more than one instance of a given adapter to a given object.

For example, the keySet method of the Map interface returns a Set view of the
Map object, consisting of all the keys in the map. Naively, it would seem that every
call to keySet would have to create a new Set instance, but every call to keySet
on a given Map object may return the same Set instance. Although the returned Set
instance is typically mutable, all of the returned objects are functionally identical:
when one of the returned objects changes, so do all the others, because they’re all
backed by the same Map instance. While it is largely harmless to create multiple
instances of the keySet view object, it is unnecessary and has no benefits.

Another way to create unnecessary objects is autoboxing, which allows the
programmer to mix primitive and boxed primitive types, boxing and unboxing
automatically as needed. **_Autoboxing blurs but does not erase the distinction
between primitive and boxed primitive types_**.

**_prefer primitives to
boxed primitives, and watch out for unintentional autoboxing_**