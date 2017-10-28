<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Question Two E - Check if tables exists</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
</head>
<body>
	<?php
		include("DBConn.php");
		
		$item = array(4);
		
		// TABLE ONE :
		// Check if there is an existing table called 'tbl_Order'.
		$TableName = "tbl_Order";
		$SQLstring = "SHOW TABLES LIKE '$TableName'";
		
		if (mysqli_num_rows (mysqli_query($DBConnect, $SQLstring)) == 1) 
		{   
			// Delete the table.
				$deleteStatement = "Drop TABLE tbl_Order;";
				deleteTable($DBConnect, $deleteStatement, $TableName);
			// Create the table.
				$SQLstring = "CREATE TABLE tbl_Order (OrderID int(11) PRIMARY KEY AUTO_INCREMENT, ID int(11), TotalPrice int(5) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;";
				createTable($DBConnect, $SQLstring, $TableName);
		}
		else
		{	
			echo "<p>The tbl_User table does not exist!</p>"; 
		}
		
		
		//TABLE TWO :
		// Check if there is an existing table called 'tbl_Item'.
		$TableName = "tbl_Item";
		$SQLstring = "SHOW TABLES LIKE '$TableName'";
		
		if (mysqli_num_rows (mysqli_query($DBConnect, $SQLstring)) == 1) 
		{   
			// Delete the table.
				$deleteStatement = "Drop TABLE tbl_Item;";
				deleteTable($DBConnect, $deleteStatement, $TableName);
			// Create the table.
				$SQLstring = "CREATE TABLE tbl_Item (ItemID varchar(30) PRIMARY KEY, Description Varchar(50) NOT NULL, CostPrice decimal(15,2) NOT NULL, Quantity int(11) NOT NULL, SellPrice decimal(15,2) NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;";
				createTable($DBConnect, $SQLstring, $TableName);
			// Load information into table.
				$SQLString = "LOAD DATA LOCAL INFILE 'items.csv' 
    						  INTO TABLE tbl_Item  
    						  FIELDS TERMINATED BY ','";
				loadInformation($DBConnect,$SQLString);
		}
		else
		{	
			echo "<p>The tbl_User table does not exist!</p>"; 
		}

		//TABLE THREE :
		// Check if there is an existing table called 'tbl_User'.
		$TableName = "tbl_User";
		$SQLstring = "SHOW TABLES LIKE '$TableName'";

		if (mysqli_num_rows (mysqli_query($DBConnect, $SQLstring)) == 1) 
		{   
			// Delete the table.
				$deleteStatement = "Drop TABLE tbl_User;";
				deleteTable($DBConnect, $deleteStatement, $TableName);
			// Create the table.
				$SQLstring = "CREATE TABLE tbl_User (ID int(11) PRIMARY KEY AUTO_INCREMENT, FirstName Varchar(50) NOT NULL, LastName Varchar(50) NOT NULL, Email Varchar(50) NOT NULL, Password Varchar(50) NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;";
				createTable($DBConnect, $SQLstring, $TableName);
			// Load information into table.
				$SQLString = "LOAD DATA LOCAL INFILE 'userData.csv' 
    						  INTO TABLE tbl_User  
    						  FIELDS TERMINATED BY ','";
				loadInformation($DBConnect,$SQLString);
		}
		else
		{	
			echo "<p>The tbl_User table does not exist!</p>"; 
		}
		
		// Add FOREIGN Keys.
			$Str = "ALTER TABLE tbl_Order ADD CONSTRAINT fk_ID FOREIGN KEY (ID) REFERENCES tbl_Customer (ID) ON DELETE CASCADE ON UPDATE CASCADE";
			updateTable($DBConnect, $Str, $TableName);
	function deleteTable($DBConnect, $SQLstring, $table)
	{
		echo "<p> Deleting table ".$table."</p>";
		// Execute the drop.
		$QueryResult = mysqli_query($DBConnect, $SQLstring);
		if ($QueryResult)
		{
			echo "<p>Successfully deleted the table.</p>";
		}	
		else
		{
			echo "<p>Unable to execute the query.</p>". "<p>Error code " . mysqli_errno($DBConnect). ": " . mysqli_error($DBConnect) . "</p>";
		}
	}
	function updateTable($DBConnect, $SQLstring, $table)
	{
		echo "Updating table ".$table;
		  
		// Execute the update.
		$QueryResult = mysqli_query($DBConnect, $SQLstring);  
		
		if ($QueryResult)
		{			
			echo "<p>Successfully update the table</p>"; 
		}				
		else  
		{				
			echo "<p>Unable to execute the query.</p>"."<p>Error code " . mysqli_errno ($DBConnect). ": " . mysqli_error($DBConnect). "</p>";  
		}
	}
	function createTable($DBConnect, $SQLstring, $table)
	{
		echo "Recreating table ".$table;
		  
		// Execute the create table.
		$QueryResult = mysqli_query($DBConnect, $SQLstring);  
		
		if ($QueryResult)
		{			
			echo "<p>Successfully created the table</p>"; 
		}				
		else  
		{				
			echo "<p>Unable to execute the query.</p>"."<p>Error code " . mysqli_errno ($DBConnect). ": " . mysqli_error($DBConnect). "</p>";  
		}
	}
	// Read info from txt, store it in an array and insert those values into tbl_user
	function loadInformation($DBConnect,$SQLString)
	{	
				$QueryResult = mysqli_query($DBConnect, $SQLString);

					if($QueryResult) 
					{
						
						echo "<p>Data was successfully imported</p>";
					
					}
					else
					{
						echo "<p>The query was unable to execute/p>"
						."<p>Error code: ".mysqli_errno($DBConnect)."</p>";
					}
	}
	?>
	
</body>
