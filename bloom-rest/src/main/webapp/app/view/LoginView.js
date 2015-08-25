/**
 * Created by Rono on 7/30/2015.
 */
Ext.define('BloomUI.view.LoginView', {

    extend: 'Ext.window.Window',
    requires: [],
    alias: 'widget.loginView',
    autoShow: true,

    height: 170,
    width: 360,
    layout: {
        type: 'fit'
    },
    title: 'Login View',
    closeAction: 'hide',
    closeable: false,
    items: [
        {
            xtype: 'form',
            frame: false,
            bodyPadding: 15,
            defaults: {
                xtype: 'textfield',
                achor: '100%',
                labelWidth: '60%',
                allowBlank: false,
                vtype: 'alphanum',
                minLength: 3,
                msgTarget: 'Text Field must have at least 3 characters'
            },
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        {
                            xtype: 'button',
                            itemId: 'submitButton',
                            name: 'Submit'
                        },
                        {
                            xtype: 'button',
                            itemId: 'cancelButton',
                            name: 'Cancel'
                        }
                    ]
                }
            ],
            items: [
                {
                    name: 'User',
                    fieldLabel: 'UserName',
                    maxLength: 30
                },
                {
                    name: 'Password',
                    fieldLabel: 'Password',
                    inputType: 'password',
                    maxLength: 15
                }
            ]
        }
    ]
});
