(ns clojure-site.db
  (:require [clojure.java.jdbc :as jdbc]))

(defonce mysql-db {:subprotocol "mysql"
               :subname "//vladimir7.beget.tech/vladimir7_match?serverTimezone=UTC"
               :user "vladimir7_match"
               :password "chees_match"})

(defn get-regions []
  (jdbc/query mysql-db ["select * from REGION"]))

(defn get-user [login password]
  (jdbc/query mysql-db ["select * from USERS
                                  where LOGIN = ? and
                                  PASSWORD = ?" login password]))

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
                                TOURNAMENT.COUNT_TOUR as count_tour,
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
  (jdbc/query mysql-db ["select LIST_PLAYER.ID as id_player_list,
                            HUMAN.LAST as last,
                            HUMAN.id as id_human,
                            HUMAN.DATE_BORN as date_born,
                            HUMAN.ADRES as adres,
                            HUMAN.FIRST as first,
                            HUMAN.PATRO as patro,
                            HUMAN.TELEPHON as telephon,
                            HUMAN.EMAIL as email,
                            PLAYER.RATING_RUS as rating_rus,
                            PLAYER.RATING_FIDE as rating_fide,
                            PLAYER.ID_REGION as id_region,
                            REGION.NAME as region,
                            TITLE_RUS.CODE as title_rus,
                            TITLE.CODE as title,
                            PLAYER.ID_TITLE as id_title,
                            PLAYER.ID_TITLE_RUS as id_title_rus,
                            PLAYER.IDFIDE as fide,
                            PLAYER.IDRUS as rus,
                            LIST_PLAYER.ACTIVE as activ
                            from LIST_PLAYER,
                            PLAYER, HUMAN, REGION, TITLE_RUS, TITLE
                            where LIST_PLAYER.ID_PLAYER = PLAYER.ID and
                            PLAYER.ID_HUMAN = HUMAN.ID and
                            REGION.ID = PLAYER.ID_REGION and
                            TITLE_RUS.ID = PLAYER.ID_TITLE_RUS and
                            TITLE.ID = PLAYER.ID_TITLE and
                            ID_TOURNAMENT = ? order by last, first, patro" id]))

(defn mark-player [id activ]
  (jdbc/update! mysql-db
                :LIST_PLAYER
               {:ACTIVE activ}
               ["id = ? " id]))

(defn get-start-list-players [id-tournament]
  (jdbc/query mysql-db ["select LIST_PLAYER.ID as id_player_list,
                            HUMAN.LAST as last,
                            HUMAN.id as id_human,
                            HUMAN.DATE_BORN as date_born,
                            HUMAN.ADRES as adres,
                            HUMAN.FIRST as first,
                            HUMAN.PATRO as patro,
                            HUMAN.TELEPHON as telephon,
                            HUMAN.EMAIL as email,
                            PLAYER.RATING_RUS as rating_rus,
                            PLAYER.RATING_FIDE as rating_fide,
                            PLAYER.ID_REGION as id_region,
                            REGION.NAME as region,
                            TITLE_RUS.CODE as title_rus,
                            TITLE.CODE as title,
                            PLAYER.ID_TITLE as id_title,
                            PLAYER.ID_TITLE_RUS as id_title_rus,
                            PLAYER.IDFIDE as fide,
                            PLAYER.IDRUS as rus,
                            LIST_PLAYER.ACTIVE as activ
                            from LIST_PLAYER,
                            PLAYER, HUMAN, REGION, TITLE_RUS, TITLE
                            where LIST_PLAYER.ID_PLAYER = PLAYER.ID and
                            PLAYER.ID_HUMAN = HUMAN.ID and
                            REGION.ID = PLAYER.ID_REGION and
                            TITLE_RUS.ID = PLAYER.ID_TITLE_RUS and
                            TITLE.ID = PLAYER.ID_TITLE and
                            ID_TOURNAMENT = ? order by last, first, patro" id-tournament]))

(defn set-tour [number-tour id-tournament]
  (:generated_key (first (jdbc/insert! mysql-db :TOUR {:ID_TOURNAMENT id-tournament
                                                       :NUMBER number-tour}))))

(defn set-pair-tour [first second id-tour]
  (jdbc/insert! mysql-db :GAME {:ID_TOUR id-tour
                                :ID_PLAYER_WHITE first
                                :ID_PLAYER_BLACK second}))

(defn get-tours [id-tournament]
  (jdbc/query mysql-db ["select * from TOUR where ID_TOURNAMENT = ?" id-tournament]))

(defn get-file-tournament [id-tournament]
  (jdbc/query mysql-db ["select TABLE_TOURNAMENT as table_tournament from TOURNAMENT where ID = ?" id-tournament]))

(defn update-file [text-file id-tournament]
  (jdbc/update! mysql-db
                :TOURNAMENT
                {:TABLE_TOURNAMENT text-file}
                ["id = ? " id-tournament]))

(defn get-rusult-tour [id-tour]
  (jdbc/query mysql-db ["select GAME.ID,
                        GAME.ID_PLAYER_WHITE,
                        GAME.ID_PLAYER_BLACK,
                        RESULT.ID as id_result,
                        RESULT.CODE,
                        human_w.LAST as last_w,
                        human_b.LAST as last_b
                        from GAME,
                        RESULT,
                        LIST_PLAYER as list_b,
                        LIST_PLAYER as list_w,
                        PLAYER as player_w,
                        PLAYER as player_b,
                        HUMAN as human_w,
                        HUMAN as human_b
                        where GAME.ID_TOUR = ? and
                        RESULT.ID = GAME.ID_RESULT and
                        GAME.ID_PLAYER_WHITE = list_w.ID and
                        GAME.ID_PLAYER_BLACK = list_b.ID and
                        list_w.ID_PLAYER = player_w.ID and
                        list_b.ID_PLAYER = player_b.ID and
                        player_w.ID_HUMAN = human_w.ID and
                        player_b.ID_HUMAN = human_b.ID" id-tour]))

(defn get-tours [id-tournament]
  (jdbc/query mysql-db ["select *
    from TOUR
    where TOUR.ID_TOURNAMENT = ?
    order by TOUR.NUMBER" id-tournament]))

(defn get-results []
  (jdbc/query mysql-db ["select *
                        from RESULT"]))

(defn update-game [id-game result]
  (jdbc/update! mysql-db
                :GAME
                {:ID_RESULT result}
                ["id = ? " id-game]))

(defn get-result-all-games [id-tournament]
  (jdbc/query mysql-db ["select TOUR.NUMBER,
                        GAME.ID_PLAYER_WHITE,
                        GAME.ID_PLAYER_BLACK,
                        RESULT.CODE
                        from TOUR, GAME, RESULT
                        where GAME.ID_TOUR = TOUR.ID and
                        RESULT.ID = GAME.ID_RESULT and
                        TOUR.ID_TOURNAMENT = ? " id-tournament]))

(defn get-number-tour [id-tour]
  (jdbc/query mysql-db ["select TOUR.NUMBER
    from TOUR
    where TOUR.ID = ? " id-tour]))

(defn update-player [idp first_name last_name patro date_born region adress rating_rus rating_fide title]
  "Обновление данных об участнике"
  (jdbc/update! mysql-db
                :PLAYER
                {:RATING_FIDE rating_fide
                 :RATING_RUS rating_rus
                 :ID_TITLE_RUS title
                 :ID_REGION region
                 }
                ["id_human = ? " idp])
  (jdbc/update! mysql-db
                :HUMAN
                {:LAST last_name
                 :FIRST first_name
                 :PATRO patro
                 :DATE_BORN date_born
                 :ADRES adress
                 }
                ["id = ? " idp]))