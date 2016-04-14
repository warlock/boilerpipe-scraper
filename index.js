"use strict";
var spawn = require('child_process').spawn;

module.exports = function (url, proxy, callback) {
  var query = spawn('java', ["-classpath", __dirname + "/boiler_pipe/dist/boilerpipe-1.2.0.jar:" + __dirname + "/boiler_pipe/dist/:" + __dirname + "/boiler_pipe/lib/nekohtml-1.9.13.jar:" + __dirname + "/boiler_pipe/lib/xerces-2.9.1.jar", "de.l3s.boilerpipe.demo.Oneliner", url, proxy]);
        query.stdout.setEncoding('utf8')

        query.stdout.on('data', function (data) {
	    callback(data)
        })

	query.stderr.on('data', function (data) {
		callback("ERROR" + data)
	})

        query.on('close', function (code) {
        })
}
