# Design and document for inheritance or el prohibit it

**The class must document its self-use of overridable methods.**

Designing for inheritance involves more than just documenting patterns of
self-use. To allow programmers to write efficient subclasses without undue pain, a
**class may have to provide hooks into its internal workings in the form of judiciously
chosen protected methods** or, in rare instances, protected fields. For
example, consider the removeRange method from java.util.AbstractList.

**Note: If ListIterator.remove requires linear time, this
implementation requires quadratic time.**

**The only way to test a class designed for inheritance is to write subclasses.**

**There are a few more restrictions that a class must obey to allow inheritance.
Constructors must not invoke overridable methods, directly or indirectly.**

You might expect this program to print out the instant twice, but it prints out null
the first time because overrideMe is invoked by the Super constructor before the
Sub constructor has a chance to initialize the instant field. Note that this program
observes a final field in two different states! Note also that if overrideMe had
invoked any method on instant, it would have thrown a NullPointerException
when the Super constructor invoked overrideMe. The only reason this program
doesn’t throw a NullPointerException as it stands is that the println method
tolerates null parameters.

Note that it is safe to invoke private methods, final methods, and static methods,
none of which are overridable, from a constructor.

The Cloneable and Serializable interfaces present special difficulties
when designing for inheritance. It is generally not a good idea for a class designed
for inheritance to implement either of these interfaces because they place a substantial
burden on programmers who extend the class.

If you do decide to implement either Cloneable or Serializable in a class
that is designed for inheritance, you should be aware that because the clone and
readObject methods behave a lot like constructors, a similar restriction applies:
neither clone nor readObject may invoke an overridable method, directly or
indirectly. In the case of readObject, the overriding method will run before the
subclass’s state has been deserialized. In the case of clone, the overriding method
will run before the subclass’s clone method has a chance to fix the clone’s state.
In either case, a program failure is likely to follow. In the case of clone, the failure
can damage the original object as well as the clone. This can happen, for example,
if the overriding method assumes it is modifying the clone’s copy of the object’s
deep structure, but the copy hasn’t been made yet.

Finally, if you decide to implement Serializable in a class designed for
inheritance and the class has a readResolve or writeReplace method, you must
make the readResolve or writeReplace method protected rather than private. If
these methods are private, they will be silently ignored by subclasses. This is one
more case where an implementation detail becomes part of a class’s API to permit
inheritance.

By now it should be apparent that **designing a class for inheritance requires
great effort and places substantial limitations on the class**.

The best solution to this problem is to prohibit subclassing in classes that
are not designed and documented to be safely subclassed. There are two ways
to prohibit subclassing. The easier of the two is to declare the class final. The
alternative is to make all the constructors private or package-private and to add
public static factories in place of the constructors.