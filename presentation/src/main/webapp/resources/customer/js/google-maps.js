/*
 * Google maps integration.
 * Loaded via AJAX after document is ready.
 * Created by Ruslan Gunavardana
 */

var directionsDisplay;
var directionsService;
var map;
var geocoder;

/*
 * Addresses in format {location, stopover}
 */
var origin;
var waypoints = [];
var destination;

var defaultLocation;
var defaultZoom = 12;

function loadScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'http://maps.googleapis.com/maps/api/js?v=3.9&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&sensor=true'
    + '&signed_in=true&callback=initialize';
    document.body.appendChild(script);
}

function initialize() {
    defaultLocation = new google.maps.LatLng(50.449226, 30.542454);
    origin = {location: defaultLocation};
    destination = {location: defaultLocation};

    var mapOptions = {
        zoom: defaultZoom,
        center: defaultLocation,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    tryGeolocation();
    initializeDirections();
    calcRoute();
    geocoder = new google.maps.Geocoder();
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
    $('#total').text(getRouteTime(result.routes[0]) + ' s');
}

function getRouteTime(route) {
    var total = 0;
    for (var i = 0; i < route.legs.length; i++) {
        total += route.legs[i].duration.value;
    }
    return total;
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

/*
 * Sending a request for building new path.
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

/*
 * Geocoding service
 */
function codeAddresses() {
    var address1 = $('#origin').val();
    var address2 = $('#destination').val();
    codeAddress(address1, origin);
    codeAddress(address2, destination);
}

function codeAddress(address, resultHolder) {
    geocoder.geocode( { 'address': address}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            resultHolder.location = results[0].geometry.location;
            //TODOmap.setCenter();
            calcRoute();
        } else {
            $('#error-label').text('Geocode was not successful for the following reason: ' + status);
        }
    });
}

$(document).ready(loadScript);