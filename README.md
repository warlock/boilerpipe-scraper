##BOILERPIPE
Boilerpipe with modifications. In unique instance:
- Request in multiple  proxy's.
- Checks size of text and make multiple filters for get bigger.

```javascript
var boiler = require('boilerpipe-scraper')

boiler("http://www.spellbook.io", "192.168.0.1:3128 192.168.0.2:3128", (err, text) => {
  if (err) console.log(err)
  else console.log(text)
})
```
