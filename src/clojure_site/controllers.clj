(ns clojure-site.controllers
  (:require

    ; функция редиректа
    [ring.util.response :refer [redirect]]

    ; функция для взаимодействия с БД
    [clojure-site.db :as db]))

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
