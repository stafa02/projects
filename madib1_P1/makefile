default: TSP.class TSPClient.class

TSP.class: tsp/TSP.java
	javac -g tsp/TSP.java

TSPClient.class: tsp/TSPClient.java
	javac -g tsp/TSPClient.java

run: TSP.class TSPClient.class
	java tsp.TSPClient input.txt

clean: 
	$(RM) tsp/*.class
	$(RM) output.txt
