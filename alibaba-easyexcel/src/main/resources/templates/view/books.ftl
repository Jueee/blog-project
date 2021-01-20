<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<div style="margin: 1% 10%;width: auto;">
<button type="button" class="btn btn-success" onclick="downloadBooks()">
    <i class="glyphicon glyphicon-circle-arrow-down"></i> 下载
</button>
<#if (books?size>0)>
<form class="form-horizontal" role="form" id="keyWordsBatchForm" method="post" >
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
</form>
<#else>
 	<div class="mod-nodata">没有数据</div>
</#if>
</div>
<script>
function downloadBooks(id){
    window.location="downloadBooks";
}
</script>