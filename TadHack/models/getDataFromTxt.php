<?php


header("Content-type: application/json");
require "../config/config.php";
require "functions.php";
try {
    $podaci = file(events);
    //var_dump($podaci);
    $data = "";

//    foreach($podaci as $txt){
//        $data .= explode("\n", $txt);
//    }



    http_response_code(200);
    echo json_encode($podaci);


} catch (PDOException $ex) {
    http_response_code(500);
    catchErrors("getDataFromTxt.php ->" . $ex->getMessage());
}


