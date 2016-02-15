# UVaBucketList - CS4720 Web & Mobile Systems Project
An Android Bucket List App by Vivian Liu, Qian Xiong and Yuanyang Zheng.

## Standard Features
- The user can see a list of item when the application starts up.
- The user can check off items at both the main page and the info page via the checkbox.
- Tapping on an item on the main page brings the user to the info page where some basic info about the item is shown.
- Everything works fine.

## Extra Features
- Achieve data persistence through SQLite Database. The database is initiated by reading a csv file in the resource. All data in the APP is dynamically read from the database.
- Add animation to the ListView items when scrolling.
- Add features to add/edit/delete items in the ListView.
- Add confirmation upon deleting an item.
- Bind both click listener (to access info page) and swipe left listener (to delete entry) to ListView items through SimpleOnGestureListener.
- Checked items are sorted to the bottom of the ListView.
- Handle the Back Stack correctly.

