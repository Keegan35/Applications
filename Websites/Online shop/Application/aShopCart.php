<?php session_start(); ?>
<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Shopping Cart</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Cart Items</h1>
<?php
require 'item.php';
$sCart = new shoppingCart();
$DBConnect = $sCart->__wakeup();
if(isset($_GET['ItemID'])){
	$IdItem = $_GET['ItemID'];
	$sql = "SELECT ItemID, Quantity, SellPrice FROM tbl_Item WHERE ItemID = '".$IdItem."'";
	$result = mysqli_query($DBConnect,$sql);
	$product = mysqli_fetch_object($result);
	$item = new Item();
	$item->ItemID = $product->ItemID;
	$item->SellPrice = $product->SellPrice;
	$item->Quantity = 1;
	$index = -1;
	$cart = unserialize(serialize($_SESSION['cart']));

	for($i=0; $i<count($cart); $i++){
		if($cart[$i]->ItemID == $_GET['ItemID'])
		{
			$index = $i;
			break;
		}
	}
	if($index == -1){
		$_SESSION['cart'][0] =$item;
	}
	else{
		$cart[$index]->Quantity++;
		$_SESSION['cart']=$cart;
	}
}
if(isset($_GET['ItemToDelete'])){
	$cart = unserialize(serialize($_SESSION['cart']));

	for($i=0; $i<count($cart); $i++){
		if($cart[$i]->ItemID == $_GET['ItemToDelete'])
		{
			$index = $i;
			break;
		}
	}
	if($index != -1){
		if($cart[$index]->Quantity > 0){
			$cart[$index]->Quantity--;
			$_SESSION['cart']=$cart;
		}
	}
}
if(isset($_GET['ItemToAdd'])){
	$cart = unserialize(serialize($_SESSION['cart']));

	for($i=0; $i<count($cart); $i++){
		if($cart[$i]->ItemID == $_GET['ItemToAdd'])
		{
			$index = $i;
			break;
		}
	}
	if($index != -1){
		$cart[$index]->Quantity++;
		$_SESSION['cart']=$cart;
	}
}

if(isset($_GET['index'])){
	$cart = unserialize(serialize($_SESSION['cart']));
	unset($cart[$_GET['index']]);
	$cart = array_values($cart);
	$_SESSION['cart'] = $cart;
}
?>
<table width='100%' border='1' align="center">
	<tr>
		<th  width="20%"style="text-align:center;font-weight:bold">Quantity</th>
		<th  width="20%"style="text-align:center;font-weight:bold">ID</th>
		<th  width="20%"style="text-align:center;font-weight:bold">Price</th>
		<th  width="20%"style="text-align:center;font-weight:bold">Total</th>	
		<th  width="20%"style="text-align:center;font-weight:bold">Delete</th>
	</tr>
	<?php
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
            <td><a href="aShopCart.php?ItemToAdd=<?php echo $cart[$i]->ItemID; ?>">Add</a></br><a href="aShopCart.php?ItemToDelete=<?php echo $cart[$i]->ItemID; ?>">Delete</a></td>
		</tr>
				<?php 
					$index++;
		} 		?>
	<tr>
		<td colspan = "3" align="right">Sum</td>
		<td align="left"><?php echo $s; ?></td>
	</tr>
</table>
<br>

<a href="myShop.php">Continue Shopping</a> <br>
<a href="checkout.php">Checkout</a>
<a href="index.php"> <button>Home</button> </a>

<?php
class shoppingCart{
	
	function __sleep() 
		{
			$SerialVars = array('Balance');
			return $SerialVars;
		}
	function __wakeup() 
	{
		include("DBConn.php");
		$this->DBConnect = $DBConnect;
		return $this->DBConnect;
	}
}

?>