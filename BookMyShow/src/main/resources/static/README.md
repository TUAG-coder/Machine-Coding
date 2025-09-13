<b>Important annotations used:
<br>
<b>@Entity - To convert Java model classes to DB tables
<br>
<b>@MappedSuperclass - Used when we don't to create table
for a particular Model class but want to propagate 
its attributes in the child Model classes. So using this
annotation, we can get <b>id</b> column in tables of
Model classes such as User, Movie, Show, Theatres, 
Booking, etc.
<br>
<b>@Enumerated<b>
<br>
<b>@ElementCollection<b>
<br>