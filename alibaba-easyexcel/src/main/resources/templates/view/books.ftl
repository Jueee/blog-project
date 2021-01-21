<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.1.4/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.1.4/js/fileinput.min.js"></script>
<div style="margin: 1% 10%;width: auto;">
<form class="form-horizontal" role="form" id="uploadBooksForm" method="post" action="uploadBooks" enctype="multipart/form-data">
    <div style="width:500px">
        <input id="input-b2" name="file" type="file" class="file" data-show-preview="false">
    </div>
    <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 上传</button>
    <button type="button" class="btn btn-success" onclick="downloadBooks()">
        <i class="glyphicon glyphicon-circle-arrow-down"></i> 下载
    </button>
</form>
<#if (books?size>0)>
<div class="dt-wrapper">
	<table class="table table-striped" >
	   <thead>
	    <tr>
	      <th>ID</th>
	      <th>名称</th>
	      <th>作者</th>
	      <th>出版社</th>
	      <th>页数</th>
	      <th>价格</th>
	      <th>CASE ID</th>
	      <th>ABLED</th>
	    </tr>
	  </thead>
	  <tbody>
		<#list books as temp>
		  <tr>
	      <td class="message">${temp.id?default("-")}</td>
	      <td class="message">${temp.name?default("-")}</td>
	      <td class="message">${temp.author?default("-")}</td>
	      <td class="message">${temp.publish?default("-")}</td>
	      <td class="message">${temp.pages?default("-")}</td>
	      <td class="message">${temp.price?default("-")}</td>
	      <td class="message">${temp.bookcaseid?default("-")}</td>
	      <td class="message">${temp.abled?default("-")}</td>
	      </tr>
		</#list>
	  </tbody>
	</table>
</div>
<#else>
 	<div class="mod-nodata">没有数据</div>
</#if>
</div>
<script>
function downloadBooks(id){
    window.location="downloadBooks";
}
</script>