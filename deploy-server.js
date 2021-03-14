const express = require("express");
const https = require("https");
const fs = require("fs");
const history = require("connect-history-api-fallback");
const bodyParser = require('body-parser');

const enableHttps = false;

const ssloptions = {}

if (enableHttps) {
  ssloptions.cert =  fs.readFileSync("./ssl/sportsstore.crt");
  ssloptions.key = fs.readFileSync("./ssl/sportsstore.pem");
}

const app = express();

app.use(bodyParser.json());
app.use(history());
app.use("/", express.static("./dist/SportsStore"));

app.listen(3000,
  () => console.log("HTTP Server running on port 3000"));

if (enableHttps) {
  https.createServer(ssloptions, app).listen(443,
    () => console.log("HTTPS Server running on port 443"));
} else {
  console.log("HTTPS disabled")
}
