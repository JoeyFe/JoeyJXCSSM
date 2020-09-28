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
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
        </div>
        <div class="wu-toolbar-search">
            <label>商品名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>所属供应商:</label>
            <select id="search-supplier" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${supplierList }" var="supplier">
            		<option value="${supplier.id }">${supplier.name }</option>
            	</c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
           <tr>
                <td width="80" align="right">商品名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写商品名称'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属供应商:</td>
                <td>
                	<select name="supplierId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择所属供应商'">
		                <c:forEach items="${supplierList }" var="supplier">
		            		<option value="${supplier.id }">${supplier.name }</option>
		            	</c:forEach>
		            </select>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">商品产地:</td>
                <td><input type="text" name="place" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品规格:</td>
                <td><input type="text" name="spec" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品包装:</td>
                <td><input type="text" name="pk" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品单位:</td>
                <td><input type="text" name="unit" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="80" align="right">商品进价:</td>
                <td><input type="text" name="price" class="wu-text easyui-numberbox easyui-validatebox" data-options="required:true,min:0,precision:2, missingMessage:'请填写商品进价'" /></td>
            </tr>
             <tr>
                <td width="80" align="right">商品售价:</td>
                <td><input type="text" name="sellprice" class="wu-text easyui-numberbox easyui-validatebox" data-options="required:true,min:0,precision:2, missingMessage:'请填写商品售价'" /></td>
            </tr>
            <tr>
                <td align="right">商品描述:</td>
                <td><textarea name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
       <table>
            <tr>
                <td width="80" align="right">商品名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写商品名称'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属供应商:</td>
                <td>
                	<select name="supplierId" id="edit-supplierId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择所属供应商'">
		                <c:forEach items="${supplierList }" var="supplier">
		            		<option value="${supplier.id }">${supplier.name }</option>
		            	</c:forEach>
		            </select>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">商品产地:</td>
                <td><input type="text" id="edit-place" name="place" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品规格:</td>
                <td><input type="text" id="edit-spec" name="spec" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品包装:</td>
                <td><input type="text" id="edit-pk" name="pk" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="60" align="right">商品单位:</td>
                <td><input type="text" id="edit-unit" name="unit" class="wu-text" /></td>
            </tr>
            <tr>
                <td width="80" align="right">商品进价:</td>
                <td><input type="text" id="edit-price" name="price" class="wu-text easyui-numberbox easyui-validatebox" data-options="required:true,min:0,precision:2, missingMessage:'请填写商品进价'" /></td>
            </tr>
            <tr>
                <td width="80" align="right">商品售价:</td>
                <td><input type="text" id="edit-sellprice" name="sellprice" class="wu-text easyui-numberbox easyui-validatebox" data-options="required:true,min:0,precision:2, missingMessage:'请填写商品售价'" /></td>
            </tr>
            <tr>
                <td align="right">商品描述:</td>
                <td><textarea name="remark" id="edit-remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	
	
	/**
	*  添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'../product/add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					//$("#add-content").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'../product/edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','编辑成功！','info');
					//$("#add-content").val('');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				$.ajax({
					url:'../product/delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
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
	
	function openEdit(){
		//$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "编辑商品信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            	$("#edit-id").val(item.id);
            	$("#edit-supplierId").combobox('setValue',item.supplierId);
            	$("#edit-name").val(item.name);
            	$("#edit-place").val(item.place);
            	$("#edit-pk").val(item.pk);
            	$("#edit-spec").val(item.spec);
            	$("#edit-unit").val(item.unit);
            	$("#edit-price").numberbox('setValue',item.price);
            	$("#edit-sellprice").numberbox('setValue',item.sellprice);
            	$("#edit-remark").val(item.remark);
            }
        });
	}
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		var supplierId = $("#search-supplier").combobox('getValue');
		if(supplierId != -1){
			option.supplierId = supplierId;
		}
		$('#data-datagrid').datagrid('reload',option);
		
	});
	
	
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'../product/list',
		rownumbers:true,
		singleSelect:true,
		pageSize:50,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'商品名称',width:100,sortable:true},
			{ field:'supplierId',title:'所属供应商',width:100,formatter:function(value,index,row){
				var supplierList = $("#search-supplier").combobox('getData');
				for(var i = 0; i < supplierList.length; i++){
					if(supplierList[i].value == value)
						return supplierList[i].text;
				}
				return value;
			}},
			{ field:'place',title:'产地',width:100,sortable:true},
			{ field:'spec',title:'规格',width:100,sortable:true},
			{ field:'pk',title:'包装',width:100,sortable:true},
			{ field:'unit',title:'单位',width:100,sortable:true},
			{ field:'price',title:'进货价格',width:100,sortable:true},
			{ field:'sellprice',title:'销售价格',width:100,sortable:true},
			{ field:'remark',title:'商品描述',width:200,sortable:true}
		]]
	});
	
	
</script>
</body>
