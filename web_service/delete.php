<?php
    require 'Student.php';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $body = json_decode(file_get_contents("php://input"), true);
        $successful_delete = Student::delete($body['id']);

        if ($successful_delete) {
            print json_encode(
                array(
                    'state' => '1',
                    'message'=> 'Eliminación exitosa'
                )
            );
        } else {
            print json_encode(
                array(
                    'state' => '2',
                    'message' => 'Eliminación fallida'
                )
            );
        }
    }
?>
