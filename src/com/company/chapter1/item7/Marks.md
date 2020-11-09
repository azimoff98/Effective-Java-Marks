# Eliminate obsolete object references

This is because the stack maintains
obsolete references to these objects. An obsolete reference is simply a reference
that will never be dereferenced again. In this case, any references outside of
the “active portion” of the element array are obsolete. The active portion consists
of the elements whose index is less than size.

Memory leaks in garbage-collected languages (more properly known as unintentional
object retentions) are insidious. If an object reference is unintentionally
retained, not only is that object excluded from garbage collection, but so too are
any objects referenced by that object, and so on. Even if only a few object references
are unintentionally retained, many, many objects may be prevented from
being garbage 
collected, with potentially large effects on performance.

The elements in the active portion of the
array (as defined earlier) are allocated, and those in the remainder of the array are
free. The garbage collector has no way of knowing this; to the garbage collector,
all of the object references in the elements array are equally valid. Only the
programmer knows that the inactive portion of the array is unimportant. The programmer
effectively communicates this fact to the garbage collector by manually
nulling out array elements as soon as they become part of the inactive portion.

Generally speaking, **_whenever a class manages its own memory, the programmer
should be alert for memory leaks_**.

_**Another common source of memory leaks is caches**_. Once you put an
object reference into a cache, it’s easy to forget that it’s there and leave it in the
cache long after it becomes irrelevant.

**_A third common source of memory leaks is listeners and other callbacks.**_
If you implement an API where clients register callbacks but don’t deregister them
explicitly, they will accumulate unless you take some action. One way to ensure
that callbacks are garbage collected promptly is to store only weak references to
them, for instance, by storing them only as keys in a WeakHashMap.

They are typically
discovered only as a result of careful code inspection or with the aid of a
debugging tool known as a _heap profiler_.