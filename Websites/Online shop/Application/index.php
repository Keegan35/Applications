<?php session_start(); ?>
<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Welcome</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<h1>Welcome to Smart Clothes - An eShop</h1>
	<h3>This eShop is a clothings shop</h3>
	<p style="text-align:center" >So grab a cup of coffee and start browsing the website. There is clothing that will suit everyone.</p>
	<p style="text-align:center" >Make your choices by clicking on the cart image. When you are ready to purchase your items, click Checkout.</p>
	<?php
	if($_SESSION['AdminStatus'] == true){
		echo "<a href='adminShop.php'><button>Admin</button></a>";
	}else{
		echo "<a href='adminLogin.php'><button>Admin</button></a>";
	}
	?>
	<a href="login.php"><button>User</button></a>
 
</body>
</html>