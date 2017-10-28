<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin Delete</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<?php
	//Variables
		$displayForm = true;
		$ItemName ="";
		
		//Establish Connection to DB
		include("DBConn.php");
		//Code for submit button
	
	if(isset($_POST['delete']))
	{
		$ItemName = $_POST['itemName'];
		$countErrors=0;
		//Validate Name
		if (empty($ItemName)){
			echo("Enter an Name for item");
			$countErrors++;
		}
		
		//If there are no errors occur 
		if($countErrors==0)
		{
			$displayForm=false;
			$ItemName = $ItemName;
			$sqlRes = "DELETE FROM tbl_Item where Description LIKE '%".$ItemName."%'";
		$result = mysqli_query($DBConnect, $sqlRes);
		//If errors occur
		if ($result === FALSE)
			echo "<p>Unable to delete item from database.</p>". "<p>Error code " . mysqli_errno($DBConnect). ": " . mysqli_error($DBConnect) . "</p>";
		else
			echo "<p>Successfully deleted Item-".$ItemName." from the database</p>";
		}
		else{
			$displayForm=true;
		}
	}
	if ($displayForm) 
	{
?>
<h1>Enter the ItemID of the Item that you want to delete</h1>
	<form action="adminDeleteItem.php" method="post">
			Enter Name:<br><input type="text" name="itemName" value="<?php echo $ItemName?>"/><br/>
			<input type="submit" name="delete" value="Delete Item" /><br/><br/>
		</form>
<?php	
	}
?>
<a href="index.php"><button>Home</button></a>	
</body>
</html> 