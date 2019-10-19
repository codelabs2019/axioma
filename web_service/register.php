<?php
    require 'Student.php';


    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $body = json_decode(file_get_contents("php://input"), true);

        if (!Student::exists($body['email'])) {
            $successful_insert = Student::insert(
                $body['forename'],
                $body['surname'],
                $body['email'],
                $body['password']);

            if ($successful_insert) {
                $student = Student::getByEmail($body['email']);

                $data['state'] = "1";
                $data['student'] = $student;

                print json_encode($data);
            } else {
                print json_encode(
                    array(
                        'state' => '2',
                        'message' => 'Registro fallido')
                );
            }
        } else {
            print json_encode(
                array(
                    'state' => '3',
                    'message' => 'El usuario ya existe'
                )
            );
        }
    }
?>
