# Consider implementing Comparable

Virtually all of the value classes in the Java platform libraries, as well as all enum
types, implement Comparable. If you are writing a value class with an
obvious natural ordering, such as alphabetical order, numerical order, or chronological
order, you should implement the Comparable interface.

Throws ClassCastException if the specified
object’s type prevents it from being compared to this object.

• The implementor must ensure that sgn(x.compareTo(y)) == -sgn(y.
compareTo(x)) for all x and y. (This implies that x.compareTo(y) must
throw an exception if and only if y.compareTo(x) throws an exception.)

• The implementor must also ensure that the relation is transitive: (x.
compareTo(y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0.

• Finally, the implementor must ensure that x.compareTo(y) == 0 implies that
sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.

Unlike the equalsmethod, which imposes a global equivalence relation on all objects, compareTo
doesn’t have to work across objects of different types: when confronted with
objects of different types, compareTo is permitted to throw ClassCastException.
Usually, that is exactly what it does. The contract does permit intertype comparisons,
which are typically defined in an interface implemented by the objects being
compared.

Just as a class that violates the hashCode contract can break other classes that
depend on hashing, a class that violates the compareTo contract can break other
classes that depend on comparison. Classes that depend on comparison include
the sorted collections TreeSet and TreeMap and the utility classes Collections
and Arrays, which contain searching and sorting algorithms.

Let’s go over the provisions of the compareTo contract. The first provision
says that if you reverse the direction of a comparison between two object references,
the expected thing happens: if the first object is less than the second, then
the second must be greater than the first; if the first object is equal to the second,
then the second must be equal to the first; and if the first object is greater than the
second, then the second must be less than the first.


One consequence of these three provisions is that the equality test imposed by
a compareTo method must obey the same restrictions imposed by the equals contract:
reflexivity, symmetry, and transitivity.

If you
want to add a value component to a class that implements Comparable, don’t
extend it; write an unrelated class containing an instance of the first class. Then
provide a “view” method that returns the contained instance.

If you create an empty HashSet instance and then add
new BigDecimal("1.0") and new BigDecimal("1.00"), the set will contain two
elements because the two BigDecimal instances added to the set are unequal
when compared using the equals method. If, however, you perform the same
procedure using a TreeSet instead of a HashSet, the set will contain only one
element because the two BigDecimal instances are equal when compared using
the compareTo method.

Writing a compareTo method is similar to writing an equals method, but
there are a few key differences. Because the Comparable interface is parameterized,
the compareTo method is statically typed, so you don’t need to type check or
cast its argument. If the argument is of the wrong type, the invocation won’t even
compile. If the argument is null, the invocation should throw a NullPointer-
Exception, and it will, as soon as the method attempts to access its members.

Use of the relational operators < and > in compareTo methods is
verbose and error-prone and no longer recommended.

This implementation builds a comparator at class initialization time, using
two comparator construction methods.

There are also comparator construction methods for object reference types.
The static method, named comparing, has two overloadings. One takes a key
extractor and uses the keys’ natural order. The second takes both a key extractor
and a comparator to be used on the extracted keys.

In summary, whenever you implement a value class that has a sensible ordering,
you should have the class implement the Comparable interface so that its
instances can be easily sorted, searched, and used in comparison-based collections.
When comparing field values in the implementations of the compareTo
methods, avoid the use of the < and > operators. Instead, use the static compare
methods in the boxed primitive classes or the comparator construction methods in
the Comparator interface.
