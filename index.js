const exec = require('child_process').exec

module.exports = (url, proxy, callback) => {
  var proc
  if (typeof proxy === 'string') proc = `java -jar ${__dirname}/boilerpipe.jar ${url} "${proxy}"`
  else {
    callback = proxy
    proc = `java -jar ${__dirname}/boilerpipe.jar "${url}"`
  }
  exec(proc, { timeout : 60000 }, (error, stdout, stderr) => {
    if (error) callback(error)
    else callback(null, stdout)
  })
}
