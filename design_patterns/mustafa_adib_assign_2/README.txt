

#Compilation
From the folder: mustafa_adib_assign_2\registrationScheduler\src

ant all

TO RUN:
From the folder: mustafa_adib_assign_2\registrationScheduler\src

ant run -Darg0=<reg-preference-file-name> -Darg0=<add-drop-file-name> -Darg1=<outputfilename> -Darg2=<number-of-threads> -Darg3=<logger level>

Example:
ant run -Darg0=reg-input.txt -Darg1=add-drop.txt -Darg2=output.txt -Darg3=2 -Darg4=0

The files are read/written from/to here:
	mustafa_adib_assign_2/registrationScheduler/reg-input.txt
	mustafa_adib_assign_2/registrationScheduler/add-drop.txt
	mustafa_adib_assign_2/registrationScheduler/output.txt
will run 2 threads
will use Logger for debug level 0

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: 03/07/2017] 

CHOICE OF DATA STRUCTURE:
Data structure used is hashmap (although it is not thread safe, methods modifying it are synchronized) because the frequent operations are insert and modify and the time complexity for avg case is O(1). 


Citations:
1. Head first design patterns

