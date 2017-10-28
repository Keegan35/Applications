<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Shopping - Admin</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h1>Clothing Items</h1>

<?php
	
	// Call the DB
	include("DBConn.php");
	readAndDisplayTable($DBConnect);
	
	function readAndDisplayTable($DBConnect){
	?>
		<table border="1" width="100%">
		<!-- Display the headings -->
		<tr>
			<td width="15%"style="text-align:center;font-weight:bold">ID</td>
			<td width="25%"style="text-align:center;font-weight:bold">Description</td>
			<td width="15%"style="text-align:center;font-weight:bold">Quantity Available</td>
			<td width="15%"style="text-align:center;font-weight:bold">Selling Price</td>
			<td width="15%"style="text-align:center;font-weight:bold">Image</td>
			<td width="15%"style="text-align:center;font-weight:bold">Update</td>
		</tr>
	<?php
		// Get the all the details about the items.
		$SQLstring = "SELECT ItemID, Description, Quantity, SellPrice FROM tbl_Item";
		// Execute the SELECT.
		$QueryResult = mysqli_query($DBConnect, $SQLstring);
		// Keep track of the records.
		$count = 1;
		while(($Row = mysqli_fetch_assoc($QueryResult)) !== null)
		{
			
	?>	
			<!-- Display the all the items from the text file -->
			<tr>
				<td width="15%"style="text-align:center;font-weight:bold"><?php echo htmlentities($count)?></td>
				<td width="25%"style="text-align:center;font-weight:bold"><?php echo htmlentities($Row['Description'])?></td>
				<td width="15%"style="text-align:center;font-weight:bold"><?php echo htmlentities($Row['Quantity'])?></td>
				<td width="15%"style="text-align:center;font-weight:bold"><?php echo "R".htmlentities($Row['SellPrice'])?></td>
				<td width="15%" align="center" ><img src = "<?php echo "images/".$Row['ItemID'].".jpg" ?>" height="40" width="40" align = "top"/></td>	
				<td width="15%" align="center" ><a href="adminDeleteItem.php"><button>Delete</button></a></td>
			</tr>
		
	<?php
	$count++;
	}
	?>	

	</table>
	<a href="adminAddItem.php"><button>Add</button></a>
	<?php
		}
		
		function __sleep() 
		{
			$SerialVars = array('Balance');
			return $SerialVars;
		}
		function __wakeup() 
		{
			include("DBConn.php");
			$this->DBConnect = $DBConnect;
		}
	?>
</body>
</html>
