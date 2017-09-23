"use strict";

//index.js
const express = require('express');
const https = require('https');
const fs = require('fs');
const port = 3000;

const app = express()

app.listen(port, function () {
   console.log('Example app listening on port 3000!')
});

app.get('/create', function (req, res) {
  getLocationDetails("Dallas");
  getLocationWiki("Dallas");
});


var getLocationDetails= function(location){
  const url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location;
  https.get(url, res => {
    res.setEncoding("utf8");
    let body = "";
    res.on("data", data => {
      body += data;
    });
    res.on("end", () => {
      body = JSON.parse(body);
      displayLocationInfo(body);
    });
  });
}

var displayLocationInfo = function(body){
  console.log(
    `City: ${body.results[0].formatted_address} -`,
    `Latitude: ${body.results[0].geometry.location.lat} -`,
    `Longitude: ${body.results[0].geometry.location.lng}`
  );
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
      saveWikiAsFile(body);
    });
  });
}

var saveWikiAsFile = function(body){
  fs.writeFile("test", body, function(err) {
    if(err) {
        return console.log(err);
    }

    console.log("The file was saved!");
  });
}
