<#macro masterTemplate title="Welcome">
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>Prediction</title>
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- Bootstrap -->
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>  Face Prediction  <small>  From COMP 7305</small></h1>
			</div>
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a href="/VGGpredict">Prediction</a></li>
					</ul>
				</div>
				<div id="myDiv" class="col-md-9">

					<div class="container">
						<#nested />
					</div>
				</div>
			</div>
			<footer class="footer">
				<p>Group06 &mdash; A Spark Application</p>
			</footer>
		</div><!-- /container -->
	</body>
</html>
</#macro>
