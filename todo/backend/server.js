var express = require('express');
var bodyParser = require('body-parser')

var app = express();
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
var fs = require("fs");
var mysql = require('mysql');
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "root",
  database: "todo"
});

app.get('/getTasks', function (req, res) {
    con.query("SELECT * FROM tasks", function (err, result) {
      if (err){
        res.status(500);
        res.send("Error fetching records"); 
      }
      else if(result == null){
        res.status(404);
        res.send("No records found");
      }else{
        res.end(JSON.stringify(result));
      }
    });
})


app.post('/addTask', function (req, res) {
       data = req.body;
       con.query("insert into tasks (description) values('"+String(data.desc)+"')", function (err, result) {
        if (err){
          res.status(500);
          res.send("Error inserting record"); 
        }
        else{
          res.end(JSON.stringify(result));
        }
       });
           
})

app.post('/updateTask/:taskid', function (req, res) {
       var id = req.params.taskid;
       data = req.body;
       con.query("update tasks set description = '"+data.desc+"' where id = "+id, function (err, result) {
         if (err){
          res.status(500);
          res.send("Error updating record"); 
        }
        else{
         res.end(JSON.stringify(result));
        }
       });
           
})

app.delete('/deleteTask/:taskid', function (req, res) {
       var id = req.params.taskid;
       con.query("delete from tasks where id = "+id, function (err, result) {
         if (err){
          res.status(500);
          res.send("Error deleting record"); 
        }else{
         res.end(JSON.stringify(result));
        }
       });
           
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})
