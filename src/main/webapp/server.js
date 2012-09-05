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
    db.sprints.find({}, function(err, sprints) {
      res.send(sprints);
    });
  },
  // POST /sprints
  create: function(req, res) {
    db.sprints.save(req.body, function(err, sprint) {
      res.send(sprint, 201);
    });
  },
  // GET /sprints/id
  show: function(req, res) {
    db.sprints.findOne({_id: db.ObjectId(req.params.sprint)}, function(err, sprint) {
      res.send(sprint);
    });
  },
  // PUT /sprints/id
  update: function(req, res) {
    db.sprints.update({_id: db.ObjectId(req.params.sprint)}, {$set: req.body}, function(err, sprint) {
      res.send(sprint);
    });
  },
  // DELETE /sprints/id
  destroy: function(req, res) {
    db.sprints.remove({_id: db.ObjectId(req.params.sprint)}, function(err, sprint) {
      res.send();
    });
  }
});

app.listen(port);
