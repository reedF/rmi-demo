<?php
/*
 * Created on 2014-3-28
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
include_once 'src/HessianClient.php'; 
$testurl = 'http://localhost:8080/rmi-demo/remoting/userServiceImpl'; 
$proxy = new HessianClient($testurl); 
try{ 
	$data = $proxy->findById(303);
    var_dump($data);
    echo $data->name;  
    echo 'done';
} catch (Exception $ex){ 
   echo 'error';
}
?>
