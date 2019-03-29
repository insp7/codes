<?php
    // Sample Database Class
    /**
     * Database class for establishing connection with the database; 
     * Also will he used for getting this database connection object.
     * 
     * @access public
     */
    class SampleDB {
        private $servername;
        private $username;
        private $password;
        private $database;
        private $connection;
        
        /**
         * Creates a new Database object and sets the necessary details(servername, username, password, database) 
         * for establishing connection with the database.
         * 
         * @access public
         */
        public function __construct() {
            $this->servername = "localhost";
            $this->username = "your-username";
            $this->password = "your-password";
            $this->database = "your-databasename";
            $this->connectDB();
        }

        /**
         * Function for establishing connection with the database;
         * The connection is stored in the '$connection' variable.
         * 
         * @access public
         */
        public function connectDB() {
            $this->connection = new mysqli($this->servername, $this->username, $this->password, $this->database);

            if(mysqli_connect_error()) { // if connection not successful
                die("Connection Failed : ".mysqli_error());
            }

            // FOR TESTING 
            if(!$this->connection)
                echo "Database not connected";
            // else
            //     echo "Connected successfully";
        }

        /**
         * Function to get the established connection.
         * 
         * @access public
         * @return mysqli 'mysqli' connection object is returned if connection is established successfully
         */
        public function getConnection() {
            return $this->connection;
        }
    }

    $database = new SampleDB();
?>