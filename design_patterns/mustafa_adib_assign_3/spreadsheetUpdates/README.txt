

#Compilation
From the folder: mustafa_adib_assign_3\spreadsheetUpdates\src

ant all

TO RUN:
From the folder: mustafa_adib_assign_3\spreadsheetUpdates\src

ant run -Darg0=<input-file-name> -Darg1=<output-file-name> -Darg2=<logger level>

Example:
ant run -Darg0=reg-input.txt -Darg1=add-drop.txt -Darg2=output.txt -Darg3=2 -Darg4=0

The files are read/written from/to here:
	mustafa_adib_assign_2/spreadsheetUpdates/input.txt
	mustafa_adib_assign_2/spreadsheetUpdates/output.txt
will use Logger for debug level 0

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: 04/02/2017] 

CHOICE OF DATA STRUCTURE:
Used hashmap as an adjacency list to detect cycles. utilized DFS algorithm on the graph for cycle detection with O(V+E) complexity. Used an arrayList to store observers for each cell achieving search in O(n). 

Citations:
1. To check if a string is a number - http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
2. Prof. cutler's algorithm notes for cycle detection. 

