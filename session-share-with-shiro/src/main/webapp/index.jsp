<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>登陆界面</title>
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<style>
		html {
			background: url("/resources/images/bg.png") no-repeat center center;
		}
		label {
			color: #fff;
		}
		.container {
			position:absolute;
			top:50%;
			left:50%;
			margin-top: -115px;
			margin-left: -250px;
			width: 500px;
			height:230px;
			padding:50px;
			border: 2px solid #eee; 
			border-radius: 5px;
			box-shadow:5px 5px 16px #000;
		}
	</style>
</head>

<body>
	<div class="container">
		<form class="form-horizontal" role="form" action="/login" method="post">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="userName" placeholder="用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" placeholder="密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary" style="width: 100%">登陆</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
