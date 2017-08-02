## BOILERPIPE
Boilerpipe with modifications. In unique instance:
- Request in multiple  proxy's.
- Checks size of text and make multiple filters for get bigger.
(Request timeout configurable)


### Load
```javascript
const boiler = require('boilerpipe-scraper')
```

### Basic usage
```javascript
boiler("http://www.js.gl", (err, text) => {
  if (err) console.log(err)
  else console.log(text)
})
```

### Usage with proxies
```javascript
boiler("http://www.js.gl", "192.168.0.1:3128 192.168.0.2:3128", (err, text) => {
  if (err) console.log(err)
  else console.log(text)
})
```

### That lib requires the Java VM 

## License
```
The MIT License (MIT) Copyright (c) 2015 Josep Subils (js@js.gl)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
