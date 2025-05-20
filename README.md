There is no additional steps to build the application. Everything is ready including API_KEY and application.properties file in code.

Sample urls with default data to test endpoints:


1. Create a new fundraising event:
 
POST | http://localhost:8080/event/add

body:

{
    "balance" : 0,
    "currency" : "PLN",
    "name" : "CHARITY EVENT 1"
}

2. Register a new collection box:

POST | http://localhost:8080/collectionbox/register

body:

{
    "identifier" : "BOX001",
    "currencies" : ["USD", "EUR"]
}

3. List all collection boxes. Include information if the box is assigned (but don’t expose to what
   fundraising event) and if it is empty or not (but don’t expose the actual value in the box):

GET | http://localhost:8080/collectionbox/getall

4. Unregister (remove) a collection box (e.g. in case it was damaged or stolen):

DELETE | http://localhost:8080/collectionbox/unregister/1

5. Assign the collection box to an existing fundraising event:

PATCH | http://localhost:8080/collectionbox/assign/1?eventId=1

6. Put (add) some money inside the collection box:

PATCH | http://localhost:8080/collectionbox/putmoney/1?currency=USD&amount=100

7. Empty the collection box i.e. “transfer” money from the box to the fundraising event’s account:

PATCH | http://localhost:8080/collectionbox/transfermoney/1

8. Display a financial report with all fundraising events and the sum of their accounts:

GET | http://localhost:8080/event/getfinancialreport

![Sii-2025-05-20_21-52](https://github.com/user-attachments/assets/5a4e6986-520c-4cf9-82ed-850338dc5165)

Thank you for the opportunity to take part in the task :)









