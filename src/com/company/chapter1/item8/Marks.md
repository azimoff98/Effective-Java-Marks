# Avoid finalizers and cleaners

**_Finalizers are unpredictable, often dangerous, and generally unnecessary.**_
Their use can cause erratic behavior, poor performance, and portability problems.
Finalizers have a few valid uses, which we’ll cover later in this item, but as a rule,
you should avoid them. As of Java 9, finalizers have been deprecated, but they are
still being used by the Java libraries. The Java 9 replacement for finalizers is
cleaners. _**Cleaners are less dangerous than finalizers, but still unpredictable,
slow, and generally unnecessary**_.

C++ destructors are also used to reclaim other nonmemory resources. In Java, a
try-with-resources or try-finally block is used for this purpose

_**you should never do anything time-critical in a finalizer or cleaner.**_

As a consequence, you should **_never
depend on a finalizer or cleaner to update persistent state.**_

Another problem with finalizers is that an uncaught exception thrown during
finalization is ignored, and finalization of that object terminates

Normally, an uncaught exception will terminate the thread and print a stack
trace, but not if it occurs in a finalizer—it won’t even print a warning. Cleaners do
not have this problem because a library using a cleaner has control over its thread.

**_There is a severe performance penalty for using finalizers and cleaners**_.

In other words, it is about 50
times slower to create and destroy objects with finalizers.

**_Finalizers have a serious security problem: they open your class up to
finalizer attacks**_

_**Throwing an exception from a constructor
should be sufficient to prevent an object from coming into existence; in the
presence of finalizers, it is not**_. Such attacks can have dire consequences. Final
classes are immune to finalizer attacks because no one can write a malicious
subclass of a final class. _**To protect nonfinal classes from finalizer attacks,
write a final finalize method that does nothing**_.

_**have your class implement AutoCloseable**_, and require its clients
to invoke the close method on each instance when it is no longer needed,
typically using try-with-resources to ensure termination even in the face of
exceptions

Some Java
library classes, such as FileInputStream, FileOutputStream, ThreadPoolExecutor,
and java.sql.Connection, have finalizers that serve as safety nets.

A second legitimate use of cleaners concerns objects with native peers. A
native peer is a native (non-Java) object to which a normal object delegates via
native methods. Because a native peer is not a normal object, the garbage collector
doesn’t know about it and can’t reclaim it when its Java peer is reclaimed. A
cleaner or finalizer may be an appropriate vehicle for this task, assuming the
performance is acceptable and the native peer holds no critical resources.

Unlike finalizers,
cleaners do not pollute a class’s public API


