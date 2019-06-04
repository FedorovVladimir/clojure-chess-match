(ns clojure-site.controllers
  (:require

    ; функция редиректа
    [ring.util.response :refer [redirect]]

    ;Эксопрт
    [excel.functions :refer [test-calling-java-method-display-db-start
                             test-calling-java-method-display-db]]

    [pairs.pairs :refer [create-tour-pairs]]

    ; функция для взаимодействия с БД
    [clojure-site.db :as db]
    [clojure-site.views :as v])
  (:import (autorization User)))


(defn tournament-add
  "Создание турнира"
  [request]
  (let [tournament {:name (get-in request [:form-params "name"])
              :region (get-in request [:form-params "region"])
              :adress (get-in request [:form-params "adress"])
              :start-date (get-in request [:form-params "start-date"])
              :end-date (get-in request [:form-params "end-date"])
              :count-tour (get-in request [:form-params "count-tour"])
              :time-control (get-in request [:form-params "time-control"])
              :system-match (get-in request [:form-params "system-match"])
              :type-competition (get-in request [:form-params "type-competition"])
              :indicator (get-in request [:form-params "indicator"])
              :city (get-in request [:form-params "city"])}]

    (if (and (not-empty (:name tournament))
             (not-empty (:region tournament))
             (not-empty (:adress tournament))
             (not-empty (:start-date tournament))
             (not-empty (:end-date tournament))
             (not-empty (:count-tour tournament))
             (not-empty (:time-control tournament))
             (not-empty (:system-match tournament))
             (not-empty (:type-competition tournament))
             (not-empty (:indicator tournament))
             (not-empty (:city tournament)))

      (do
        (db/create-tournament tournament)
        (redirect "/"))

      "Проверьте правильность введенных данных")))

(defn tournament-register
  "Регистрация на турнир"
  [request]
  (let [user {:last_name (get-in request [:form-params "last_name"])
                    :first_name (get-in request [:form-params "first_name"])
                    :patro (get-in request [:form-params "patro"])
                    :date_born (get-in request [:form-params "date_born"])
                    :sex (get-in request [:form-params "sex"])
                    :email (get-in request [:form-params "email"])
                    :rating_fide (get-in request [:form-params "rating_fide"])
                    :rating_rus (get-in request [:form-params "rating_rus"])
                    :title (get-in request [:form-params "title"])
                    :title_rus (get-in request [:form-params "title_rus"])
                    :region (get-in request [:form-params "region"])
                    :adress (get-in request [:form-params "adress"])
                    :telephon (get-in request [:form-params "telephon"])
                    :yes (get-in request [:form-params "yes"])
                    :tournament (get-in request [:form-params "tournament"])}]

    (if (and (not-empty (:last_name user))
             (not-empty (:first_name user))
             (not-empty (:patro user))
             (not-empty (:date_born user))
             (not-empty (:sex user))
             (not-empty (:email user))
             (not-empty (:rating_fide user))
             (not-empty (:rating_rus user))
             (not-empty (:title user))
             (not-empty (:title_rus user))
             (not-empty (:region user))
             (not-empty (:adress user))
             (not-empty (:telephon user))
             (not-empty (:yes user))
             (not-empty (:tournament user)))

      (do
        (db/tournament-register user (:tournament user))
        (redirect "/"))

      "Проверьте правильность введенных данных")))

(defn tournament-delete [id]
  (do
  (db/tournament-delete id)
  (redirect "/")))

(defn mark-player [idt idp activ]
  (do
    (db/mark-player idp activ)
    (redirect (str "/tournaments/" idt "/prev_list"))))

(defn tournament-prev-list-export [id]
  (test-calling-java-method-display-db id)
  (redirect (str "/tournaments/" id "/prev_list")))

(defn tournament-start-list-export [id]
  (test-calling-java-method-display-db-start id)
  (redirect (str "/tournaments/" id "/start_list")))

(defn create-tour [id number-tour]
  (create-tour-pairs id number-tour)
  (redirect (str "/tournaments/" id "/tours")))

(defn update-player [idt idp]
  (println "здесь должно быть обновление игрока"))

(defn update-game [request]
  (let [result {:result (get-in request [:form-params "result"])}
        id-tournament {:id-tournament (get-in request [:form-params "id-tournament"])}
        id-tour {:id-tour (get-in request [:form-params "id-tour"])}
        id-game {:id-game (get-in request [:form-params "id-game"])}]

    (if (not-empty (:result result))
      (do
        (db/update-game (:id-game id-game) (:result result))
        (redirect (str "/tournaments/" (:id-tournament id-tournament) "/tour/" (:id-tour id-tour))))

      "Проверьте правильность введенных данных")))

(defn equal-user [user]
  (println (second (first user)))
  (println (second (second user)))

  ;(let [user-login (first user)]
  ;(println (second user-login)))
  )

(defn login [request]
  (let [user {:login    (get-in request [:form-params "login"])
              :password (get-in request [:form-params "password"])}]
    (let [us (db/get-user (second (first user)) (second (second user)))]
      (println us)
      (if (== 0 (count us))
        (redirect "/tournaments")
        (User/setAuthorization false)
        ; 1 to singlton
        (redirect "/"))
      )
    ;(redirect "/")
    ;(println us)
    ;(redirect "/")
    ;(equal-user user)
    ;(clojure-site.views/login (:login user) (:password user))
    ;(println user)
    ))