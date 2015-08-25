/**
 * Created by Rono on 7/29/2015.
 */
//var splashScreen;
Ext.application({

    name: "BloomUI",

    requires: ['BloomUI.view.LoginView'],
    views: ['LoginView'],
    models: [],

    init: function () {

        var splashScreen = Ext.getBody().mask('Loading Message', 'splashScreen');
        console.log("Starting init...");
    },

    launch: function () {

        console.log("Application Started");
        var task = new Ext.util.DelayedTask(function () {

            // fade out the body mask
            splashScreen.fadeOut({
                duration: 1000,
                remove: true,
                useDisplay: true
            });

            // fade out the message
            splashscreen.next().fadeOut({
                duration: 1000,
                remove: true,
                useDisplay: true
            });
        });

        task.delay(2000, function () {
            Ext.getBody().unmask();
        }, this, null);
        Ext.widget('loginView');
    }
});