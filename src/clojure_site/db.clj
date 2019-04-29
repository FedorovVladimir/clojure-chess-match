(ns clojure-site.db
  (:require [clojure.java.jdbc :as jdbc]))

(defonce mysql-db {:subprotocol "mysql"
               :subname "//vladimir7.beget.tech/vladimir7_match?serverTimezone=UTC"
               :user "vladimir7_match"
               :password "chees_match"})

(defn get-regions []
  (jdbc/query mysql-db ["select * from REGION"]))

(defn get-systems-match []
  (jdbc/query mysql-db ["select * from SYSTEM_MATCH"]))

(defn get-types-competition []
  (jdbc/query mysql-db ["select * from TYPE_COMPETITION"]))

(defn get-indicators []
  (jdbc/query mysql-db ["select * from INDICATOR"]))

(defn get-citys []
  (jdbc/query mysql-db ["select * from CITY"]))

(defn create-tournament [tournament]
  (jdbc/insert! mysql-db :TOURNAMENT {:NAME (:name tournament)
                                      :DATE_START (:start-date tournament)
                                      :DATE_END (:end-date tournament)
                                      :ADRESS (:adress tournament)
                                      :TIME_CONTROL (:time-control tournament)
                                      :ID_TYPE_COMPETITION (:type-competition tournament)
                                      :ID_SYSTEM_MATCH (:system-match tournament)
                                      :ID_REGION (:region tournament)
                                      :ID_INDICATOR (:indicator tournament)
                                      :ID_CITY (:city tournament)
                                      :COUNT_TOUR (:count-tour tournament)}))