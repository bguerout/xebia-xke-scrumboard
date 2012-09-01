define(['views/SprintsView', 'views/SprintView', 'collections/Sprints', 'models/Sprint'], 
    function (SprintsView, SprintView, Sprints, Sprint) {
    return Backbone.Router.extend({
        routes: {
            'sprint/:id': 'sprint',
            '*path': 'sprints'
        },
        sprints: function() {
            var sprints = new Sprints();
            var sprintsView = new SprintsView({collection: sprints});
            sprints.fetch();
        },
        sprint: function(id) {
            var sprint = new Sprint({id: id});
            var sprintView = new SprintView({model: sprint});
            sprint.fetch();
        }
    });
});