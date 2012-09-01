define(['models/Sprint'], function (Sprint) {
    return Backbone.Collection.extend({
    	url: '/resource/sprint',
    	model: Sprint
    });
});