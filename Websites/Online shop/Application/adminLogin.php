<?php session_start(); ?>
<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login Page - Admin</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<?php
	
	// Declare variables.
	$displayForm = true;
	$UsernameEntered = "";
	$PasswordEntered = "";
	$Status;

		include("DBConn.php");
		
	// If the Submit button is clicked, execute this code.
	if(isset($_POST['Login']))
	{
		// Bring the values from the form and store it in the variables.
		$UsernameEntered = $_POST['Username'];
		$PasswordEntered = $_POST['Password'];
		
		// Validate the input.
		if(validateInput($UsernameEntered) && validateInput($PasswordEntered))
		{
			// If the user is an admin.
			$Status = checkIfUserIsAdmin($UsernameEntered, $PasswordEntered);
			
			if($Status)
			{
				$displayForm = false;
				?>
					<a href="adminShop.php"><button>Show Shop</button></a>	
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
	<h1>Admin Login</h1>
	<h3>Please enter your admin login details.</h3>
	<form name="Login Form" action="adminLogin.php" method="post">
	<p>Username: <input type="text" name="Username" value="<?php echo $UsernameEntered; ?>"/></p>
	<p>Password: <input type="password" name="Password" value="<?php echo $PasswordEntered; ?>"/></p>
	<p><input type="reset" value="Clear Form" />
	&nbsp;
	&nbsp;
	<input type="submit" name="Login" value="Login" /></p>
	</form>
<?php
		
	}
	
	// Check if the user is a valid admin.
	function checkIfUserIsAdmin($Username, $Password){		
		
		if($Username == 'admin' && $Password == 'Password1')
		{
			echo "<p>Welcome ".$Username."!</p>";
			$_SESSION['AdminName'] = $Username;
			$_SESSION['AdminStatus'] = true;
			return true;
		}
		else
		{
			echo "<p>Invalid password entered.</p>";
			return false;
		}
	}
	// Check if field is in the correct format. Return false if it is not, true if it is.
	function validateInput($ValueEntered)
	{
		if(empty($ValueEntered))
		{
			echo "<p>Field can't be empty</p>";
			return false;
		}
		else
		{
			return true;
		}
	}
?>
</body>
</html>