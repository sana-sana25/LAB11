<?php

include_once __DIR__ . '/../dao/IDao.php';
include_once __DIR__ . '/../classe/Position.php';
include_once __DIR__ . '/../connexion/Connexion.php';

class PositionService implements IDao {

    private $connexion;

    public function __construct() {

        $this->connexion = new Connexion();
    }

    // INSERTION D'UNE POSITION

    public function create($position) {

    try {

        $sql = "INSERT INTO `position`(latitude, longitude, date_position, imei)
                VALUES(:latitude, :longitude, :date_position, :imei)";

        $stmt = $this->connexion
                     ->getConnexion()
                     ->prepare($sql);

        $stmt->execute([

            ':latitude' => $position->getLatitude(),
            ':longitude' => $position->getLongitude(),
            ':date_position' => $position->getDatePosition(),
            ':imei' => $position->getImei()

        ]);

        echo "INSERT OK";

    } catch (PDOException $e) {

        echo "ERREUR SQL : " . $e->getMessage();
    }
}

    // MÉTHODES OBLIGATOIRES DE L'INTERFACE IDao

    public function update($obj) {

    }

    public function delete($obj) {

    }

    public function getById($obj) {

    }

    public function getAll() {

    }
}

?>