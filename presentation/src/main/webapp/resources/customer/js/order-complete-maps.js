/**
 * Google maps integration for order complete page.
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

    //decoding path
    var route = getOrderDTO().routes[0].encodedRoute;
    var latLngArr = google.maps.geometry.encoding.decodePath(route);

    //function getWaypointsArray (pv, cv) {
    //    pv.push({location: cv, stopover: false});
    //    return pv;
    //}

    var origin = latLngArr.shift();
    var dest = latLngArr.pop();
    //var waypoints = latLngArr.reduce(getWaypointsArray, []);
    var waypoints = [];

    // creating path
    initializeDirections(map);
    calcRoute(origin, dest, waypoints);
}

function initializeDirections(map) {
    var rendererOptions = {
        region: DEFAULT_REGION,
        draggable: getMapsDraggable()
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
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
