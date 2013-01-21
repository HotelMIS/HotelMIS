
Ext.define('Admin.Admin',{
    extend:'Ext.container.Viewport',
    id:'admin-win',
    title: '背景设置',
    modal: true,
    width: 640,
    height: 480,
    border: false,
    initComponent: function () {
        Ext.create('Ext.data.Request',{
            url:'modules/admin/layout-browser.html'
        });
        this.callParent();
    }
});