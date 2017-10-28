<!-- 15003770 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Register Page</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<?php
	// Declare variables.
		$displayForm = true;
		$FName ="";
		$LName = "";
		$email = "";
		$password = "";
		$passwordConfirm = "";
		
		include("DBConn.php");
		
	if(isset($_POST['submit']))
	{
		$FName = $_POST['FirstName'];
		$LName = $_POST['LastName'];
		$email = $_POST['Email'];
		$password = $_POST['Password'];
		$passwordConfirm = $_POST['Cpassword'];
		$countErrors=0;
		if (!preg_match("/^[a-zA-Z ]*$/",$FName)){
			echo("First name is in incorrect format");
			$countErrors++;
		}
		if (!preg_match("/^[a-zA-Z ]*$/",$LName))
		{
			echo("LastName is in incorrect format");
			$countErrors++;
		}
		if(!$email)
		{
			echo('Email is empty');
			$countErrors++;
		}
		elseif(!preg_match('/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)*(\.[[A-Za-z]{2,})$/i', $email))
		{
			echo('Email is in incorrect format');
			$countErrors++;
		}
		if($password != $passwordConfirm) 
		{
			echo 'Passwords do not match';
			$countErrors++;
		}
                elseif(!preg_match('/^(?=.*\d)(?=.*[A-Z])[0-9A-Za-z!@#$%]{5}$/', $password))
		{
			echo('Password must be 5 characters with capitial, small letters and numbers');
			$countErrors++;
		}
		if($countErrors==0)
		{
			$displayForm=false;
			$password = md5($password);
			$sqlRes = "INSERT INTO tbl_User (FirstName,LastName,Email,Password) "." VALUES('$FName', '$LName', '$email', '$password')";
		$result = mysqli_query($DBConnect, $sqlRes);
		if ($result === FALSE)
			echo "<p>Unable to execute the query.</p>". "<p>Error code " . mysqli_errno($DBConnect). ": " . mysqli_error($DBConnect) . "</p>";
		else
			echo "<p>Successfully added your details to the database.</p>";
                        ?>
					<a href="login.php"> <button>Go Back To Login</button> </a>
                        <?php
		}
		else{
			$displayForm=true;
		}
		
	}
	if ($displayForm) 
	{
?>
        <h2 style= "text-align:center">Register New User</h2>
	<form action="register.php" method="post">
			First Name:<br>
			<input type="text" name="FirstName" value="<?php echo $FName?>"/><br/>
			Last Name:<br>
			<input type="text" name="LastName" value="<?php echo $LName?>" /><br/>
			Email:<br>
			<input type="text" name="Email" value="<?php echo $email?>"/><br/>
			Password:<br>
			<input type="password" name="Password" /><br />
			Confirm Password:<br>
				<input type="password" name="Cpassword" ><br/><br/>
				<input type="submit" name="submit" value="Sign Up" /><br/><br/>
				<a href="index.php">Already a member?</a>
		</form>
<?php
		
	}
	
?>


</body>
</html>