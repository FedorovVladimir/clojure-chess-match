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

(defn get-tournaments []
  (jdbc/query mysql-db ["select TOURNAMENT.ID as id,
                                TOURNAMENT.NAME as name,
                                TOURNAMENT.DATE_START as date_start,
                                TOURNAMENT.DATE_END as date_end,
                                TOURNAMENT.ADRESS as adress,
                                CITY.NAME as city_name
                                from TOURNAMENT, CITY
                                where ID_CITY = CITY.ID"]))

(defn get-tournament [id]
  (first (jdbc/query mysql-db [(str "select TOURNAMENT.ID as id,
                                TOURNAMENT.NAME as name,
                                TOURNAMENT.DATE_START as date_start,
                                TOURNAMENT.DATE_END as date_end,
                                TOURNAMENT.ADRESS as adress,
                                TOURNAMENT.TIME_CONTROL as time_control,
                                CITY.NAME as city_name,
                                REGION.NAME as region,
                                INDICATOR.NAME as indicator
                                from TOURNAMENT, CITY, REGION, INDICATOR
                                where ID_CITY = CITY.ID and ID_INDICATOR = INDICATOR.ID and TOURNAMENT.ID_REGION = REGION.ID and TOURNAMENT.ID = " id)])) )

(defn get-sex []
  (jdbc/query mysql-db ["select * from SEX"]))