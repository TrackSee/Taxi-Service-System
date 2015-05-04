/**
 * Google maps integration.
 * Loaded using AJAX after document is ready.
 *
 * @author Ruslan Gunavardana
 */

var directionsDisplay;
var directionsService;
var map;
var geocoder;
var infoBox;

/* Addresses in format {location, stopover} */
var origin;
var waypoints = [];
var destination;

/* Constants */
var DEFAULT_LOCATION;
var DEFAULT_REGION = 'UA';
var DEFAULT_ZOOM = 12;
var SECS_PER_MINUTE = 60;
var MINUTES_PER_HOUR = 60;

function loadScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'http://maps.googleapis.com/maps/api/js?v=3.9&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8'
        + '&sensor=true&language=en&signed_in=true&callback=initialize';
    document.body.appendChild(script);
}

function initialize() {
    DEFAULT_LOCATION = new google.maps.LatLng(50.4500, 30.5233);
    origin = {location: DEFAULT_LOCATION};
    destination = {location: DEFAULT_LOCATION};

    var mapOptions = {
        zoom: DEFAULT_ZOOM,
        center: DEFAULT_LOCATION,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    tryGeolocation();
    initializeDirections();
    calcRoute();
    geocoder = new google.maps.Geocoder();
    infoBox = new google.maps.InfoWindow();
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
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
        updateInfoBox(directionsDisplay.getDirections().routes[0]);
        updateAddresses(directionsDisplay.getDirections().routes[0]);
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
 * getRouteMiddle()
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

function tryGeolocation() {

    // HTML5 geolocation
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(pos);
            origin.location = pos;
            destination.location = pos;
            calcRoute();
        }, function() {
            $.notify('The Geolocation service failed.', 'error');
        });
    } else {
        // Browser doesn't support Geolocation
        $.notify('Your browser doesn\'t support geolocation.', 'error');
    }
}

/**
 * calcRoute()
 * Sends a request for building new path to Google Directions Service.
 */
function calcRoute() {
    var request = {
        origin: origin.location,
        destination: destination.location,
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
function useLatLngAddressFor(latLng, textInput) {
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

/* Results view */

/**
 * updateInfoBox
 * Updates route distance and duration information on the
 * {google.maps.InfoWindow} instance, that is connected to this route.
 *
 * @param route {google.maps.DirectionsRoute}
 */
function updateInfoBox(route) {
    var data = getRouteData(route);
    var time = data.duration;

    var timeText = time % MINUTES_PER_HOUR + ' m';
    if (time >= MINUTES_PER_HOUR) {
        timeText = Math.floor(time / MINUTES_PER_HOUR) + ' h ' + timeText;
    }
    var text = '<p>' + timeText + '<p>' + data.distance + ' km';

    if (data.distance > 0) {
        var middle = getRouteMiddle(route);
        infoBox.setContent(text);
        infoBox.setPosition(middle);
        infoBox.open(map);
    }
}

/**
 * updateAddresses
 * Updates addresses text fields with new Google Maps
 * information.
 */
function updateAddresses(route) {
    useLatLngAddressFor(route.overview_path[0], $('#origin'));
    useLatLngAddressFor(route.overview_path[route.overview_path.length - 1], $('#destination'));
}

/**
 * updateRoute()
 * Geocodes entered addresses and changes the route on the map.
 */
function updateRoute() {
    var address1 = $('#origin').val();
    var address2 = $('#destination').val();

    if (address1 != "") {
        origin.location = address1;
    }
    if (address2 != "") {
        destination.location = address2;
    }
    calcRoute();
}

//function getSo() {
//    return {route: directionsDisplay.getDirections().routes[0].overview_path}
//}

$(document).ready(loadScript);