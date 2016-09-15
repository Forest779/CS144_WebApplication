var geocoder;
var map;
var add="USA";

function initialize() 
{
  // add= "<%=location %>";
  // add='San Bernardino, CA';
  geocoder = new google.maps.Geocoder();
  var latlng = new google.maps.LatLng(53.3496, -6.3263);
  var mapOptions = 
  {
    zoom: 8,
    center: latlng
  }
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  codeAddress(add);//call the function
}

function codeAddress(address) 
{
  geocoder.geocode( {address:address}, function(results, status) 
  {
    if (status == google.maps.GeocoderStatus.OK) 
    {
      // alert('Geocode was  successful for the following reason: ' + address)
      map.setCenter(results[0].geometry.location);//center the map over the result
      //place a marker at the location
      var marker = new google.maps.Marker(
      {
          map: map,
          position: results[0].geometry.location
      });
    } else {
      // alert('Geocode was not successful for the following reason: ' + address);
      codeAddress('pacific ocean')
   }
  });
}
google.maps.event.addDomListener( window, 'load', initialize );