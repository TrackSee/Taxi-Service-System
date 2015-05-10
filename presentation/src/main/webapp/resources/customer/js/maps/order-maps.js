/**
 * Google maps integration for order page.
 * Loaded using AJAX after document is ready.
 *
 * @author Ruslan Gunavardana
 */
var directionsDisplay;
var directionsService;
var map;
var geocoder;

function initializeMaps() {
    DEFAULT_LOCATION = new google.maps.LatLng(DEFAULT_LOCATION.lat, DEFAULT_LOCATION.lng);

    var mapOptions = {
        zoom: DEFAULT_ZOOM,
        center: DEFAULT_LOCATION,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    initializeDirections();
    calcRoute(DEFAULT_LOCATION, DEFAULT_LOCATION, []);
    tryGeolocation();
    geocoder = new google.maps.Geocoder();
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
        updateAddresses(directionsDisplay.getDirections().routes[0]);
    });
}

function initializeDirections() {
    var rendererOptions = {
        draggable: true,
        preserveViewport: true,
        region: DEFAULT_REGION
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
}

function tryGeolocation() {

    // HTML5 geolocation
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(pos);
            calcRoute(pos, pos, []);
        }, function() {
            $.notify('The Geolocation service failed.', 'error');
        });
    } else {
        // Browser doesn't support Geolocation
        $.notify('Your browser doesn\'t support geolocation.', 'error');
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
        }
    });
}

/**
 * useLatLngAddressFor
 * Function geocodes passed LatLng parameter and uses it
 * as callback parameter.
 *
 * @param latLng {google.maps.LatLng}
 * @param textInput {Node}
 */
function decodePointToText(latLng, textInput) {
    geocoder.geocode({'latLng': latLng}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                textInput.val(results[0].formatted_address);
            } else {
                $.notify('No results found', 'error');
            }
        } else {
            $.notify('Geocoder failed due to: ' + status, 'error');
        }
    });
}

/**
 * getRouteData
 * Returns data about route in format {duration, length}.
 * Duration in minutes, length in km.
 *
 * @param route {google.maps.DirectionsRoute}
 * @returns {{duration: number, distance: number}}
 */
function getRouteData(route) {
    var duration = 0;
    var distance = 0;
    for (var i = 0; i < route.legs.length; i++) {
        duration += route.legs[i].duration.value;
        distance += route.legs[i].distance.value;
    }
    return {duration : Math.round(duration / SECS_PER_MINUTE), // to minutes
        distance : Math.round(distance / 100) / 10};           // to km
}

/**
 * getRouteMiddle
 * Returns one of the middle route points.
 *
 * @param route {google.maps.DirectionsRoute}
 * @returns {google.maps.LatLng}
 */
function getRouteMiddle(route) {
    var middleLeg = route.legs[Math.floor(route.legs.length / 2)];
    var middleStep = middleLeg.steps[Math.floor(middleLeg.steps.length / 2)];
    return middleStep.end_location;
}

/**
 * updateAddresses
 * Updates addresses text fields with new Google Maps
 * information.
 */
function updateAddresses(route) {
    decodePointToText(route.overview_path[0], $('#origin'));
    decodePointToText(route.overview_path[route.overview_path.length - 1], $('#destination'));
}

/**
 * updateRoute
 * Geocodes entered addresses and changes the route on the map.
 */
function updateRoute() {
    calcRoute($('#origin').val(), $('#destination').val(), []);
}