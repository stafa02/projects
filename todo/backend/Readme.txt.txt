Steps to run application:

i) Setup mysql database using the scripts in sql/ folder.
ii) Set mysql user and password in server.js
iii) Start the application -
     $ node server.js
iv) Use a rest client to call web service.

CRUD api's:
i) /getTasks : lists all tasks.
ii) /addTask : add a new task. expected son {"desc":"value"}
iii) /updateTask/:taskid : update task description for this taskid. expected son {"desc":"value"}
iv) /deleteTask/:taskid : delete record for this task.