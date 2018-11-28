function startTour() {
	var startLocation = document.getElementById('destination-input').value
	localStorage.setItem("TravelTourStart", startLocation);
	window.location.href="mapSearch.html";
}

document.getElementById('destination-input').addEventListener("keydown", function(e) {
	if (!e) { var e = window.event; }

	// Enter is pressed
	if (e.keyCode == 13) { startTour(); }
}, false);

