<?php
/**
 * Created by PhpStorm.
 * User: Aniket
 * Date: 2/25/2019
 * Time: 8:55 PM
 */

    require_once('SampleDB.php');

    /**
     * 'QueryBuilder' class to perform various common operations on database relations;
     *
     * @author Aniket   
     * @access public
     */
    class QueryBuilder {

        /**
         * Function to get specified data from the specified relation.
         *
         * Ex. IF $attributes = "first_name", $student_id = 1, $relation_name = "students" THEN
         * $sql = "SELECT first_name FROM students WHERE student_id = 1"
         *
         * @access public
         * @param string $attributes Specifies the attribute names to be retrieved from the relation
         * @param string $relation_name Specifies the relation name to be queried
         * @param array $where_condition Specifies the condition for the WHERE clause
         * @return mysqli_result|string $result_set of '$relation_name' table on successful retrieval, else returns error-details specified by '$database->getConnection()->error'
         */
        public static function select($attributes, $relation_name, $where_condition) {
            global $database;

            $where_string = ''; // condition for the WHERE clause
            $datatype_defining_string = ''; // Specifies the string which is the first parameter in the 'bind_param' function
            $values_array = array(); // Array to store only the values of all keys from the json string

            foreach($where_condition as $key => $val) {
                // Push values of each key into values_array
                array_push($values_array, $val);

                // Identify the data-type of the current value
                // And set the $datatype_defining_String accordingly
                if(gettype($val) == "boolean" || gettype($val) == "string")
                    $datatype_defining_string .= "s";
                else if(gettype($val) == "integer")
                    $datatype_defining_string .= "i";
                else if(gettype($val) == "double")
                    $datatype_defining_string .= "d";
                else
                    return "Error: Cannot perform insert on relation $relation_name! Datatype for given data is denied!";

                // Form where String
                if(count($where_condition) == 1) { // if only 1 attribute is passed
                    $where_string = $key . " = ?     ";
                    break;
                }
                $where_string .= $key . " = ? AND ";
            }

            $where_string = substr($where_string, 0, -5);

            // Form SQL query
            $sql = "SELECT $attributes FROM $relation_name WHERE $where_string";
            $preparedStatement = $database->getConnection()->prepare($sql);
            $preparedStatement->bind_param($datatype_defining_string, ...$values_array);
            $preparedStatement->execute();
            $result_set = $preparedStatement->get_result();

            if($database->getConnection()->errno)
                return "Error while getting $relation_name details : " . $database->getConnection()->error;
            return $result_set;
        }

        /**
         * Function to insert data into specified relation
         *
         * @access public
         * @param array $insertion_details Specifies the php array which contains key-value pairs corresponding to attribute-value.
         * @param string $relation_name Specifies the relation name to be queried
         * @return string 'true' upon successful insertion, else error-details specified by $this->connection->error
         */
        public static function insert($insertion_details, $relation_name) {
            global $database;

            $total_unspecified_parameters_string = ''; // Total number of unspecified parameters i.e. question-marks(?)
            $attributes_list = ''; // List of all attributes in which values are to be inserted
            $datatype_defining_string = ''; // Specifies the string which is the first parameter in the 'bind_param' function
            $values_array = array(); // Array to store only the values of all keys from the php array

            foreach($insertion_details as $key => $val) {
                // Push values of each key into values_array
                array_push($values_array, $val);

                // Identify the data-type of the current value
                // And set the $datatype_defining_String accordingly
                if(gettype($val) == "boolean" || gettype($val) == "string")
                    $datatype_defining_string .= "s";
                else if(gettype($val) == "integer")
                    $datatype_defining_string .= "i";
                else if(gettype($val) == "double")
                    $datatype_defining_string .= "d";
                else
                    return "Error: Cannot perform insert on relation $relation_name! Datatype for given data is denied!";

                // Form the insertion string in the format attr1 = ?, attr2 = ?, ...
                // And also Maintain the attribute list
                if(count($insertion_details) == 1) { // if only 1 attribute is passed
                    $attributes_list = $key . "  "; // 2 spaces left blank after attribute-name intentionally; later they'll be removed by substr method
                    $total_unspecified_parameters_string = "?  "; // 2 spaces left blank after question-mark(?) intentionally; later they'll be removed by substr method
                    break;
                }
                $attributes_list .= $key . ", ";
                $total_unspecified_parameters_string .= "?, ";

            }

            // To remove the last comma & space i.e. ', ' in $attributes_list & $total_unspecified_parameters_string
            $attributes_list = substr($attributes_list, 0, -2);
            $total_unspecified_parameters_string = substr($total_unspecified_parameters_string, 0, -2);

            // Form SQL query & execute
            $sql = "INSERT INTO $relation_name($attributes_list) VALUES($total_unspecified_parameters_string)";
            $preparedStatement = $database->getConnection()->prepare($sql);
            $preparedStatement->bind_param($datatype_defining_string, ...$values_array);
            $preparedStatement->execute();

            if($database->getConnection()->errno)
                return "Error inserting values in $relation_name: " . $preparedStatement->error;
            return "true";
        }

        /**
         * Function to update data in the specified relation.
         *
         * @access public
         * @param array $attributes Specifies the attribute names to be retrieved from the relation
         * @param string $relation_name Specifies the relation name to be queried
         * @param array $where_condition Specifies the condition for the WHERE clause
         * @return string 'true' upon successful updation, else error-details specified by $database->getConnection()->error
         */
        public static function update($attributes, $relation_name, $where_condition) {
            global $database;

            $updation_string = ''; // Specifies the string which comes after "SET" keyword in the UPDATE statement; initialized to blank by default
            $where_string = '';
            $datatype_defining_string = ''; // Specifies the string which is the first parameter in the 'bind_param' function
            $values_array = array(); // Array to store only the values of all keys from the php array

            foreach($attributes as $key => $val) {
                // Push values of each key into values_array
                array_push($values_array, $val);

                // Identify the data-type of the current value
                // And set the $datatype_defining_String accordingly
                if(gettype($val) == "boolean" || gettype($val) == "string")
                    $datatype_defining_string .= "s";
                else if(gettype($val) == "integer")
                    $datatype_defining_string .= "i";
                else if(gettype($val) == "double")
                    $datatype_defining_string .= "d";
                else
                    return "Error: Cannot perform update on relation $relation_name! Datatype for given data is denied!";

                // Form the updation string in the format attr1 = ?, attr2 = ?, ...
                if(count($attributes) == 1) { // if only 1 attribute is passed
                    $updation_string = $key . " = ?  "; // 2 spaces left blank after question-mark(?) intentionally; later they'll be removed by substr method
                    break;
                }
                $updation_string .= $key . " = ?, ";
            }

            // To remove the last comma & space i.e. ', ' in the updation string
            $updation_string = substr($updation_string, 0, -2);

            foreach($where_condition as $key => $val) {
                // Push values of each key into values_array
                array_push($values_array, $val);

                // Identify the data-type of the current value
                // And set the $datatype_defining_String accordingly
                if(gettype($val) == "boolean" || gettype($val) == "string")
                    $datatype_defining_string .= "s";
                else if(gettype($val) == "integer")
                    $datatype_defining_string .= "i";
                else if(gettype($val) == "double")
                    $datatype_defining_string .= "d";
                else
                    return "Error: Cannot perform update on relation $relation_name! Datatype for given data is denied!";

                // Form the updation string in the format attr1 = ?, attr2 = ?, ...
                if(count($where_condition) == 1) { // if only 1 attribute is passed
                    $where_string .= $key . " = ?     ";
                    break;
                }
                $where_string .= $key . " = ? AND ";
            }

            // To remove the unnecessary last characters in the where string
            $where_string = substr($where_string, 0, -5);

            // Form the SQL query & execute
            $sql = "UPDATE $relation_name SET $updation_string WHERE $where_string";
            $preparedStatement = $database->getConnection()->prepare($sql);
            $preparedStatement->bind_param($datatype_defining_string, ...$values_array);
            $preparedStatement->execute();

            if($database->getConnection()->error)
                return "Error while Updating $relation_name: " . $database->getConnection()->error;
            return "true";
        }

        /**
         * Function to delete data from the specified relation.
         *
         * @access public
         * @param string $relation_name Specifies the relation name to be queried
         * @param array $where_condition Specifies the condition for the WHERE clause
         * @return string 'true' upon successful updation, else error-details specified by $database->getConnection()->error
         */
        public static function delete($relation_name, $where_condition) {
            global $database;

            $where_string = ''; // condition for the WHERE clause
            $datatype_defining_string = ''; // Specifies the string which is the first parameter in the 'bind_param' function
            $values_array = array(); // Array to store only the values of all keys from the php array

            foreach($where_condition as $key => $val) {
                // Push values of each key into values_array
                array_push($values_array, $val);

                // Identify the data-type of the current value
                // And set the $datatype_defining_String accordingly
                if(gettype($val) == "boolean" || gettype($val) == "string")
                    $datatype_defining_string .= "s";
                else if(gettype($val) == "integer")
                    $datatype_defining_string .= "i";
                else if(gettype($val) == "double")
                    $datatype_defining_string .= "d";
                else
                    return "Error: Cannot perform update on relation $relation_name! Datatype for given data is denied!";

                // Form the updation string in the format attr1 = ?, attr2 = ?, ...
                if(count($where_condition) == 1) { // if only 1 attribute is passed
                    $where_string .= $key . " = ?     ";
                    break;
                }
                $where_string .= $key . " = ? AND ";
            }

            // To remove the unnecessary last characters in the where string
            $where_string = substr($where_string, 0, -5);

            // Form SQL query
            $sql = "UPDATE $relation_name SET is_deleted = 1 WHERE $where_string";
            $preparedStatement = $database->getConnection()->prepare($sql);
            $preparedStatement->bind_param($datatype_defining_string, ...$values_array);
            $preparedStatement->execute();

            if($database->getConnection()->error)
                return "Error while deleting in $relation_name: " . $database->getConnection()->error;
            else
                return "true";
        }
    }
?>