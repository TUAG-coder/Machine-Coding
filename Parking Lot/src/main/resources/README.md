The DTOs are generally used in Controller methods, as the request body sent by UI/Client need not be same as our Model object. Another important reason is that Controller methods are client facing and we want an Abstraction layer as we want to hide actual objects from the User/Client. 

But it is not a cool practice to use DTOs for Services as Controller-Service communication is internal to our backend.

In IssueTicketRequestDTO, we're not giving complete Java objects as attributes as Client won't be giving us those. Therefore, we should have IDs as attributes in DTO classes.

Services classes should be as generic as possible, but a service method can be called by any of the Controller classes. It's not like methods in TicketService will be called by TicketController only.

For every Model class, we should have separate Repository.

Using Optional<> class is a good practice.