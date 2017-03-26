Name; Mustafa Adib
Email: madib1@binghamton.edu

Steps to compile and run

i) cd to madib_recommender_system dir

ii) $ make clean
cleans up previously compiled classes

iii) $ make
compiles java classes in recommenderSystem package

iv) $ java recommenderSystem.RecommenderClient <path to input file> <number of users> <number of items>
run client with 3 arguments, output.txt containing all values is generated in current directory
sample command:  java recommenderSystem.RecommenderClient ~/Documents/train_all_txt.txt 943 1682
