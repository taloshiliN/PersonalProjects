<?php
$username=$_POST['Username'];
$name=$_POST['Name'];
$surname=$_POST['Surname'];
$email=$_POST['Email'];
$password=$_POST['Password'];

$conn = new mysqli('localhost','root','','dummy');
if($conn ->connect_error){
    die("connection failed". $conn->connect_error);
} else{
    $stmt=$conn->prepare("insert into registrationlist(Username,Name,Surname,Email,Password)values(?,?,?,?,?)");
    $stmt->bind_param("sssssi",$username,$name,$surname,$email,$password);
    $stmt->execute();
    echo "registration Successful";
    header('Location: index.html');
    exit;
    $stmt->close();
    $conn->close();
}
?>