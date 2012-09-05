define(['text!/tmpl/tmpl.html'], function (tmpl) {
    return Backbone.View.extend({
        el: '.DashboardPage',
        template: Handlebars.compile($(tmpl).find('#sprints-template').html()),
        createTemplate: Handlebars.compile($(tmpl).find('#create-template').html()),
        events: {
            'click #newsprint': 'create',
            'click .deletesprint': 'delete'
        },
    	initialize: function() {
    		this.collection.on('reset sync remove', this.render, this);
    	},
    	render: function() {
            this.$el.empty();

            var view = this;
    		this.collection.each(function(sprint) {
    			view.$el.append(view.template(sprint.toJSON()));
    		});

            this.$el.append(view.createTemplate);
    	},
        create: function() {
            var name = this.$el.find('input[type=text]').val();
            this.collection.create({name:name});
        },
        delete: function(event) {
            var story = $(event.target).closest('.DashboardStory');
            
            var view = this;
            $(story.parent().children()).each(function(index, item) {
                if($(item)[0] == story[0]) {
                    var model = view.collection.at(index);
                    model.destroy({wait: true});
                }
            });
        }
    })
});
