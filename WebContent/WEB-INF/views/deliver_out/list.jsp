<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/wu.css" />
<link rel="stylesheet" type="text/css" href="../resources/admin/easyui/css/icon.css" />
<script type="text/javascript" src="../resources/admin/easyui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加出库单</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cart-edit" onclick="openEdit()" plain="true">出库</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-eye" onclick="openView()" plain="true">查看出库详单</a>
        </div>
        <div class="wu-toolbar-search">
            <label>操作员:</label><input id="search-operator" class="wu-text" style="width:100px">
            <label>快递公司	:</label>
            <select id="search-payType" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">顺丰</option>
            	<option value="1">中通</option>
            	<option value="2">圆通</option>
            	<option value="3">申通</option>
            	<option value="4">京东</option>
            	<option value="5">苏宁</option>
            </select>
            <label>状态:</label>
            <select id="search-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">未出库</option>
            	<option value="1">已出库</option>
            </select>
            <label>金额:</label>
            <input id="search-minMoney" class="wu-text numberbox" data-options="min:0,precision:2" style="width:100px">
            	至
            <input id="search-maxMoney" class="wu-text numberbox" data-options="min:0,precision:2" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- 添加商品弹框 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:650px; padding:10px;">
	<div>
	<span >操作员: </span><input id="operatornum" class="wu-text" style="width:100px" value="${admin.username}" contenteditable="false">
	<span >订单编号: </span><input id="ordernum" class="wu-text" style="width:200px"  contenteditable="false">
	</div>
		<div>
	<span >顾客名: </span><input id="customid" class="wu-text" style="width:100px"  contenteditable="false">
	<span >电话: </span><input id="customphone" class="wu-text" style="width:150px"  contenteditable="false">
	<span >地址: </span><input id="customaddr" class="wu-text" style="width:150px"  contenteditable="false">
	</div>
	<table id="selected-product-datagrid" class="easyui-datagrid" style="width:616px;height:356px" toolbar="#select-product-btn">
	<thead>
		<tr>
			<th field="id" width="151px">商品ID</th>
			<th field="name" width="151px">商品名称</th>
			<th field="price" width="151px">商品价格</th>
			<th field="productNum" width="151px" editor="{type:'numberbox',options:{min:1,precision:0}}">商品数量</th>
		</tr>
	</thead>
</table>
<div id="select-product-btn">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="selectProduct()">添加商品</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeProduct()">删除</a>
</div>
<form id="add-form" method="post">
        <table>
            <tr>
                <td  width="60px" align="right">快递公司:</td>
                <td>
                	<select name="payType" id="add-payType" class="easyui-combobox" panelHeight="auto" style="width:500px" data-options="required:true, missingMessage:'请选择支付方式'">
		            	<option value="0" selected>顺丰</option>
            			<option value="1">中通</option>
            			<option value="2">圆通</option>
            			<option value="3">申通</option>
            			<option value="4">京东</option>
            			<option value="5">苏宁</option>
		            </select>
                </td>
                </tr>
               <tr>
                <td  align="right">是否出库:</td>
                <td>
                	<select name="status" id="add-status" class="easyui-combobox" panelHeight="auto" style="width:500px" data-options="required:true, missingMessage:'请选择状态'">
		            	<option value="0" selected>不出库</option>
		            	<option value="1">出库</option>
		            </select>
                </td>
            </tr>
            <tr>
            	<td align="right">关联销售单:</td>
            	<td>
            	<textarea name="selloutid" id="associate-id" rows="1" class="wu-textarea" style="width:490px"></textarea>
            	</td>
            	<td>
            	<a href="#" class="easyui-linkbutton" iconCls="icon-eye" onclick="openAssociate()" plain="true"></a>
            	</td>
            </tr>
            <tr>
                <td align="right">备注:</td>
                <td><textarea name="remark" id="add-remark" rows="6" class="wu-textarea" style="width:490px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!-- 查看商品弹窗 -->
