<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin Add</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<?php
	//Variables
		$displayForm = true;
		$ItemID ="";
		$Description = "";
		$CostPrice = "";
		$Quantity = "";
		$SellPrice = "";
		
		include("DBConn.php");
		//Code for submit button
	
	if(isset($_POST['submit']))
	{
		$ItemID = $_POST['ItemID'];
		$Description = $_POST['Description'];
		$CostPrice = $_POST['CostPrice'];
		$Quantity = $_POST['Quantity'];
		$SellPrice = $_POST['SellPrice'];
		$countErrors=0;
		
		if (empty($ItemID)){
			echo("Enter an ID for item");
			$countErrors++;
		}
	
		if (empty($Description))
		{
			echo("Enter a description");
			$countErrors++;
		}
		
		if(empty($CostPrice))
		{
			echo('Please supply CostPrice');
			$countErrors++;
		}
		
		if(empty($Quantity))
		{
			echo('Please enter a quanitity');
			$countErrors++;
		}
		
		if(empty($SellPrice)) 
		{
			echo 'Please enter a sell price';
			$countErrors++;
		} 
		//If there are no errors occur 
		if($countErrors==0)
		{
			$displayForm=false;
			$sqlRes = "INSERT INTO tbl_Item (ItemID,Description,CostPrice,Quantity,SellPrice) "." VALUES('$ItemID', '$Description', '$CostPrice', '$Quantity', '$SellPrice')";
		$result = mysqli_query($DBConnect, $sqlRes);
		//If errors occur
		if ($result === FALSE)
			echo "<p>Unable to add new item to database.</p>". "<p>Error code " . mysqli_errno($DBConnect). ": " . mysqli_error($DBConnect) . "</p>";
		else
			echo "<p>Successfully added item to the database.</p>";
		}
		else{
			$displayForm=true;
		}
	}
	if ($displayForm) 
	{
?>
        <h2 style= "text-align:center" >Add Item</h2>
	<form action="adminAddItem.php" method="post">
			Item ID:<br>
			<input type="text" name="ItemID" value="<?php echo $ItemID?>"/><br/>
			Description:<br>
			<input type="text" name="Description" value="<?php echo $Description?>" /><br/>
			CostPrice:<br>
			<input type="text" name="CostPrice" value="<?php echo $CostPrice?>"/><br/>
			Quantity:<br>
			<input type="text" name="Quantity" value="<?php echo $Quantity?>"/><br />
			SellPrice:<br>
				<input type="text" name="SellPrice" value="<?php echo $SellPrice?>" ><br/><br/>
				<input type="submit" name="submit" value="Add Item" /><br/><br/>
		</form>
<?php	
	}
?>
	<a href="index.php"><button>Home</button></a>		
</body>
</html> 