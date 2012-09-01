define(['text!/tmpl/tmpl.html'], function (tmpl) {
    return Backbone.View.extend({
        el: '.DashboardPage',
        template: Handlebars.compile($(tmpl).find('#sprint-template').html()),
        titleTemplate: Handlebars.compile($(tmpl).find('#title-template').html()),
    	initialize: function() {
    	},
    	render: function() {
    	}
    })
});