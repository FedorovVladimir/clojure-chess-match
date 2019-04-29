(ns clojure-site.routes
  (:require

    ; работа с маршрутами
    [compojure.core :refer [defroutes GET POST]]
    [compojure.route :as route]

    ; контроллеры запросов
    [clojure-site.controllers :as c]

    ; отображение страниц
    [clojure-site.views :as v]

    ; функции взаимодействия с БД
    [clojure-site.db :as db]))

; объявляем маршруты
(defroutes mail-routes

           ; главная страница приложения
           (GET "/" []
               (v/index))

           ; страница с формой создания турнира
           (GET "/tournaments/add" []
             (let [regions (db/get-regions)
                   systems-match (db/get-systems-match)
                   types-competition (db/get-types-competition)
                   indicators (db/get-indicators)
                   citys (db/get-citys)]
               (v/tournament-add-form regions systems-match types-competition indicators citys)))

           ; обработчик создания турнира
           (POST "/tournaments/add" [request]
             (-> c/tournament-add))

           ; ошибка 404
           (route/not-found "Ничего не найдено"))