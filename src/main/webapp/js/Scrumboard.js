requirejs.config({
    paths:{
        'jquery':'/lib/jquery.min',
        'backbone':'/lib/backbone.min',
        'underscore':'/lib/underscore.min',
        'less':'/lib/less.min',
        'handlebars': '/lib/handlebars.min',
        'text': '/lib/text.min',
        'router': 'Router'
    },
    shim:{
        'backbone':{ deps:['jquery', 'underscore'] },
        'router':{ deps:['backbone'] }
    }
});

require(['router','jquery','underscore','backbone','handlebars','text','less'],
    function (Router) {
        new Router();
        Backbone.history.start();
});