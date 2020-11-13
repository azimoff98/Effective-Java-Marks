# Minimize the accessibility of classes and members

The single most important factor that distinguishes a well-designed component
from a poorly designed one is the degree to which the component hides its internal
data and other implementation details from other components. A well-designed
component hides all its implementation details, cleanly separating its API from its
implementation. Components then communicate only through their APIs and are
oblivious to each others’ inner workings. _This concept, known as information
hiding or encapsulation, is a fundamental tenet of software design_.

Information hiding is important for many reasons, most of which stem from
the fact that it decouples the components that comprise a system, allowing them to
be developed, tested, optimized, used, understood, and modified in isolation.

Finally, information hiding decreases the risk in building large systems
because individual components may prove successful even if the system does not.

The rule of thumb is simple: **make each class or member as inaccessible as
possible.**

If a package-private top-level class or interface is used by only one class,
consider making the top-level class a private static nested class of the sole class
that uses it.

But it is far more important to reduce the
accessibility of a gratuitously public class than of a package-private top-level
class: the public class is part of the package’s API, while the package-private toplevel
class is already part of its implementation.

For members of public classes, a huge increase in accessibility occurs when
the access level goes from package-private to protected. A protected member is
part of the class’s exported API and must be supported forever.

If a method overrides a superclass method, it cannot have a more restrictive
access level in the subclass than in the superclass [JLS, 8.4.8.3]. This is necessary
to ensure that an instance of the subclass is usable anywhere that an instance of the
superclass is usable. If you violate this rule, the compiler 
will generate an error message when you try to compile the subclass.

**Instance fields of public classes should rarely be public**.

**classes with public mutable fields are not generally thread-safe.**

Note that a nonzero-length array is always mutable, so **it is wrong for a class
to have a public static final array field, or an accessor that returns such a
field.**

