var express = require('express')
  , resource = require('express-resource')
  , app = express.createServer()
  , port = 9998
  , dbname = 'xebia';

var db = require('mongojs').connect(dbname, ['sprints']);

app.use(express.bodyParser());
app.use(express.static(__dirname));

app.resource('resource/sprint', {
  // GET /sprints
  index: function(req, res) {
  },
  // POST /sprints
  create: function(req, res) {
  },
  // GET /sprints/id
  show: function(req, res) {
  },
  // PUT /sprints/id
  update: function(req, res) {
  },
  // DELETE /sprints/id
  destroy: function(req, res) {
  }
});

app.listen(port);