<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>My Shop</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Clothing Items</h1>
<?php
require 'DBConn.php';
// Get all the items from the DB.
$result = mysqli_query($DBConnect, 'SELECT * FROM tbl_Item');
// Keep track of the clothing items.
$clothes = 1;
?>
<table width='100%' border='1' >
	<!-- Table headings -->
	<tr>
		<th width="25%"style="text-align:center;font-weight:bold">Item</th>
		<th width="25%"style="text-align:center;font-weight:bold">Selling Price</th>
		<th width="25%"style="text-align:center;font-weight:bold">Image of Item</th>
		<th width="25%"style="text-align:center;font-weight:bold">Add To Cart</th>	
	</tr>
	<!-- Loop through the items and load them into the table -->
	<?php while ($product = mysqli_fetch_object($result)){ ?>
		<!-- Filling each row -->
		<tr>
			<td align = "center"><?php echo $clothes ?></td>
			<td align = "center"><?php echo "R".$product->SellPrice; ?></td>
			<td align = "center"><img src = "<?php echo "images/".$product->ItemID.".jpg" ?>" height="40" width="40"/></td>
			<td><a href="aShopCart.php?ItemID=<?php echo $product->ItemID; ?>"/><button><img src = "<?php echo "images/cart.jpg" ?>" height="40" width="40"/></button></td>
		</tr>
	<?php $clothes = $clothes + 1; } ?>
</table>
<a href="index.php"> <button>Home</button> </a>