"use strict";
var tourService = function(){
  const url = "https://maps.googleapis.com/maps/api/geocode/json?address=Dallas";

  var  mapDemo = function(){
    https.get(url, res => {
      res.setEncoding("utf8");
      let body = "";
      res.on("data", data => {
        body += data;
      });
      res.on("end", () => {
        body = JSON.parse(body);
        console.log(
          `City: ${body.results[0].formatted_address} -`,
          `Latitude: ${body.results[0].geometry.location.lat} -`,
          `Longitude: ${body.results[0].geometry.location.lng}`
        );
      });
    });
  }

  var getLocationWiki
}
