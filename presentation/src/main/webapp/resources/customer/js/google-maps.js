/*
 * Google maps integration.
 * Loaded using AJAX after document is ready.
 * Created by Ruslan Gunavardana
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
var DEFAULT_ZOOM = 12;
var SECS_PER_MINUTE = 60;
var MINUTES_PER_HOUR = 60;

function loadScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?v=3.9&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&sensor=true'
    + '&signed_in=true&callback=initialize';
    document.body.appendChild(script);
}

function initialize() {
    DEFAULT_LOCATION = new google.maps.LatLng(50.449226, 30.542454);
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
        preserveViewport: true
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
        updateTime(directionsDisplay.getDirections());
    });
}

function updateTime(result) {
    var route = result.routes[0];
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

/* Returns data about route in format {duration, length}.
 * Duration in minutes, length in km.
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
            $('#error-label').text('The Geolocation service failed.');
        });
    } else {
        // Browser doesn't support Geolocation
        $('#error-label').text('Your browser doesn\'t support geolocation.');
    }
}

/* Sending a request for building new path. */
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

/* Geocodes addreses and changes the route. */
function updateAddresses() {
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

$(document).ready(loadScript);