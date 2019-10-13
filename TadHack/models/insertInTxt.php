<?php


//header("Content-type: application/json");
require "../config/config.php";
require "functions.php";
try {
    $hiddenId = $_POST['hiddenId'];
    $mobilePhone = $_POST['mobileNumber'];
    //var_dump($mobilePhone);

    $podaci = fopen(send, "a");
    $data = '';

    fwrite($podaci, "{$hiddenId} | {$mobilePhone} \n");
    fclose($podaci);



    http_response_code(200);
//    echo json_encode($podaci);


} catch (PDOException $ex) {
    http_response_code(500);
   // catchErrors("getDataFromTxt.php ->" . $ex->getMessage());
}

