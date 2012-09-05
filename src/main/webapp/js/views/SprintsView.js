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
    	},
    	render: function() {
    	},
        create: function() {
        },
        delete: function(event) {
        }
    })
});