# Decisions

* I've decided to go with the clean code architecture to show up how I'm familiar with it, also to keep code as more readable, I know that all of the implemented layers here could be implemented in the data class of the menu and that's it but I preferred to implement it with data and domain layers.
* I've created a single use case for each point of the requirements to keep following the single responsibility principle (SOLID).
* I preferred to implement also different use cases for multiple selection and un-selection, again to make it more reusable and to not create a list for a single recipe.
* One of the behaviours here that the user won't be able to select multiple recipes in case if the size of the selected recipes more that 3 or 5 according to the subscription "isForFamily" value, this could be enhanced to add some of the new selected recipes and just show the error for the extras not for all of the new selected recipes (it's depending on a business decision btw).
* Time consumed : 68 mins.
