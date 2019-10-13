<?php
function getDataFromTxt($events){
    $podaci = file($events);
    $data = "";
    foreach($podaci as $key=>$value){
        $data = explode("\n", $value);
    }
    return $data;
}
