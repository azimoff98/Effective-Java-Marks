# Obey the general contract when overriding equals

Overriding the equals method seems simple, but there are many ways to get it
wrong, and consequences can be dire. The easiest way to avoid problems is not to
override the equals method, in which case each instance of the class is equal only
to itself.

**Each instance of the class is inherently unique.**

**There is no need for the class to provide a “logical equality” test**

**A superclass has already overridden equals, and the superclass behavior
is appropriate for this class.**

**The class is private or package-private, and you are certain that its equals
method will never be invoked.**

So when is it appropriate to override equals? It is when a class has a notion of
logical equality that differs from mere object identity and a superclass has not
already overridden equals. This is generally the case for value classes.

When you override the equals method, you must adhere to its general contract.
Here is the contract, from the specification for Object :

Reflexive: For any non-null reference value x, x.equals(x) must return true.

Symmetric: For any non-null reference values x and y, x.equals(y) must return
true if and only if y.equals(x) returns true.

Transitive: For any non-null reference values x, y, z, if x.equals(y) returns
true and y.equals(z) returns true, then x.equals(z) must return true.

Consistent: For any non-null reference values x and y, multiple invocations
of x.equals(y) must consistently return true or consistently return false,
provided no information used in equals comparisons is modified.

For any non-null reference value x, x.equals(null) must return false.

**Once you’ve violated the equals contract,
you simply don’t know how other objects will behave when confronted with
your object.**


**There is no way to extend an
instantiable class and add a value component while preserving the equals
contract**

The Liskov substitution principle says that any important property of a type
should also hold for all its subtypes so that any method written for the type should
work equally well on its subtypes.

There are some classes in the Java platform libraries that do extend an instantiable
class and add a value component. 

For example, java.sql.Timestamp extends java.util.Date and adds a nanoseconds field.
For example, you could have an abstract class Shape with no
value components, a subclass Circle that adds a radius field, and a subclass
Rectangle that adds length and width fields. Problems of the sort shown earlier
won’t occur so long as it is impossible to create a superclass instance directly.

Consistency—The fourth requirement of the equals contract says that if two
objects are equal, they must remain equal for all time unless one (or both) of them
is modified. In other words, mutable objects can be equal to different objects at
different times while immutable objects can’t.


Whether or not a class is immutable, **do not write an equals method that
depends on unreliable resources**. It’s extremely difficult to satisfy the consistency
requirement if you violate this prohibition. For example, java.net.URL’s
equals method relies on comparison of the IP addresses of the hosts associated
with the URLs. Translating a host name to an IP address can require network
access, and it isn’t guaranteed to yield the same results over time. This can cause
the URL equals method to violate the equals contract and has caused problems in
practice. The behavior of URL’s equals method was a big mistake and should not
be emulated.

If this type check were missing and the equals method were passed an argument
of the wrong type, the equals method would throw a ClassCastException,
which violates the equals contract. But the instanceof operator is specified to
return false if its first operand is null, regardless of what type appears in the
second operand [JLS, 15.20.2]. Therefore, the type check will return false if
null is passed in, so you don’t need an explicit null check.

1. Use the == operator to check if the argument is a reference to this object.
If so, return true. This is just a performance optimization but one that is worth
doing if the comparison is potentially expensive.
2. Use the instanceof operator to check if the argument has the correct type.
If not, return false.
3. Cast the argument to the correct type. Because this cast was preceded by an
instanceof test, it is guaranteed to succeed.
4. For each “significant” field in the class, check if that field of the argument
matches the corresponding field of this object.

for float fields, use the static Float.compare(float, float) method; and
for double fields, use Double.compare(double, double). The special treatment
of float and double fields is made necessary by the existence of
Float.NaN, -0.0f and the analogous double values; see JLS 15.21.1 or the
documentation of Float.equals for details. While you could compare float
and double fields with the static methods Float.equals and Double.equals,
this would entail autoboxing on every comparison, which would have poor
performance.

**When you are finished writing your equals method, ask yourself three
questions: Is it symmetric? Is it transitive? Is it consistent?**


• **Always override hashCode when you override equals**.

• **Don’t try to be too clever**. If you simply test fields for equality, it’s not hard
to adhere to the equals contract.

• **Don’t substitute another type for Object in the equals declaration**.


_**use Google’s open source AutoValue framework_**
