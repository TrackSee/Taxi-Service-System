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

    var origin = latLngArr.shift();
    var dest = latLngArr.pop();
    //var waypoints = [{location: latLngArr[latLngArr.length / 2], stopover: false }];
    var waypoints = [];

    // creating path
    initializeDirections(map);
    calcRoute(origin, dest, waypoints);
    google.maps.event.addListener(directionsDisplay, 'directions_changed', function(){
        updateAddresses(directionsDisplay.getDirections().routes[0]);
        updatePrice();
    });
    updateAddresses(directionsDisplay.getDirections().routes[0]);
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

/**
 * getTaxiPricePerKm
 * @returns {number}
 */
function getTaxiPriceEntity() {
    var date = $(".form_datetime").datetimepicker('getDate');

    // price parameters
    var isWeekEnd = date.toString().indexOf('Sun') != -1 || date.toString().indexOf('Sat') != -1;
    var isNight   = date.getHours() > 22 || date.getHours() < 6;
    var carCategory = $('#carCategory').val();

    return getPriceList().filter(function(e){
        return carCategory == e.carCategory && isWeekEnd ==  e.weekend && isNight == e.nightTariff;
    })[0];
}

function getAdditionalOptionsMultiplier() {
    var multiplier = 1;
    if ($('#animalTransportationCheckbox').is(":checked"))
        multiplier *= getAnimalTransportationMultiplier();
    return multiplier;
}

/**
 * updatePrice
 * Updates order price when user change route addresses
 */
function updatePrice() {
    var service = $('#service').val();
    //var isTimePriced = service == 'CELEBRATION_TAXI' && service == 'TAXI_FOR_LONG_TERM';
    //isTimePriced? priceEntity.pricePerMin :
    var priceEntity = getTaxiPriceEntity();
    var price = priceEntity.pricePerKm;

    var distance = getRoutesData().reduce(function(pv, cv) {return pv + cv.distance; }, 0);
    // taxi for very short distance has constant min price
    var businessDistance = (distance > getMinDistance()) ? distance : getMinDistance();
    var totalCost = price * businessDistance * getAdditionalOptionsMultiplier();
    $('#price').val("$ " + totalCost.toFixed(2));
}