

#Compilation
From the folder: adib_mustafa_assignment_4\genericDeser\src

ant all

TO RUN:
From the folder: adib_mustafa_assignment_4\genericDeser\src

ant run -Darg0=<input-file-name> -Darg1=<logger level>

Example:
ant run -Darg0=input.txt -Darg1=0

The files are read/written from/to here:
	adib_mustafa_assignment_4/genericDeser/src/input.txt

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
Used hashset to store deserialized object to maintain uniqueness. It provides O(1) time complexity for add and get. 

Commits:

git log
commit 5eaa4729465156e2ec26c89470495e2c56f94b45
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 16:41:11 2017 -0400

    Added build and readme files

commit ca4a5843e1681e94534ed7a10b2de5601d1f5a90
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 14:46:50 2017 -0400

    Logger argument validation added to driver

commit 667defcafc9f6df4b9cd39921706ddf441ad50c6
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 14:32:53 2017 -0400

    Added Logger statements for all leveles

commit 1936d0783df06b06b9278505fff018ed6a398c97
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 13:13:35 2017 -0400

    Added implementation for logger

commit 59c2c181b11f2e4fdbf538cd32c71330c1e066f3
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 12:50:58 2017 -0400

    Modifications to handle empty lines in input

commit ef31d2800a390def21dfcb3c2ea1fd31c9578d5d
Author: stafa <stafa92@gmail.com>
Date:   Sat Apr 22 14:51:05 2017 -0400

    Added driver for assignment

commit c849aa66274f22e23a5dbbb14e442537eb80647f
Author: stafa <stafa92@gmail.com>
Date:   Sat Apr 22 14:49:04 2017 -0400

    Added implementation of reflection code

commit 011168d2423e4e0533f638cb0fbb19cfe8a2f1ac
Author: stafa <stafa92@gmail.com>
Date:   Fri Apr 21 22:56:00 2017 -0400
l-48@l48-Latitude-E5530-non-vPro:~/Documents/dp/assign4/adib_mustafa_assignment_4/genericDeser$ git log
commit 5eaa4729465156e2ec26c89470495e2c56f94b45
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 16:41:11 2017 -0400

    Added build and readme files

commit ca4a5843e1681e94534ed7a10b2de5601d1f5a90
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 14:46:50 2017 -0400

    Logger argument validation added to driver

commit 667defcafc9f6df4b9cd39921706ddf441ad50c6
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 14:32:53 2017 -0400

    Added Logger statements for all leveles

commit 1936d0783df06b06b9278505fff018ed6a398c97
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 13:13:35 2017 -0400

    Added implementation for logger

commit 59c2c181b11f2e4fdbf538cd32c71330c1e066f3
Author: stafa <stafa92@gmail.com>
Date:   Mon Apr 24 12:50:58 2017 -0400

    Modifications to handle empty lines in input

commit ef31d2800a390def21dfcb3c2ea1fd31c9578d5d
Author: stafa <stafa92@gmail.com>
Date:   Sat Apr 22 14:51:05 2017 -0400

    Added driver for assignment

commit c849aa66274f22e23a5dbbb14e442537eb80647f
Author: stafa <stafa92@gmail.com>
Date:   Sat Apr 22 14:49:04 2017 -0400

    Added implementation of reflection code

commit 011168d2423e4e0533f638cb0fbb19cfe8a2f1ac
Author: stafa <stafa92@gmail.com>
Date:   Fri Apr 21 22:56:00 2017 -0400

    Added implementation for Fileprocessor

commit 3b15a3c27ba22677e81a28aa149b3223240c5e7e
Author: stafa <stafa92@gmail.com>
Date:   Fri Apr 21 20:56:03 2017 -0400

    Added implementation for second object

commit 1d0e7a66c9e181883e2238034e37caf2f8b70c80
Author: stafa <stafa92@gmail.com>
Date:   Thu Apr 20 17:40:33 2017 -0400

    added implementation for First object

commit 5c759ab514add18d4e50e43a3b788bc924869db6
Author: stafa <stafa92@gmail.com>
Date:   Thu Apr 20 17:36:05 2017 -0400

    Added project structure
