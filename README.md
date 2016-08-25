# star challenge
The intention of the star challenge is to determine the level of skill of the applicant. Based on the given example we want to evaluate the technical design and focus on implementation specifics. Implement and return this challenge to us as if you are pushing this code to production (including tests, documentation, etc.). You should be able to finish this challenge within 6 hours, if you do not finish in time, please stop and document what you would have done. 

## introduction to the model
* Production: A production represents a step along the production chain for transporting a shipment from the collection to the delivery address. Typically this production chain consists of the following productions. (production types)
 
	1. collection: a pick up from the collection address to the collection terminal,
	2. line haul: a transport from the collection terminal to the delivery terminal,
	3. delivery: a delivery from the delivery terminal to the delivery address.

* Lifecycle: The following table shows the lifecycle of a production along the production chain. The status flow of each production is `created` -> `inProduction` -> `completed` or `created` -> `completed`

/ 			| *collected*  	 | *entry*  	| *exit* 		| *delivered*
----------- | -------------- | ------------	| -------------	| ------------
*collection*| `inProduction` | `completed`  | `invalid` 	| `invalid`
*linehaul*  | `invalid` 	 | `completed` 	| `inProduction`| `invalid`
*delivery*  | `invalid` 	 | `invalid` 	| `inProduction`| `completed`

* Event: The event carriers a production reference and the event type. 

## tasks
The challenge is structured in three tasks, start with the first one and continue with the following in the listed order. Please use GIT and provide small commits with meaningful commit messages.

### task 1 - Data Access Layer - Locking
The first task is to implement a locking mechanism (lock and release) for manipulating any objects stored within the in memory data storage. Any object is stored as entity (see Entity class) wrapping each object maintained within the in memory data storage and holding the corresponding locking information. The locking should be done through the implied layering as provided in the template, look for the comment `#task 1` to see what methods are expected to be implemented. Further, it is crucial that the provided implementation is holistically tested. Hence, implement the provided InMemoryDaoTest class. The lock and release methods are required to ensure exclusive access to the entities and should be used from the production and event service.

### task 2 - CRUD Service - Production
The second task is to implement a user service for creating, updating, reading and deleting productions. In order to prevent locking contention this service makes use of optimistic locking, meaning that the user does not hold a lock on the production object while changing it. However, watch out for dirty writes - changes should be rejected, if the production has been changed in the meantime! - add a test to show that you took care of that. The service itself, however, needs to ensure mutual exclusion, when modifying productions. You should not be required to change or enhance the model. Search for the comment `#task 2` to see what classes are expected to be implemented.

### task 3 - Event Service - Event Processing
The third task is to implement a service for processing multiple events on productions. An event should transit the status of a production according to the table depicted above. Search for the comment `#task 3` to see what classes are expected to be implemented.

	1. Log invalid events as warnings.
	2. The events may arrive in mixed orders, however a status transition must only proceed forward. 
	3. Make use of multi-threading to improve the throughput. (Take care of mutual exclusion)

* Bonus Question: Why does the getInstance method in the ProductionDao need to be synchronized?

