"use strict";
var spawn = require('child_process').spawn;
var route = "/home/josep/Documentos/node/NouBoiler/boiler_pipe/"

module.exports = function (url, proxy, callback) {
  var query = spawn('java', ["-classpath", route + "dist/boilerpipe-1.2.0.jar:" + route + "dist/:" + route + "lib/nekohtml-1.9.13.jar:" + route + "lib/xerces-2.9.1.jar", "de.l3s.boilerpipe.demo.Oneliner", url, proxy]);
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



var url = "http://www.diariosur.es/deportes/futbol/liga-campeones/201604/12/zidane-futbol-siempre-sufre-20160412232323-rc.html"
var url = "http://www.diariosur"

boiler(url, (resp) => {
	console.log(resp)
});



console.log("java -classpath " + ruta + "dist/boilerpipe-1.2.0.jar:" + ruta + "dist/:" + ruta + "lib/nekohtml-1.9.13.jar:" + ruta + "lib/xerces-2.9.1.jar de.l3s.boilerpipe.demo.Oneliner " + url)
