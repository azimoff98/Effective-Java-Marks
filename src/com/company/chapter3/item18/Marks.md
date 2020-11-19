# Favor composition over inheritance

It is safe to use inheritance within a package, where the
subclass and the superclass implementations are under the 
control of the same programmers. It is also safe to use 
inheritance when extending classes specifically designed 
and documented for extension.

**Unlike method invocation, inheritance violates encapsulation**

Internally, HashSet’s addAll method is implemented
on top of its add method, although HashSet, quite reasonably, does not
document this implementation detail. The addAll method in Instrumented-
HashSet added three to addCount and then invoked HashSet’s addAll implementation
using super.addAll. This in turn invoked the add method, as overridden in
InstrumentedHashSet, once for each element. Each of these three invocations
added one more to addCount, for a total increase of six: each element added with
the addAll method is double-counted.

We could “fix” the subclass by eliminating its override of the addAll method.
While the resulting class would work, it would depend for its proper function on
the fact that HashSet’s addAll method is implemented on top of its add method.

It would be slightly better to override the addAll method to iterate over the
specified collection, calling the add method once for each element. This would
guarantee the correct result whether or not HashSet’s addAll method were
implemented atop its add method because HashSet’s addAll implementation
would no longer be invoked.

Instead of
extending an existing class, give your new class a private field that references an
instance of the existing class. This design is called composition because the existing
class becomes a component of the new one. Each instance method in the new
class invokes the corresponding method on the contained instance of the existing
class and returns the results. This is known as forwarding, and the methods in the
new class are known as forwarding methods.

The InstrumentedSet class implements
the Set interface and has a single constructor whose argument is also of
type Set. In essence, the class transforms one Set into another, adding the instrumentation
functionality. Unlike the inheritance-based approach, which works only
for a single concrete class and requires a separate constructor for each supported
constructor in the superclass, the wrapper class can be used to instrument any Set
implementation and will work in conjunction with any preexisting constructor.

The InstrumentedSet class is known as a wrapper class because each
InstrumentedSet instance contains (“wraps”) another Set instance. This is also
known as the Decorator pattern because the InstrumentedSet class
“decorates” a set by adding instrumentation. Sometimes the combination of composition
and forwarding is loosely referred to as delegation. Technically it’s not
delegation unless the wrapper object passes itself to the wrapped object.

The disadvantages of wrapper classes are few. One caveat is that wrapper
classes are not suited for use in callback frameworks, wherein objects pass selfreferences
to other objects for subsequent invocations (“callbacks”). Because a
wrapped object doesn’t know of its wrapper, it passes a reference to itself (this)
and callbacks elude the wrapper. This is known as the SELF problem.

Inheritance is appropriate only in circumstances where the subclass really is a
subtype of the superclass. In other words, a class B should extend a class A only if
an “is-a” relationship exists between the two classes. If you are tempted to have a
class B extend a class A, ask yourself the question: Is every B really an A?

There are a number of obvious violations of this principle in the Java platform
libraries. For example, a stack is not a vector, so Stack should not extend Vector.
Similarly, a property list is not a hash table, so Properties should not extend
Hashtable. In both cases, composition would have been preferable.

To summarize, inheritance is powerful, but it is problematic because it
violates encapsulation. It is appropriate only when a genuine subtype relationship
exists between the subclass and the superclass. Even then, inheritance may lead to
fragility if the subclass is in a different package from the superclass and the
superclass is not designed for inheritance.