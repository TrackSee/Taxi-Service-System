/**
 * Google maps integration for order page.
 * Loaded using AJAX after document is ready.
 *
 * @author Ruslan Gunavardana
 */
var directionsDisplay;
var directionsService;

function initializeMaps() {
    DEFAULT_LOCATION = new google.maps.LatLng(DEFAULT_LOCATION.lat, DEFAULT_LOCATION.lng);

    // creating map
    var mapOptions = {
        zoom: DEFAULT_ZOOM,
        center: DEFAULT_LOCATION,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    new google.maps.TrafficLayer().setMap(map);

    // creating path
    initializeDirections(map);
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function(){
        updateAddresses(directionsDisplay.getDirections().routes[0]);
        updatePrice();
    });
    tryGeolocation(map);
    calcRoute(DEFAULT_LOCATION, DEFAULT_LOCATION, []);
}

function initializeDirections(map) {
    var rendererOptions = {
        draggable: true,
        preserveViewport: true,
        region: DEFAULT_REGION
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
}

function tryGeolocation(map) {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(pos);
            geolocationWorked = true;
            calcRoute(pos, pos, []);

        }, function() {
            $.notify('The Geolocation service failed.', 'error');
        });
    } else {
        // Browser doesn't support Geolocation
        $.notify("Your browser doesn't support geolocation.", 'error');
    }
}

/**
 * calcRoute
 * Sends a request for building new path to Google Directions Service.
 *
 * @param origin google.maps.LatLng
 * @param destination google.maps.LatLng
 * @param waypoints google.maps.DirectionsWaypoint []
 */
function calcRoute(origin, destination, waypoints) {
    var request = {
        origin: origin,
        destination: destination,
        waypoints: waypoints,
        travelMode: google.maps.TravelMode.DRIVING
    };
    directionsService.route(request, function(response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        } else {
            $('#origin').notify("Google couldn't find the address", { position: 'right', className: 'error'});
        }
    });
}

/**
 * getRoutesData
 * Returns data about route in format {duration, length}.
 * Duration in minutes, length in km.
 *
 * @returns {[{duration: number, distance: number, route: string}]}
 */
function getRoutesData() {
    var route = directionsDisplay.getDirections().routes[0];
    var duration = 0;
    var distance = 0;
    for (var i = 0; i < route.legs.length; i++) {
        duration += route.legs[i].duration.value;
        distance += route.legs[i].distance.value;
    }
    return [{
        durationInMin   : Math.round(duration / SECS_PER_MINUTE), // to minutes
        distance        : Math.round(distance / 100) / 10,        // to km
        encodedRoute : route.overview_polyline // encoded poly
    }];
}

/**
 * updateAddresses
 * Updates addresses text fields with new Google Maps
 * information.
 *
 * @param route {google.maps.DirectionsRoute}
 */
function updateAddresses(route) {
    $('#origin').val(route.legs[0].start_address);
    $('#destination').val(route.legs[route.legs.length - 1].end_address);
}

/**
 * updateRoute
 * Geocodes entered addresses and changes the route on the map.
 */
function updateRoute() {
    calcRoute($('#origin').val(), $('#destination').val(), []);
}

