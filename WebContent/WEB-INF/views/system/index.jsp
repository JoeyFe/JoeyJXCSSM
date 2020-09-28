<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/wu.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/icon.css" />
<script type="text/javascript" src="../resources/admin/easyui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1>零售销售系统</h1>
        </div>
        <div class="wu-header-right">
        	<p><strong class="easyui-tooltip" title="2条未读消息">${admin.username}</strong>，欢迎您！</p>
            <p><a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a href="../system/login">安全退出</a></p>
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	<c:if test="${admin.role == 'admin'}">
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
           
            <div title="商品管理" data-options="iconCls:'icon-box'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../product/list" iframe="1">商品管理</a></li>
                </ul>
            </div>
            <div title="供应商" data-options="iconCls:'icon-vcard'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../supplier/list" iframe="1">供应商管理</a></li>
                </ul>
            </div> 
            <div title="进货管理" data-options="iconCls:'icon-application-form-edit'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../order_in/list" iframe="1">订货单管理</a></li>              
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../purchase_in/list" iframe="1">采购单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../stock_in/list" iframe="1">入库单管理</a></li>
                	
                </ul>
            </div>
            <div title="订单管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../sell_out/list" iframe="1">销售单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../deliver_out/list" iframe="1">出库单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../send_out/list" iframe="1">发货单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../sell_back/list" iframe="1">退货单管理</a></li>
                </ul>
            </div>
            <div title="库存管理" data-options="iconCls:'icon-bricks'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                 <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../stock/list" iframe="1">库存查看</a></li> 	
                </ul>
            </div>
            
        </div>
   </c:if>
   <c:if test="${admin.role == 'saleman'}">
   	<div class="easyui-accordion" data-options="border:false,fit:true"> 
            <div title="订单管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../sell_out/list" iframe="1">销售单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../deliver_out/list" iframe="1">出库单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../send_out/list" iframe="1">发货单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../sell_back/list" iframe="1">退货单管理</a></li>
                </ul>
            </div>
            <div title="库存管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                 <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../stock/list" iframe="1">库存查看</a></li> 	
                </ul>
            </div>
            
        </div>
   </c:if>
   <c:if test="${admin.role == 'purchaseman'}">
   <div class="easyui-accordion" data-options="border:false,fit:true"> 
           
            <div title="商品管理" data-options="iconCls:'icon-box'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../product/list" iframe="1">商品管理</a></li>
                </ul>
            </div>
            <div title="供应商" data-options="iconCls:'icon-vcard'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../supplier/list" iframe="1">供应商管理</a></li>
                </ul>
            </div> 
            <div title="进货管理" data-options="iconCls:'icon-application-form-edit'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../order_in/list" iframe="1">订货单管理</a></li>              
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../purchase_in/list" iframe="1">采购单管理</a></li>
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../stock_in/list" iframe="1">入库单管理</a></li>
                	
                </ul>
            </div>
            <div title="库存管理" data-options="iconCls:'icon-cart'" style="padding:5px;">  	
    			<ul class="easyui-tree wu-side-tree">
                 <li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="../stock/list" iframe="1">库存查看</a></li> 	
                </ul>
            </div>
            
        </div>
   </c:if>
    </div>	
       
    <!-- end of sidebar -->    
    <!-- begin of main -->
    <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true">  
            <div title="首页" data-options="href:'welcome',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
        </div>
    </div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy; Joey All Rights Reserved
    </div>
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		/**
		* Name 载入树形菜单 
		*/
		$('#wu-side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#wu-datagrid').datagrid('reload');
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
	</script>
</body>
</html>
