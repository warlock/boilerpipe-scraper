"use strict";
var spawn = require('child_process').spawn;

module.exports = function (url, proxy, callback) {
    if (typeof proxy === 'function') callback = proxy
    var query = spawn('java', ["-jar", __dirname + "/boilerpipe.jar", url, proxy])
    var text, proxyres = ""
    var err = null
    query.stdout.setEncoding('utf8')
    var n = 0;
    query.stdout.on('data', function (data) {
        if (n === 0) text = data
        else if (data !== 0) proxyres =+ " " + data
        n++
    })

    query.stderr.on('data', function (data) {
        err = data.toString()
    })

    query.on('close', function () {
       callback(err, text, proxyres)
    })
}
