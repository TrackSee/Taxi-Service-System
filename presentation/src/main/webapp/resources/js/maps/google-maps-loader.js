/**
 * Google maps integration.
 * Loaded using AJAX after document is ready.
 *
 * @author Ruslan Gunavardana
 */

/* Constants */
var APP_KEY = 'AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8';
var DEFAULT_LOCATION = {lat: 50.4500, lng: 30.5233};
var DEFAULT_REGION = 'UA';
var DEFAULT_ZOOM = 12;
var SECS_PER_MINUTE = 60;

var GOOGLE_MAPS_SRC = 'http://maps.googleapis.com/maps/api/js?v=3.19&key=' + APP_KEY
    + '&sensor=true&language=en&signed_in=true&callback=initializeMaps';

function loadScript() {
    var script = document.createElement('script');
    script.src = GOOGLE_MAPS_SRC;
    document.body.appendChild(script);
}

$(document).ready(loadScript);