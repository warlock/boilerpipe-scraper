"use strict";
var spawn = require('child_process').spawn;

module.exports = function (url, proxy, callback) {
  var query = spawn('java', ["-jar", __dirname + "/boilerpipe.jar", url, proxy]);
    var text = ""
    var err = null
    query.stdout.setEncoding('utf8')

    query.stdout.on('data', function (data) {
        text = data
    })

    query.stderr.on('data', function (data) {
        err = data
    })

    query.on('close', function (code) {
       callback(text, err)
    })
}
