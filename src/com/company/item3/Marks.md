
# Singleton

Enforce the singleton property with a private 
constructor or an enum type.

**_Making a class a singleton can make it difficult to test
its clients_** because it's impossible to substitute a mock
implementation for a singleton unless it implements an 
interface that serves as its type.

**_If you need to defend against reflective attacks, modify 
constructor to make it throw an exception if it's 
asked to create a second instance._**

You can write a **_generic singleton_** if your 
application requires it.

Single element enum type is often the best way
to implement a singleton
