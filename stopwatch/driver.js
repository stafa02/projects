/*
* 2 instances of stopwatch used.
* x counts up and y counts down.
*/

var x = new stopwatch();
var y = new stopwatch();
var $time;
var $timeReverse;
var $buttonX;
var $buttonY;
var timer;
var endTime = 0;
var isPausedX = true;
var isPausedY = true;
function start(num) {
	if((num==1 && isPausedX) || (num != 1 && isPausedY)){
		timer = setInterval("updateTimer()", 100);
		if (num == 1){
			x.start();
			$buttonX.value = "pause";
			$buttonX.style = "color:red;font-size:30px";
			isPausedX = false;
		}
		else{
			y.start();
			$buttonY.value = "pause";
			$buttonY.style = "color:red;font-size:30px";
			isPausedY = false;
		}
	}
	else{
		pause(num);
	}
}
function pause(num) {
	if(num == 1){
		x.pause();
		$buttonX.value = " start ";
		$buttonX.style = "color:green;font-size:30px";
		isPausedX = true;
	}
	else{
		y.pause();
		$buttonY.value = " start ";
		$buttonY.style = "color:green;font-size:30px";
		isPausedY = true;
	}
	if(isPausedX && isPausedY){
		clearInterval(timer);
	}
}
function reset(num) {
	pause(num);
	if (num == 1){
		x.reset();
		enableButton($buttonX);
	}
	else{
		y.reset();
		enableButton($buttonY);
	}
	updateTimer();
}
function show() {
	$time = document.getElementById('time');
	$timeReverse = document.getElementById('timeReverse');
	$buttonX = document.getElementById('startX');
	$buttonY = document.getElementById('startY');
	updateTimer();
}
function updateTimer() {
	if(x.current() >= endTime){
		pause(1);
		$time.innerHTML = formatTime(endTime);
		disabled(1);
	}
	else{
		$time.innerHTML = formatTime(Math.floor(x.time()/1000));
	}	
	if(y.current()-endTime >= 0){
		pause(0);
		$timeReverse.innerHTML = formatTime(0);
		disabled(0);
		return;
	}
	$timeReverse.innerHTML = formatTime(y.timeRemaining());
}
function disabled(num){
	num ? disableButton($buttonX) : disableButton($buttonY);
}
function disableButton($button){
	$button.disabled = true;
	$button.style = "color:grey;font-size:30px";
}
function enableButton($button){
	$button.disabled = false;
	$button.style = "color:green;font-size:30px";
}
function formatTime(time) {
	var day = formatText(Math.floor(time/(24*60*60)),"days");
	time = time % (24*60*60);
	var hour = formatText(Math.floor(time/(60*60)),"hrs");
	time = time % (60*60);
	var mins = formatText(Math.floor(time/(60)),"mins");
	time = time % (60);
	var sec = formatText(time,"secs");
	return (day + "<br />" + hour + "<br />" + mins + "<br />" + sec);
}
function formatText(val,format){
	if(val < 10){
		val = '0'+val;
	}
	return val+' '+format;
}
function updateTime(){
	endTime = document.getElementById('endTime').value;
	enableButton($buttonX);
	enableButton($buttonY);
	updateTimer();
}
