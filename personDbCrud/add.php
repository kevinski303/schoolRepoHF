<html>
<head>
    <title>Add Data</title>
</head>
 
<body>
<?php
//including the database connection file
include_once("config.php");
 
if(isset($_POST['Submit'])) {    
    $Vorname = $_POST['Vorname'];
    $Nachname = $_POST['Nachname'];
            
    // checking empty fields
    if(empty($Vorname) || empty($Nachname)) {                
        if(empty($Vorname)) {
            echo "<font color='red'>Vorname is empty.</font><br/>";
        }
        
        if(empty($Nachname)) {
            echo "<font color='red'>Nachname is empty.</font><br/>";
        }
        
        
        //link to the previous page
        echo "<br/><a href='javascript:self.history.back();'>Go Back</a>";
    } else { 
        // if all the fields are filled (not empty)             
        //insert data to database
        $result = mysqli_query($mysqli, "INSERT INTO person(Vorname,Nachname) VALUES('$Vorname','$Nachname')");
        
        //display success message
        echo "<font color='green'>Data added successfully.";
        echo "<br/><a href='index.php'>View Result</a>";
    }
}
?>
</body>
</html>