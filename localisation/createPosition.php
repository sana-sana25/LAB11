<?php

include_once 'service/PositionService.php';
include_once 'classe/Position.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $latitude = $_POST['latitude'];
    $longitude = $_POST['longitude'];
    $datePosition = $_POST['date_position'];
    $imei = $_POST['imei'];

    try {

        $service = new PositionService();

        $position = new Position(
            null,
            $latitude,
            $longitude,
            $datePosition,
            $imei
        );

        $service->create($position);

        echo "INSERTION OK";

    } catch (Exception $e) {

        echo "ERREUR : " . $e->getMessage();
    }
}

?>