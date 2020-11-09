# Prefer try-with-resources to try-finally

To be usable with this construct,
a resource must implement the AutoCloseable interface, which consists of a
single void-returning close method. Many classes and interfaces in the Java
libraries and in third-party libraries now implement or extend AutoCloseable. If
you write a class that represents a resource that must be closed, your class should
implement AutoCloseable too