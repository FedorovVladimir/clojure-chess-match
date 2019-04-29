(ns clojure-site.db
  (:require

    ; непосредственно Monger
    [monger.core :as mg]
    [monger.collection :as m]
    [monger.operators :refer :all])

    ; Импортируем методы из Java библиотек
    (:import org.bson.types.ObjectId)
  )

; создадим переменную соединения с БД
(defonce db
         (let [uri "mongodb://127.0.0.1:27017/papamail"
               {:keys [db]} (mg/connect-via-uri uri)]
           db))
