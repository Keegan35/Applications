<?php session_start(); ?>
<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login Page</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<?php
	
	// Declare variables.
	$displayForm = true;
	$EmailEntered = "";
	$PasswordEntered = "";
	$Status;
	
		include("DBConn.php");
		
	// If the Submit button is clicked, execute this code.
	if(isset($_POST['Login']))
	{
		// Bring the values from the form and store it in the variables.
		$EmailEntered = $_POST['Email'];
		$PasswordEntered = $_POST['EnteredPassword'];
		
		// Validate the first name and last name.
		if(validateEmail($EmailEntered) && validatePassword($PasswordEntered))
		{
			// Compare the entered details to the details in the DB if user exists.
			$Status = checkIfUserExists($EmailEntered, $PasswordEntered, $DBConnect);
			
			if($Status)
			{
				$displayForm = false;
				?>
					<a href='<?php echo "myShop.php?".session_id()?>'> <button>Show Items</button> </a>
					
				<?php
			}
		}
		else
		{
			$displayForm = true;
		}
		
	}
	if ($displayForm) 
	{
?>
	<h1>Login</h1>
	<h3>Please enter your login details.</h3>
	<form name="Login Form" action="login.php" method="post">
	<p>Email: <input type="email" name="Email" value="<?php echo $EmailEntered; ?>"/></p>
	<p>Password: <input type="password" name="EnteredPassword" value="<?php echo $PasswordEntered; ?>"/></p>
	<p><a href="register.php">Don't have an account? Click here to sign up.</a></p>
	<p><input type="reset" value="Clear Form" />
	&nbsp;
	&nbsp;
	<input type="submit" name="Login" value="Login" /></p>
	</form>
<?php
		
	}
	
	// Check user is already in the DB. Return true if they are, false if they are not.
	function checkIfUserExists($Email, $PasswordEntered, $DBConnect){		
		// Get the userID from the DB.
		$SQLstring = "SELECT ID, FirstName, LastName, Password FROM tbl_User WHERE Email ='".$Email."'";
		// Execute the SELECT.
		$QueryResult = mysqli_query($DBConnect, $SQLstring);
		// Store the results.
		$Row = mysqli_fetch_assoc($QueryResult);
		// $Row will be NULL if there is no ID for the user -- the user does not exist in the DB.
		if($Row == NULL)
		{
			echo "<p>User does not exit.</p>";
			return false;
		}
		else
		{
			if(md5($PasswordEntered) == trim($Row['Password']))
			{
				echo "<p>Welcome ".$Row['FirstName']."!</p>";
				
				$_SESSION['ID'] = $Row['ID'];
				$_SESSION['FirstName'] = $Row['FirstName'];
				$_SESSION['LastName'] = $Row['LastName'];
				return true;
			}
			else
			{
				echo "<p>Invalid password entered.</p>";
				return false;
			}
		}
		
	}
	// Check if email is in the correct format.
	function validateEmail($EmailEntered)
	{
		if(empty($EmailEntered))
		{
			echo "<p>Email field can't be empty</p>";
			return false;
		}
		else
		{
			return true;
		}
	}
	
	// Check if password is entered.
	function validatePassword($PasswordEntered)
	{
		if(empty($PasswordEntered))
		{
			echo "<p>Password field can't be empty</p>";
			return false;
		}
		else
		{
			return true;
		}
	}
	
        function checkEmail($EmailEntered)
	{
		$validEmail = false;
		if(preg_match('/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)*(\.[[A-Za-z]{2,})$/i', $EmailEntered) == 1)
		{
			$validEmail = true;
		}
		return $validEmail;
	}

	function checkPassword($PasswordEntered)
	{
		$validPassword = false;
		if(preg_match('/^(?=.*\d)(?=.*[A-Z])[0-9A-Za-z!@#$%]{5}$/', $PasswordEntered) == 1)
		{
			$validPassword = true;
		}
		return $validPassword;
	}
	
?>


</body>
</html>