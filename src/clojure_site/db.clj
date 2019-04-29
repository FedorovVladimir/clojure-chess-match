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
                                where ID_CITY = CITY.ID and
                                ID_INDICATOR = INDICATOR.ID and
                                TOURNAMENT.ID_REGION = REGION.ID and
                                TOURNAMENT.ID = " id)])) )

(defn get-sex []
  (jdbc/query mysql-db ["select * from SEX"]))

(defn tournament-register [user id-t]
  (let [id-human (jdbc/insert! mysql-db :HUMAN {:LAST (:last_name user)
                                               :FIRST (:first_name user)
                                               :PATRO (:patro user)
                                               :DATE_BORN (:date_born user)
                                               :TELEPHON (:telephon user)
                                               :EMAIL (:email user)
                                               :ADRES (:adress user)
                                               :ID_SEX (:sex user)})
        id-h (:generated_key (first id-human))
    id-player (jdbc/insert! mysql-db :PLAYER {:RATING_RUS (:rating_rus user)
                                   :RATING_FIDE (:rating_fide user)
                                   :ID_TITLE (:title user)
                                   :ID_TITLE_RUS (:title_rus user)
                                   :ID_COEFF_MOD 2
                                   :ID_REGION (:region user)
                                   :ID_HUMAN id-h})
        id-p (:generated_key (first id-player))]
    (jdbc/insert! mysql-db :LIST_PLAYER {:RATING_START_RUS (:rating_rus user)
                                    :RATING_START_FIDE (:rating_fide user)
                                    :ID_TOURNAMENT id-t
                                    :ID_PLAYER id-p})))

(defn get-titles []
  (jdbc/query mysql-db ["select * from TITLE"]))

(defn get-titles-rus []
  (jdbc/query mysql-db ["select * from TITLE_RUS"]))

(defn tournament-delete [id]
  (jdbc/delete! mysql-db :TOURNAMENT ["id = ?" id]))

(defn get-prev-list-players [id]
  (jdbc/query mysql-db [(str "select LIST_PLAYER.ID as id_player_list,
                          HUMAN.LAST as last,
                          HUMAN.FIRST as first,
                          HUMAN.PATRO as patro,
                          PLAYER.RATING_RUS as rating_rus,
                          LIST_PLAYER.ACTIVE as activ
                          from LIST_PLAYER,
                          PLAYER,
                          HUMAN
                          where LIST_PLAYER.ID_PLAYER = PLAYER.ID and
                          PLAYER.ID_HUMAN = HUMAN.ID and
                          ID_TOURNAMENT = " id " order by last, first, patro")]))

(defn mark-player [id activ]
  (jdbc/update! mysql-db
                :LIST_PLAYER
               {:ACTIVE activ}
               ["id = ? " id]))

(defn get-start-list-players [id]
  (jdbc/query mysql-db [(str "select LIST_PLAYER.ID as id_player_list,
                          HUMAN.LAST as last,
                          HUMAN.FIRST as first,
                          HUMAN.PATRO as patro,
                          PLAYER.RATING_RUS as rating_rus
                          from LIST_PLAYER,
                          PLAYER,
                          HUMAN
                          where LIST_PLAYER.ID_PLAYER = PLAYER.ID and
                          PLAYER.ID_HUMAN = HUMAN.ID and
                          ID_TOURNAMENT = " id " and LIST_PLAYER.ACTIVE = 1 order by last, first, patro")]))