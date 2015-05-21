/**
 * @author Ruslan Gunavardana
 */
var maps = [];
var directionsDisplays = [];

function initializeMaps() {
    DEFAULT_LOCATION = new google.maps.LatLng(DEFAULT_LOCATION.lat, DEFAULT_LOCATION.lng);

    var mapOptions = {
        zoom: DEFAULT_ZOOM,
        center: DEFAULT_LOCATION,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    for(var canvas in document.getElementsByClassName('map-canvas')) {
        maps.push(new google.maps.Map(canvas, mapOptions));
    }

    initializeDirections();
    calcRoute(DEFAULT_LOCATION, DEFAULT_LOCATION, []);
}

function initializeDirections() {
    var rendererOptions = {
        region: DEFAULT_REGION
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
}