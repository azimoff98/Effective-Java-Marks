# Noninstantiability with private constructors

**_Attempting to enforce noninstantiability by 
making a class abstract does
not work._** The class can be subclassed and 
the subclass instantiated. Furthermore,
it misleads the user into thinking the class was designed for inheritance

**_a class can be
made noninstantiable by including a private constructor**_


The AssertionError isn’t strictly required, but it provides insurance in case the
constructor is accidentally invoked from within the class. It guarantees the class
will never be instantiated under any circumstances.

As a side effect, this idiom also prevents the class from being subclassed. All
constructors must invoke a superclass constructor, explicitly or implicitly, and a
subclass would have no accessible superclass constructor to invoke.