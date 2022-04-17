Problem Statement
Consider a scenario where thousands of trades flows into one store. Assume any way of transmission of trades.
We need to create a one trade store, which stores the trade in the following order

You would be required to include following validations in the program.
1. During transmission if the lower version is being received by the store it will reject the trade and throw
an exception. If the version is same it will override the existing record.
2. Store should not allow the trade which has less maturity date then today date.
3. Store should automatically update the expire flag if in a store the trade crosses the maturity date.

CURLS ->

1. To store the trades in DB below is the curl request:

curl --location --request PUT 'http://localhost:8080/tradeStore' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "T1",
    "version" : 1,
    "counterPartyId" : "CP-1",
    "bookId": "B1",
    "maturityDate": "20/05/2024",
    "createdDate": "17/04/2022",
    "expired": "N"
}'

Sample Output: 
Empty Response with 201 Created HttpStatus.


2. To fetch the trades from DB below is the curl request:

curl --location --request GET 'http://localhost:8080/tradeStores'

Sample Output:

[
    {
        "version": 1,
        "counterPartyId": "CP-1",
        "bookId": "B1",
        "maturityDate": "20/05/2024",
        "createdDate": "17/04/2022",
        "expired": "N",
        "id": "T1"
    },
    {
        "version": 2,
        "counterPartyId": "CP-2",
        "bookId": "B1",
        "maturityDate": "20/05/2024",
        "createdDate": "17/04/2022",
        "expired": "N",
        "id": "T2"
    },
    {
        "version": 3,
        "counterPartyId": "CP-3",
        "bookId": "B1",
        "maturityDate": "20/05/2024",
        "createdDate": "17/04/2022",
        "expired": "N",
        "id": "T3"
    }
]
