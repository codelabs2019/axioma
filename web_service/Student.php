<?php
    require 'Database.php';

    class Student {

        function __construct() {

        }

        public static function getByEmail($email) {
            $sql = "SELECT id, forename, surname, email, password
                FROM student WHERE email = ?";

            try {
                $statement = Database::getInstance()->getDb()->prepare($sql);
                $statement->execute(array($email));
                $row = $statement->fetch(PDO::FETCH_ASSOC);
                return $row;
            } catch(PDOException $e) {
                return false;
            }
        }

        public static function getByEmailAndPassword($email, $password) {
            $sql = "SELECT id, forename, surname, email, password
                FROM student WHERE email = ? AND password = ?";

            try {
                $statement = Database::getInstance()->getDb()->prepare($sql);
                $statement->execute(array($email, $password));
                $student = $statement->fetch(PDO::FETCH_ASSOC);
                return $student;
            } catch(PDOException $e) {
                return false;
            }
        }

        public static function exists($email) {
            $sql = "SELECT COUNT(*) FROM student WHERE email = ?";

            $statement = Database::getInstance()->getDb()->prepare($sql);
            $statement->execute(array($email));

            if ($statement->fetchColumn() == 1) {
                return true;
            }

            return false;
        }

        public static function update($id, $forename, $surname, $email, $password) {
            $sql = "UPDATE student " .
                        "SET forename = ?, surname = ?, email = ?, password = ? " .
                        "WHERE id = ?";

            $statement = Database::getInstance()->getDb()->prepare($sql);

            return $statement->execute(array($forename, $surname, $email, $password,$id));
        }

        public static function insert($forename, $surname, $email, $password) {
            $sql = "INSERT INTO student (" .
                    "forename, " .
                    "surname, " .
                    "email, " .
                    "password) " .
                    "VALUES(?,?,?,?)";

            $statement = Database::getInstance()->getDb()->prepare($sql);

            return $statement->execute(array($forename, $surname, $email, $password));
        }

        public static function delete($id) {
            $sql = "DELETE FROM student WHERE id = ?";

            $statement = Database::getInstance()->getDb()->prepare($sql);

            return $statement->execute(array($id));
        }
    }
?>
