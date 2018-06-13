<?php
// including the database connection file
include_once("config.php");
 
if(isset($_POST['update']))
{    
    $id = $_POST['ID'];
    $Vorname=$_POST['Vorname'];
    $Nachname=$_POST['Nachname']; 
    
    // checking empty fields
    if(empty($Vorname) || empty($Nachname)) {            
        if(empty($Vorname)) {
            echo "<font color='red'>Name field is empty.</font><br/>";
        }
        
        if(empty($Nachname)) {
            echo "<font color='red'>Age field is empty.</font><br/>";
        }
                
    } else {    
        //updating the table
        $result = mysqli_query($mysqli, "UPDATE Person SET Vorname='$Vorname',Nachname='$Nachname'WHERE ID=$id");
        
        //redirectig to the display page. In our case, it is index.php
        header("Location: index.php");
    }
}
?>
<?php
//getting id from url
$id = $_GET['id'];
 
//selecting data associated with this particular id
$result = mysqli_query($mysqli, "SELECT * FROM Person WHERE ID=$id");
 
while($res = mysqli_fetch_array($result))
{
    $Vorname = $res['Vorname'];
    $Nachname = $res['Nachname'];
}
?>
<html>
<head>    
    <title>Edit Data</title>
</head>
 
<body>
    <a href="index.php">Home</a>
    <br/><br/>
    
    <form name="form1" method="post" action="edit.php">
        <table border="0">
            <tr> 
                <td>Vorname</td>
                <td><input type="text" name="Vorname" value="<?php echo $Vorname;?>"></td>
            </tr>
            <tr> 
                <td>Nachname</td>
                <td><input type="text" name="Nachname" value="<?php echo $Nachname;?>"></td>
            </tr>
            <tr>
                <td><input type="hidden" name="ID" value=<?php echo $_GET['id'];?>></td>
                <td><input type="submit" name="update" value="Update"></td>
            </tr>
        </table>
    </form>
</body>
</html>