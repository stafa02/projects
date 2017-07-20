

#Compilation
From the folder: adib_mustafa_assign5\genericSerDeser\src

ant all

TO RUN:
From the folder: adib_mustafa_assign5\genericSerDeser\src

ant run -Darg0=<input-file-name> -Darg1=<output-fileName> -Darg2=<logger level>

Example:
ant run -Darg0=input.txt -Darg1=output.txt -Darg2=0

The files are read/written from/to here:
	adib_mustafa_assign5/genericDeser/src/input.txt

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.

[Date: 05/03/2017] 

CHOICE OF DATA STRUCTURE:
Used Arraylist store deserialized object to maintain order of input. It provides O(n) time complexity for get. 

References:
i) http://stackoverflow.com/questions/2126714/java-get-all-variable-names-in-a-class
ii) http://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java

Commits:

commit f6084ee4de57ad230fae9207b7ee61d137680fa4
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 18:11:01 2017 -0400

    Added ant build file

commit 4fccda638195804cf0f245622efa769d1624705c
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 18:04:18 2017 -0400

    added Readme

commit 81a3ec3b2980c9afbc6d8120bd90b52e8393c4af
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 17:57:28 2017 -0400

    checkin input file

commit f808c159a699e89f77b2e40563db2643757882ad
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 17:54:59 2017 -0400

    Serialization implementation using reflection

commit a5e239fd2568256bbe2218fb9cf1f0590c15ea37
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 17:54:17 2017 -0400

    refactor deserialization code

commit f46eca65f133368b8353fc82a5ee4c63ca798b69
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 17:52:57 2017 -0400

    Modifications to driver to handle serialization

commit 5b734d17890e88bbbe9bc538f4aef097315bbae6
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 17:11:23 2017 -0400

    maintain objects in order

commit 6e40469c8e597a55eaad01365291e83dae6c7ea0
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 16:35:58 2017 -0400

    Re-order members based on input

commit 56968a78e19cfe3789dc2cf23087a8a12616e9b8
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 14:19:54 2017 -0400

    modifications to assignment 4 code

commit 62bd18f50da1d659bb4e36025c81cfd1e9437adb
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 14:07:17 2017 -0400

    added concrete class for serialization

commit 5bc6838d0be2d108a155a9b5e62ad3fe0a1f76f7
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 13:59:28 2017 -0400

    added strategy interface

commit cda679fe9eb81cce939c8c8b9a173f348b377048
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 13:46:55 2017 -0400

    checkin deserializtion part completed in assignment 4

commit 4d842eb5f608dbfe9c51b8799421dc8ba8ce2643
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 13:27:26 2017 -0400

    remove file

commit 239e76c62eacdccbdd1c34b1e531e82f623fc1d9
Author: stafa <stafa92@gmail.com>
Date:   Wed May 3 13:20:22 2017 -0400

    test commit
