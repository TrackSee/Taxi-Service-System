/**
 * Google maps integration for order page.
 * Loaded using AJAX after document is ready.
 *
 * @author Ruslan Gunavardana
 */
var directionsDisplay;
var directionsService;
function initializeMaps() {
    var mapOptions = { mapTypeId: google.maps.MapTypeId.ROADMAP };
    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    new google.maps.TrafficLayer().setMap(map);
    
    // poly validation
    var latLngArr = google.maps.geometry.encoding.decodePath(encodedRoute);
    if (latLngArr.length == 0) return;
    
    var waypoints = [];
    latLngArr.forEach(function(e){ waypoints.push({e.stopover: false}) });

    // appending directions
    initializeDirections(map);
    calcRoute(latLngArr[0], []);
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function(){
                updateAddresses(directionsDisplay.getDirections().routes[0]);
                updatePrice();
    });
}

function initializeDirections(map) {
    var rendererOptions = {
        draggable: true,
        region: DEFAULT_REGION
    };
    directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
    directionsService = new google.maps.DirectionsService();
    directionsDisplay.setMap(map);
}

/**
 * calcRoute
 * Sends a request for building new path to Google Directions Service.
 *
 * @param destination google.maps.LatLng
 * @param waypoints google.maps.DirectionsWaypoint []
 */
function calcRoute(destination, waypoints) {
    var route = directionsDisplay.getDirections().routes[0];
    var request = {
        origin: getOrigin(),
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
    $('#destination').val(route.legs[route.legs.length - 1].end_address);
}

/**
 * updateRoute
 * Geocodes entered addresses and changes the route on the map.
 */
function updateRoute() {
    calcRoute($('#origin').val(), $('#destination').val(), []);
}

/**
 * updatePrice
 * Updates order price when user change route addresses
 */
function updatePrice() {
    var distance = getRoutesData().reduce(function(pv, cv) {return pv + cv.distance; }, 0);
    // taxi for very short distance has constant min price
    var businessDistance = (distance > getMinDistance()) ? distance : getMinDistance();
    var price = getTaxiPricePerKm() * businessDistance;
    $('#price').val(price + " UAH");
}
