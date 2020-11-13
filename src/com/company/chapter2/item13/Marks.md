# Override clone judiciously

The Cloneable interface was intended as a mixin interface (Item 20) for classes to
advertise that they permit cloning.

It determines
the behavior of Object’s protected clone implementation: if a class implements
Cloneable, Object’s clone method returns a field-by-field copy of the object;
otherwise it throws CloneNotSupportedException.

**in practice, a class implementing
Cloneable is expected to provide a properly functioning public clone method.**
In order to achieve this, the class and all of its superclasses must obey a complex,
unenforceable, thinly documented protocol.

`x.clone() != x` -> true

`x.clone().getClass() == x.getClass()` -> true

`x.clone().equals(x)` -> true

but note that
**immutable classes should never provide a clone method** because it would
merely encourage wasteful copying.

In other
words, an overriding method’s return type can be a subclass of the overridden
method’s return type.

We must
cast the result of super.clone from Object to PhoneNumber before returning it,
but the cast is guaranteed to succeed

If the clone method merely
returns super.clone(), the resulting Stack instance will have the correct value in
its size field, but its elements field will refer to the same array as the original
Stack instance.

In effect, the clone method functions as a constructor; you
must ensure that it does no harm to the original object and that it properly
establishes invariants on the clone.

Calling clone on an array returns an array whose runtime and compile-time types
are identical to those of the array being cloned.

Note also that the earlier solution would not work if the elements field were
final because clone would be prohibited from assigning a new value to the field.
This is a fundamental problem: like serialization, **the Cloneable architecture is
incompatible with normal use of final fields referring to mutable objects**

In order to make a class cloneable, it may be necessary to remove
final modifiers from some fields.

Like a constructor, a clone method must never invoke an overridable method
on the clone under construction. If clone invokes a method that is overridden
in a subclass, this method will execute before the subclass has had a chance
to fix its state in the clone, quite possibly leading to corruption in the clone and the
original.

Object’s clone method is declared to throw CloneNotSupportedException,
but overriding methods need not. **Public clone methods should omit the throws
clause**, as methods that don’t throw checked exceptions are easier to use (Item 71).
You have two choices when designing a class for inheritance, but
whichever one you choose, the class should not implement Cloneable.

If you write a thread-safe class that
implements Cloneable, remember that its clone method must be properly synchronized,
just like any other method. Object’s clone method is not
synchronized, so even if its implementation is otherwise satisfactory, you may
have to write a synchronized clone method that returns super.clone().
To recap, all classes that implement Cloneable should override clone with a
public method whose return type is the class itself. This method should first call
super.clone, then fix any fields that need fixing.

If the class contains only primitive fields or references
to immutable objects, then it is likely the case that no fields need to be fixed.

**A better approach to object copying is to
provide a copy constructor or copy factory.** A copy constructor is simply a
constructor that takes a single argument whose type is the class containing the
constructor.

The copy constructor approach and its static factory variant have many
advantages over Cloneable/clone: they don’t rely on a risk-prone extralinguistic
object creation mechanism; they don’t demand unenforceable adherence to thinly
documented conventions; they don’t conflict with the proper use of final fields;
they don’t throw unnecessary checked exceptions; and they don’t require casts.
Furthermore, a copy constructor or factory can take an argument whose type
is an interface implemented by the class.

While it’s less
harmful for final classes to implement Cloneable, this should be viewed as a performance
optimization, reserved for the rare cases where it is justified.
As a rule, copy functionality is best provided by constructors or factories. A notable
exception to this rule is arrays, which are best copied with the clone method.