function initAutocomplete() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 13,
		mapTypeId: 'roadmap'
	});
	var markers = [];
	var input = document.getElementById('pac-input');           // Create the search box and link it to the UI element.
	var searchBox = new google.maps.places.SearchBox(input);


	startLocation(localStorage.getItem("TravelTourStart"));
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
		searchBox.setBounds(map.getBounds());
	});

	// This event listener calls addMarker() when the map is clicked.
	google.maps.event.addListener(map, 'click', function(e) {
		placeMarker(e.latLng, "", map);
	});

	// Listen for the event fired when the user selects a prediction and retrieve locations
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();

		if (places.length == 0) {
			return;
		}

		// For each place, get the icon, name and location.
		var bounds = new google.maps.LatLngBounds();
		places.forEach(function(place) {
			if (!place.geometry) {
				console.log("Returned place contains no geometry");
				return;
			}

			// Create a marker for each place.
			placeMarker(place.geometry.location, place.name, map)
			map.panTo(place.geometry.location);

			//clear out search bar
			document.getElementById('pac-input').value = "";

			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		map.fitBounds(bounds);
	});

	function placeMarker(position, title, map) {
		var marker =new google.maps.Marker({
			position: position,
			title: title,
			map: map
		});
		var contentString = title +
			"<button onclick=''>Remove</button>";

		var infowindow = new google.maps.InfoWindow({
			content: contentString
		});

		marker.addListener('click', function() {
			infowindow.open(map, marker);
		});

		//add location to the list
		markers.push(position);
	}

	//Takes the title of a place and finds the location
	function startLocation(location) {
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode( { 'address': location }, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
			} else {
				alert("Could not find location: " + location);
			}
		});
	}

	//gets most popular tourist attractions in that location
	function sidebarLocations() {
		
	}

	function getMostPopularLocations() {

	}
}