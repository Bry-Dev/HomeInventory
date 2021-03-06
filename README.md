# HomeInventory
This is an android application that will help monitor the current household supplies.
This is now under beta release.

There are two items being handled -- Current Household supplies and Supplies for Shopping

To use the application here are the guidelines and info(Updated 04/12/2021):
  - Home Supplies
    - First Add a category on the first page menu(upper right)
      - This is to categorize the items (e.g. Vegetables, Fruits, Tools, Hardware, etc...)
    - After the category addition, you can now add items(upper right as well) under the categories
      - You will have to indicate the name and quantity
    - This will now be displayed on the Dashboard
      - Click the Category to display all items under it
    - Home Item Editting is now possible (Updated 04/20/2021)
      - Click the Home Item and it will display an edit button
        - This will prompt a dialog box to select quantity of item used
    - Home Item full edit is now via Long Key Press for the specific Home Item
      - If you need to edit the Category:
        - Click the Button near the Category, this will enable the drop down to select a new category
    - Category is now Editable (Updated 04/12/2021)
      - Long Click the Category and it will direct you to a page to rename the Category
      - Category can't be deleted --> This is to prevent the frustration of accidental deletion of Data
  - Shopping Supplies(Update 04/19/2021)
    - On the upper right corner, you can add new items for buying
    - Changed the Drop Down to an Auto Complete Textview for easier searching and selection of items.(Update 04/19/2021)
      - This will hopefully improve the selection process for large number of home items
    - Indicate the item, quantity, and when
      - Note: Items listed here are based on the Home Supplies. Please add home supplies first before adding it to your shopping list.
      - Note Cont: There are no notification yet when the date is near.
    - Items added here will now be displayed on the Shopping Dashboard
    - To delete an item( **no update** ), slide the item on the dashboard **left** (Update 05/06/2021)
    - To delete **and** update the item swipe it **right** (Update 05/06/2021)
      - Note: This will automatically update the number of items(based on the quantity bought) in the Home Supplies

  If you wish to test the app, please check the [_**Release page**_](https://github.com/Bry-Dev/HomeInventory/releases)

This is dedicated for the Wife;

For those that will use this as a reference for their projects, features that were used here and their location are:
  -
  - Data Binding -- RecyclerView Adapters + HomeBindingUtils or + ShoppingBindingUtils
  - RxJava/RxKotlin + Room -- AddHomeItemFragment
  - Room Relation one-to-many -- CategoryWithItems or ShoppingForHome
  - Hilt dependency Injection -- DatabaseModule
  - Nested RecyclerView -- CategoryViewAdapter + HomeBindingUtils & HomeRecyclerAdapter
  - Mobile Navigation with argument/parameters -- mobile_navigation for the XML changes and HomeFragment + AddCategoryFragment or HomeFragment + AddHomeItemFragment for the kotlin side
  - DialogFragment -- EditDialog
