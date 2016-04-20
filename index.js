"use strict";
var exec = require('child_process').exec;

module.exports = function (url, proxy, callback) {
  if (typeof proxy === 'string') var proc = exec('java -jar ' + __dirname + '/boilerpipe.jar ' + url + ' \"' + proxy + '\"')
  else {
    callback = proxy
    var proc = exec('java -jar ' + __dirname + '/boilerpipe.jar ' + ' \"' + urlv+ '\"')
  }

  var list = []
  var error = []
  proc.stdout.setEncoding('utf8');
  proc.stdout.on('data', function (res) {
    list.push(res)
  })

  proc.stderr.on('error', function (err) {
    error.push(err)
  })

  proc.stdout.on('end', function () {
    callback(error.join(""), list.join(""))
  })
}