<div id="view-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:560px; padding:10px;">
	<table id="view-detail-datagrid" class="easyui-datagrid" style="width:526px;height:356px">
	<thead>
		<tr>
			<th field="id" width="103px">ID</th>
			<th field="productName" width="103px">商品名称</th>
			<th field="price" width="103px">商品价格</th>
			<th field="productNum" width="103px">商品数量</th>
			<th field="money" width="103px">商品金额</th>
		</tr>
	</thead>
</table>
</div>
<!-- 添加选择商品弹框 -->
<div id="show-product-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:750px; padding:10px;">
	<table id="show-product-datagrid" class="easyui-datagrid" style="width:716px;height:450px">
	</table>
</div>
<!-- 添加选择销售单弹框 -->
<div id="associate-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:750px; padding:10px;">
	<table id="show-sellout-datagrid" class="easyui-datagrid" style="width:716px;height:450px">
	</table>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
/**
*  添加记录
*/
function add(){
	var selectedProducts = $("#selected-product-datagrid").datagrid('getData').rows;
	console.log(JSON.stringify(selectedProducts));
	if(selectedProducts.length <= 0){
		$.messager.alert('信息提示','请至少选择一个商品进行出库!','warning');
		return;
	}
	//为防止用户没有点击一行失去焦点保存修改的数量
	for(var i=0;i<selectedProducts.length;i++){
		$("#selected-product-datagrid").datagrid('endEdit',i);
	}
	//出库单前端，读取表单数据通过ajax异步传输到controller层
	$.ajax({
		url:'../deliver_out/add',
		dataType:'json',
		type:'post',
		data:{productList:JSON.stringify(selectedProducts),remark:$("#add-remark").val(),payType:$("#add-payType").combobox('getValue'),status:$("#add-status").combobox('getValue'),id:$("#ordernum").val(),sellOutId:$("#associate-id").val(),customId:$("#customid").val(),customPhone:$("#customphone").val(),customAddr:$("#customaddr").val()},
		success:function(data){
			switch(data.type){
			case 'success':
				$.messager.alert('信息提示','添加成功','info');
				$('#add-dialog').dialog('close');
				$('#data-datagrid').datagrid('reload');
				break;
			
			case 'outstock':
				$.messager.alert('信息提示',data.msg,'warning');
				$('#add-dialog').dialog('close');
				$('#data-datagrid').datagrid('reload');
				break;
			 default :
				$.messager.alert('信息提示',data.msg,'warning');
			 }
		}
	});
	
}
//监听行编辑事件
$("#selected-product-datagrid").datagrid({
	onClickCell:function(rowIndex, field, value){
		if(field == 'productNum'){
			$("#selected-product-datagrid").datagrid('beginEdit',rowIndex);
			return;
		}
		$("#selected-product-datagrid").datagrid('endEdit',rowIndex);
	}
});
//添加菜单的选择商品弹窗
function selectProduct(){
	$('#show-product-dialog').dialog({
		closed: false,
		modal:true,
        title: "选择商品",
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function(){
            	var item = $('#show-product-datagrid').datagrid('getSelections');
            	console.log(item);
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请至少选择一个商品信息！','info');
					return;
				}
				for(var i = 0; i < item.length; i++){
					var selectedProducts = $("#selected-product-datagrid").datagrid('getData').rows;
					var index = -1;
					for(var j = 0; j < selectedProducts.length; j++){
						if(selectedProducts[j].id == item[i].productId){
							index = j;
							break;
						}
					}
					if(index > -1){
						//说明存在，更新
						$("#selected-product-datagrid").datagrid('updateRow',{
							index:j,	
							row:{
								id:item[i].productId,
	    						name:item[i].product.name,
	    						price:item[i].product.price,
	    						productNum:parseInt(selectedProducts[j].productNum) + 1
							}
    					});
						continue;
					}
					//追加新的
					$("#selected-product-datagrid").datagrid('appendRow',{
						id:item[i].productId,
						name:item[i].product.name,
						price:item[i].product.price,
						productNum:1
					})
				}
				$('#show-product-dialog').dialog('close');
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $('#show-product-dialog').dialog('close');                    
            }
        }],
        onBeforeOpen:function(){
        	$('#show-product-datagrid').datagrid({
        		url:'../stock/list',
        		rownumbers:true,
        		singleSelect:false,
        		pageSize:20,           
        		pagination:true,
        		multiSort:true,
        		fitColumns:true,
        		idField:'id',
        		columns:[[
        			{ field:'chk',checkbox:true},
        			{ field:'name',title:'商品名称',width:100,formatter:function(value,row,index){
        				//console.log(row);
        				return row.product.name;
        			}},
        			{ field:'productNum',title:'商品数量',width:100,sortable:true},
        			{ field:'sellNum',title:'销量',width:100,sortable:true},
        			{ field:'price',title:'价格',width:100,formatter:function(value,row,index){
        				//console.log(row);
        				return row.product.price;
        			}},
        			{ field:'remark',title:'商品描述',width:200,formatter:function(value,row,index){
        				//console.log(row);
        				return row.product.remark;
        			}},
     
        		]]
        	});
        }
    });
}
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {operator:$("#search-operator").val()};
		var status = $("#search-status").combobox('getValue');
		var payType = $("#search-payType").combobox('getValue');
		var minMoney = $("#search-minMoney").val();
		var maxMoney = $("#search-maxMoney").val();
		if(status != -1){
			option.status = status;
		}
		if(payType != -1){
			option.payType = payType;
		}
		if(minMoney != ''){
			option.minMoney = minMoney;
			
		}
		if(maxMoney != ''){
			option.maxMoney = maxMoney;
			
		}
		$('#data-datagrid').datagrid('reload',option);
	});
	
	/**
	*打开查看窗口
	*/
	function openView(){
		//$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		console.log(item);
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要查看的数据！','info');
			return;
		}
		$('#view-dialog').dialog({
			closed: false,
			modal:true,
            title: "查看出库商品信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function(){
                	$('#view-dialog').dialog('close');
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#view-dialog').dialog('close');                 
                }
            }],
            onBeforeOpen:function(){
            	$("#view-detail-datagrid").datagrid('loadData',{total:0,rows:[]});
            	var productList = item.deliverOutDetailList;
            	console.log(productList);
            	for(var i = 0; i < productList.length; i++){
            		$("#view-detail-datagrid").datagrid('appendRow',{
            			id:productList[i].pid,
            			productName:productList[i].productName,
            			price:productList[i].price,
            			productNum:productList[i].productNum,
            			money:productList[i].money
            		});
            	}
            }
        });
	}
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		var ordernum=document.getElementById('ordernum');
		ordernum.value=GetDateNow();
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加商品信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#add-form input").val('');
            }
        });
	}
	/**
	* Name 打开关联窗口
	*/
	function openAssociate(){

		$('#associate-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加商品信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function(){
                	var item = $('#show-sellout-datagrid').datagrid('getSelections');
    				if(item == null || item.length == 0){
    					$.messager.alert('信息提示','请至少选择一个订货单！','info');
    					return;
    				}
    				var productLists = item[0].sellOutDetailList;
                	console.log(productLists);
                	for(var i = 0; i < productLists.length; i++){
                		$("#selected-product-datagrid").datagrid('appendRow',{
                			id:productLists[i].pid,
                			name:productLists[i].productName,
                			price:productLists[i].price,
                			productNum:productLists[i].productNum,
                		});
                	}
                	$("#associate-id").val(item[0].id);//item编号要改
                	$("#customid").val(item[0].customId);
                	$("#customphone").val(item[0].customPhone);
                	$("#customaddr").val(item[0].customAddr);
    				$('#associate-dialog').dialog('close');
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#associate-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$('#show-sellout-datagrid').datagrid({
            		url:'../sell_out/list',
            		rownumbers:true,
            		singleSelect:true,
            		pageSize:20,           
            		pagination:true,
            		multiSort:true,
            		fitColumns:true,
            		idField:'id',
            		columns:[[
            			{ field:'chk',checkbox:true},
            			{ field:'id',title:'订单编号',width:100,sortable:true},
            			{ field:'money',title:'金额',width:100,sortable:true},
            			{ field:'payType',title:'支付方式',width:100,formatter:function(value,index,row){
            				switch(value){
            					case 0:return '现金';
            					case 1:return '银行转账';
            					case 2:return '支付宝';
            					case 3:return '微信';
            					case 4:return '支票';
            					case 5:return '其他';
            				}
            				return value;
            			}},
            			{ field:'status',title:'状态',width:100,formatter:function(value,index,row){
            				switch(value){
            					case 0:return '未支付';
            					case 1:return '已支付';
            				}
            				return value;
            			}},
            			{ field:'productNum',title:'商品数量',width:100,sortable:true},
            			{ field:'operator',title:'操作人',width:100,sortable:true},
            			{ field:'remark',title:'商品描述',width:200,sortable:true},
            			{ field:'createTime',title:'进货时间',width:200,formatter:function(value,index,row){return format(value);}}
            		]]
            	});
            }
        });
	}
	function openEdit(){
		//$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		if(item.status == 1){
			$.messager.alert('信息提示','该出库单已出库！','info');
			return;
		}
		console.log(JSON.stringify(item.deliverOutDetailList));
		$.ajax({
			url:'../deliver_out/edit',
			dataType:'json',
			type:'post',
			data:{deliverDetailList:JSON.stringify(item.deliverOutDetailList),id:item.id},
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','出库成功！','info');
					//$("#add-content").val('');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'../deliver_out/list',
		rownumbers:true,
		singleSelect:true,
		pageSize:50,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'id',title:'出库单号',width:100,sortable:true},
			{ field:'sellOutId',title:'关联销售单号',width:100,sortable:true},
			{ field:'money',title:'金额',width:100,sortable:true},
			{ field:'payType',title:'快递公司',width:100,formatter:function(value,index,row){
				switch(value){
					case 0:return '顺丰';
					case 1:return '中通';
					case 2:return '圆通';
					case 3:return '申通';
					case 4:return '京东';
					case 5:return '苏宁';
				}
				return value;
			}},
			{ field:'status',title:'状态',width:100,formatter:function(value,index,row){
				switch(value){
					case 0:return '未出库';
					case 1:return '已出库';
				}
				return value;
			}},
			{ field:'productNum',title:'商品数量',width:100,sortable:true},
			{ field:'operator',title:'操作人',width:100,sortable:true},
			{ field:'remark',title:'商品描述',width:200,sortable:true},
			{ field:'createTime',title:'出库时间',width:200,formatter:function(value,index,row){return format(value);}}
		]]
	});
	function GetDateNow(){
		// 时间戳
		var time = new Date();
		// 年
		var year= String(time.getFullYear());
		// 月
		var mouth= String(time.getMonth() + 1);
		if(mouth.length<2){
			mouth='0' + mouth;
		}
		// 日
		var day= String(time.getDate());
		if(day.length<2){
			day='0' + day;
		}
		// 时
		var hours= String(time.getHours());
		if(hours.length<2){
			hours='0' + hours;
		}
		// 分
		var minutes= String(time.getMinutes());
		if(minutes.length<2) {
			minutes='0' + minutes;
		}
		// 秒
		var seconds= String(time.getSeconds());
		if(seconds.length<2) {
			seconds='0' + seconds;
		}
		var str = String('CK'+year + mouth + day + hours + minutes + seconds)
		return str;
	}
	function add0(m){return m<10?'0'+m:m }
	function format(shijianchuo){
	//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	}
</script>
</body>
