# Always override toString

The toString contract goes on to say, “It is recommended
that all subclasses override this method.” Good advice, indeed!

While it isn’t as critical as obeying the equals and hashCode contracts, providing a good toString implementation makes your class
much more pleasant to use and makes systems using the class easier to debug.

When practical, the toString method should return all of the interesting
information contained in the object,

Whether or not you decide to specify the format, you should clearly document
your intentions.

Whether or not you specify the format, **provide programmatic access to the
information contained in the value returned by toString**.

It makes no sense to write a toString method in a static utility class.
Nor should you write a toString method in most enum types because
Java provides a perfectly good one for you. You should, however, write a
toString method in any abstract class whose subclasses share a common string
representation. For example, the toString methods on most collection implementations
are inherited from the abstract collection classes.