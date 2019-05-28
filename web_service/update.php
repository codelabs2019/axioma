<?php
    require 'Student.php';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $body = json_decode(file_get_contents("php://input"), true);

        $successful_update = Student::update(
            $body['id'],
            $body['forename'],
            $body['surname'],
            $body['email'],
            $body['password']
        );

        if ($successful_update) {
            print json_encode(
                array(
                    'state' => '1',
                    'message' => 'Actualización exitosa'
                )
            );
        } else {
            print json_encode(
                array(
                    'state' => '2',
                    'message' => 'Actualización fallida'
                )
            );
        }
    }
?>
