require.config({
    //baseUrl: '',
    paths: {
        'app': 'app',
        'angular': 'base/angular',
        'router': 'base/angular-ui-router',
        'cookies': 'base/angular-cookies.min',
        'jQuery': 'base/jquery-1.7.2.min',
        'layer': 'base/layer',
        'select': 'base/select',
    },
    shim: {
        'router': {
            deps: ['angular']
        },
        'cookies':{
        	deps: ['angular']
        },
        'layer':{
        	deps: ['jQuery']
        },
        'select':{
        	deps: ['jQuery']
        }
    }
});
// 手动初始化myModule模块
require(['app','cookies','jQuery','layer','select'],function(){
    angular.bootstrap(document, ['webapp']);
})