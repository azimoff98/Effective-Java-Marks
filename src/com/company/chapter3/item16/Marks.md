# In public classes, use accessor methods, not public fields

should always be replaced by classes with
private fields and public accessor methods (getters) and, for mutable classes,
mutators (setters)

**if a class is accessible outside its package, provide accessor methods** to preserve the
flexibility to change the class’s internal representation.

However, **if a class is package-private or is a private nested class, there is
nothing inherently wrong with exposing its data fields**.

**In summary, public classes should never expose mutable fields.**