<?php
    require 'Student.php';

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $body = json_decode(file_get_contents("php://input"), true);

        $student = Student::getByEmailAndPassword(
            $body['email'],
            $body['password']);

        if ($student) {
            $data['state'] = '1';
            $data['student'] = $student;

            print json_encode($data);
        } else {
            print json_encode(array(
                'state' => '2',
                'message' => 'Correo o contraseña inválido'
            ));
        }
    }
?>
