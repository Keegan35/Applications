<?php session_start(); ?>
<!-- 15003770 -->
<?php 
	require 'DBConn.php'; 
	require 'item.php';
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Checkout</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Checkout</h1>
<table width='100%' border='1' align="center">
	<tr>
		<th width="25%"style="text-align:center;font-weight:bold">Quantity</th>
		<th width="25%"style="text-align:center;font-weight:bold">ID</th>
		<th width="25%"style="text-align:center;font-weight:bold">Price</th>
		<th width="25%"style="text-align:center;font-weight:bold">Total</th>	
	</tr>
	<?php
		$item = new Item();
		$cart = unserialize(serialize($_SESSION['cart']));
		$s = 0;
		$index = 0;
		for($i=0; $i<count($cart); $i++) {
		$s += $cart[$i]->SellPrice*$cart[$i]->Quantity;
	?>
		<tr>
			<td><?php echo $cart[$i]->Quantity;?></td>
			<td><?php echo $cart[$i]->ItemID;?></td>
			<td><?php echo $cart[$i]->SellPrice;?></td>
			<td><?php echo $cart[$i]->Quantity * $cart[$i]->SellPrice;  ?></td>
		</tr>
				<?php 
					$index++;
		} 		?>
	<tr>
		<td colspan = "3" align="right">Sum</td>
		<td align="left"><?php echo $s; ?></td>
	</tr>
</table>
<a href="checkout.php?Confirm='true'">Confirm</a>
<a href="index.php"> <button>Home</button> </a>
<?php
	if(isset($_GET['Confirm'])){
	$cart = unserialize(serialize($_SESSION['cart']));
	$totalPrice = 0;
	$ID = $_SESSION['ID'];
	for($i=0; $i<count($cart); $i++){
		$totalPrice += $cart[$i]->SellPrice*$cart[$i]->Quantity;
	}
	
	// Insert order into DB
		$sqlRes = "INSERT INTO tbl_Order (ID, TotalPrice) VALUES('$ID', '$totalPrice')";
		$result = mysqli_query($DBConnect, $sqlRes);
		//If errors occur
		if ($result === FALSE)
			echo "<p>Unable to add new order to database.</p>". "<p>Error code " . mysqli_errno($DBConnect). ": " . mysqli_error($DBConnect) . "</p>";
		else
			echo "<p>Successfully added order to the database.</p>";
		}
		
	// Clear the cart.
	$_SESSION['cart'] = null;
	$cart = null;
	
	// Destroy the session.
	// http://www.w3schools.com/php/php_sessions.asp
	// remove all session variables
	session_unset();
	
	// destroy the session
	session_destroy();
	//Redirect to home page 
	header("Location: index.php");
	exit;
	
?>
</body>
</html>