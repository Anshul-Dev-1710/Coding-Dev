Problem Statement
Consider a scenario where thousands of trades flows into one store. Assume any way of transmission of trades.
We need to create a one trade store, which stores the trade in the following order

You would be required to include following validations in the program.
1. During transmission if the lower version is being received by the store it will reject the trade and throw
an exception. If the version is same it will override the existing record.
2. Store should not allow the trade which has less maturity date then today date.
3. Store should automatically update the expire flag if in a store the trade crosses the maturity date.
