# Minimize Mutability

An immutable class is simply a class whose instances cannot be modified. All of
the information contained in each instance is fixed for the lifetime of the object, so
no changes can ever be observed. The Java platform libraries contain many
immutable classes, including String, the boxed primitive classes, and
BigInteger and BigDecimal. There are many good reasons for this: Immutable
classes are easier to design, implement, and use than mutable classes. They are
less prone to error and are more secure.

To make a class immutable, follow these five rules:

1. **Don’t provide methods that modify the object’s state.**
2. **Ensure that the class can’t be extended.**
3. **Make all fields final.**
4. **Make all fields private.**
5. **Ensure exclusive access to any mutable components.** 

 Notice how the arithmetic operations create and return a new Complex instance rather than modifying this
instance. This pattern is known as the functional approach because methods return
the result of applying a function to their operand, without modifying it.

 Immutable objects are simple. 

Immutable objects are inherently thread-safe; they require no synchronization.
Since no thread can ever observe any effect of another thread on an immutable object,
immutable objects can be shared freely. 

A consequence of the fact that immutable objects can be shared freely is that
you never have to make defensive copies of them.

Therefore, you need not and should not provide a clone method or copy
constructor on an immutable class. 

**Not only can you share immutable objects, but they can share their internals.** 

**Immutable objects make great building blocks for other objects**

**Immutable objects provide failure atomicity for free**. Their state
never changes, so there is no possibility of a temporary inconsistency.

**The major disadvantage of immutable classes is that they require a
separate object for each distinct value.** 

The performance problem is magnified if you perform a multistep operation
that generates a new object at every step, eventually discarding all objects except
the final result. There are two approaches to coping with this problem. The first is
to guess which multistep operations will be commonly required and to provide
them as primitives. If a multistep operation is provided as a primitive, the
immutable class does not have to create a separate object at each step. Internally,
the immutable class can be arbitrarily clever.

**Classes should be immutable unless there’s a very good reason to make them mutable.**

 **If a class cannot be made immutable, limit its mutability as much as possible.**

 **Declare every field private final unless there’s a good reason to do otherwise.**

**Constructors should create fully initialized objects with all of their invariants established.** 

The CountDownLatch class exemplifies these principles. It is mutable, but its
state space is kept intentionally small. You create an instance, use it once, and it’s
done: once the countdown latch’s count has reached zero, you may not reuse it.
