define(['text!/tmpl/tmpl.html'], function (tmpl) {
    return Backbone.View.extend({
        el: '.DashboardPage',
        template: Handlebars.compile($(tmpl).find('#sprint-template').html()),
        titleTemplate: Handlebars.compile($(tmpl).find('#title-template').html()),
    	initialize: function() {
    		this.model.on('change', this.render, this);
    	},
    	render: function() {
            this.$el.empty();
            this.$el.append(this.titleTemplate(this.model.toJSON()));

            var view = this;
            _.each(this.model.get('tasks'), function(task) {
                view.$el.append(view.template(task));
            });
    	}
    })
});