"use strict";

//index.js
const express = require('express')
const https = require('https')
const port = 3000;

const app = express()

app.listen(port, function () {
   console.log('Example app listening on port 3000!')
});

app.get('/create', function (req, res) {
  mapDemo();
  getLocationWiki("Dallas");
});


var mapDemo= function(){
  const url = "https://maps.googleapis.com/maps/api/geocode/json?address=Dallas";
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

var getLocationWiki = function(location){
  const url = "https://en.wikipedia.org/wiki/" + location;
  https.get(url, res => {
    res.setEncoding("utf8");
    let body = "";
    res.on("data", data => {
      body += data;
    });
    res.on("end", () => {
      console.log(body);
    });
  });
}
