var exec = require('child_process').exec;

module.exports = function (url, proxy, callback) {
  var proc;
  if (typeof proxy === 'string') proc = exec('java -jar ' + __dirname + '/boilerpipe.jar ' + url + ' \"' + proxy + '\"');
  else {
    callback = proxy;
    proc = exec('java -jar ' + __dirname + '/boilerpipe.jar ' + ' \"' + url + '\"');
  }

  var timeout = setTimeout(function () {
    callback("KILL", "");
    proc.kill('SIGHUP');
  }, 60000);
  var list = [];
  var error = [];
  proc.stdout.setEncoding('utf8');
  proc.stdout.on('data', function (res) {
    clearTimeout(timeout);
    list.push(res);
  });

  proc.stderr.on('error', function (err) {
    clearTimeout(timeout);
    error.push(err);
  });

  proc.stdout.on('end', function () {
    clearTimeout(timeout);
    callback(error.join(""), list.join(""));
  });
};
