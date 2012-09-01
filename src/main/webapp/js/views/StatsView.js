define(['text!/tmpl/tmpl.html'], function (tmpl) {
    return Backbone.View.extend({
        el: '.stats',
        template: Handlebars.compile($(tmpl).find('#stats-template').html()),
    	initialize: function() {
            this.$el.html(this.template);
    	}
    })
});