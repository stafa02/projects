/*
* stopwatch implementation. Uses sytem time to calculate elapsed seconds.
*/
var stopwatch = function() {
	var startTime = 0;
	var currentTime = 0;
	var systemTime = function() {
			return (new Date()).getTime(); 
		};
	var current1 = function(){
			var current =  currentTime + (startTime ? systemTime() - startTime : 0);
			return Math.floor(current/1000);
		}; 
	this.start = function() {
			startTime = startTime ? startTime : systemTime();
		};
	this.pause = function() {
			currentTime = startTime ? currentTime + systemTime() - startTime : currentTime;
			startTime = 0;
		};
	this.reset = function() {
			currentTime = startTime = 0;
		};
	this.time = function() {
			return currentTime + (startTime ? systemTime() - startTime : 0); 
		};
	this.timeRemaining = function(){
		return endTime - current1();
		};
	this.current = function(){
		return current1();
		};
	};
