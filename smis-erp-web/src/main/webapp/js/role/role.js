$(function(){
	var $roleManager = $('#roleManager');
	$roleManager.find("#roleList").dataTable({
        debug: true,
        check: true,
        pageCapacity:15,
        loading:false,
        oddEven:false,
        url: "data.php",
        style: {"font-size": "12px", "width": "800px"},
        align:"center",
        ButtonStyle:{fontColor:"#ffffff",backgroundColor:"#10AA9C"},
        columns: [
            {ColumnName: "roleName", title: "角色名称", width: 30},
            {ColumnName: "createTime", title: "创建时间", width: 500},
            {ColumnName: "remark", title: "备注", width: 500},
            {title: "查看", button: "show", buttonName: "查看", width: 50},
            {title: "编辑", button: "edit", buttonName: "编辑", width: 50},
            {title: "删除", button: "del", buttonName: "删除", width: 50}
        ],
        Click: function (row) {
            alert("单击："+row.id);
        },
        doubleClick: function (row) {
            alert("双击："+row.id);
        }
        ,
        editClick: function (row) {
            alert("自定义编辑："+row.id);
        }
        ,
        delClick: function (row) {
            alert("自定义删除："+row.id);
        },
        showClick: function (row) {
            alert("自定义查看："+row.name);
        }
    });
});