(function () {
   Ext.onReady(function() {
	Ext.QuickTips.init();
	var loginForm = new Ext.form.Panel({
		title : '登录', //窗口标题  
		frame : true,
		buttonAlign:'center',
		width : 300,
		renderTo : Ext.get("login"),
		bodyStyle : 'background: White;',
		border : false, 
		fieldDefaults : {
			msgTarget : 'side',
			labelWidth : 50
		},
		defaultType : 'textfield',
		defaults : {
			anchor : '100%'
		},
		items : [ 
		{
			fieldLabel : '用户名',
			name : 'username',
		}, 
		{
			name : 'password',
			fieldLabel : '密   码',
			inputType : 'password'
		} ],
		buttons : [ {
			text : '登录',
			handler : login
		}, {
			text : '重置',
			handler : reset
		} ]
	})

	function login() {
		loginForm.getForm().submit({
			clientValidation : true, //进行客户端验证  
			url : 'login', //Action  
			method : 'post', //请求方式  
			success : function(form, action) {
				Ext.Msg.alert('登陆成功', action.result.msg);

				//登陆成功后 的操作 如页面跳转等  
				//document.location="success.jsp";    
			},
			failure : function(form, action) {
				Ext.Msg.alert('登陆失败', action.result.msg);
				//document.location="fail.jsp"; 
			}
		})
	}
	function reset() {
		loginForm.form.reset();
	}
}); 
})();

