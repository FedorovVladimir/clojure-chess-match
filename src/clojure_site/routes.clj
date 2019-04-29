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
             (let [tournaments (db/get-tournaments)]
               (v/index tournaments)))

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

           ; обработчик удаления турнира
           (GET "/tournaments/:id/delete" [id]
             (c/tournament-delete id))

           ; страница стартового списка
           (GET "/tournaments/:id/prev_list" [id]
             (let [players (db/get-prev-list-players id)
                   tournament (db/get-tournament id)]
               (v/tournament-prev-list players tournament)))

           ; обработчик регистрации на турнир
           (POST "/tournaments/register" [request]
             (-> c/tournament-register))

           ; страница турнира
           (GET "/tournaments/info/:id" [id]
             (let [tournament (db/get-tournament id)
                   regions (db/get-regions)
                   sex (db/get-sex)
                   titles (db/get-titles)
                   titles-rus (db/get-titles-rus)]
             (v/tournaments-info tournament regions sex titles titles-rus)))

           ; ошибка 404
           (route/not-found "Ничего не найдено"))