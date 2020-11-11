# Always override hashCode when you override equals

**You must override hashCode in every class that overrides equals.** If you fail to
do so, your class will violate the general contract for hashCode, which will
prevent it from functioning properly in collections such as HashMap and HashSet.

When the hashCode method is invoked on an object repeatedly during an
execution of an application, it must consistently return the same value,
provided no information used in equals comparisons is modified. This value
need not remain consistent from one execution of an application to another.

If two objects are equal according to the equals(Object) method, then calling
hashCode on the two objects must produce the same integer result.

If two objects are unequal according to the equals(Object) method, it is not
required that calling hashCode on each of the objects must produce distinct
results. However, the programmer should be aware that producing distinct
results for unequal objects may improve the performance of hash tables.

**The key provision that is violated when you fail to override hashCode is
the second one: equal objects must have equal hash codes.** Two distinct
instances may be logically equal according to a class’s equals method, but to
Object’s hashCode method, they’re just two objects with nothing much in
common.

`Map<PhoneNumber, String> m = new HashMap<>();`
`m.put(new PhoneNumber(707, 867, 5309), "Jenny");`

At this point, you might expect m.get(new PhoneNumber(707, 867, 5309)) to
return "Jenny", but instead, it returns null. Notice that two PhoneNumber
instances are involved: one is used for insertion into the HashMap, and a second,
equal instance is used for (attempted) retrieval.

Therefore, the get method is likely to look for
the phone number in a different hash bucket from the one in which it was stored
by the put method. Even if the two instances happen to hash to the same bucket,
the get method will almost certainly return null, because HashMap has an optimization
that caches the hash code associated with each entry and doesn’t bother
checking for object equality if the hash codes don’t match.


The value 31 was chosen because it is
an odd prime. If it were even and the multiplication overflowed, information
would be lost, because multiplication by 2 is equivalent to shifting. The advantage
of using a prime is less clear, but it is traditional. A nice property of 31 is that the
multiplication can be replaced by a shift and a subtraction for better performance
on some architectures: 31 * i == (i << 5) - i. Modern VMs do this sort of optimization
automatically.  

The Objects class has a static method that takes an arbitrary number of
objects and returns a hash code for them. This method, named hash, lets you write
one-line hashCode methods whose quality is comparable to those written according
to the recipe in this item. Unfortunately, they run more slowly because they
entail array creation to pass a variable number of arguments, as well as boxing and
unboxing if any of the arguments are of primitive type. This style of hash function
is recommended for use only in situations where performance is not critical. Here
is a hash function for PhoneNumber written using this technique: 

**Do not be tempted to exclude significant fields from the hash code computation
to improve performance.**

**Don’t provide a detailed specification for the value returned by hashCode,
so clients can’t reasonably depend on it; this gives you the flexibility to
change it.**



