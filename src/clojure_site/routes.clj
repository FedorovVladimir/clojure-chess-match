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

           ; страница авторизации
           (GET "/login" []
               (v/login-page))

           ; страница регистрации
           (GET "/registrationlogin" []
             (v/registration-page))

           ; обработчик регистрации на сайте
           (POST "/registrationlogin" [request]
             (-> c/registration-login))

           ; список турниров
           (GET "/tournaments" []
             (let [tournaments (db/get-tournaments)]
               (v/tournaments tournaments)))

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

           (POST "/login" [request]
             (-> c/login))

           ; обработчик удаления турнира
           (GET "/tournaments/:id/delete" [id]
             (c/tournament-delete id))

           ; экспорт файла
           (GET  "/tournaments/:id/prev_list/export" [id]
             (c/tournament-prev-list-export id))

           ; экспорт файла
           (GET  "/tournaments/:id/start_list/export" [id]
             (c/tournament-start-list-export id))

           ; выход пользователя
           (GET  "/logout" []
             (c/logout))

           ; страница предварительного списка
           (GET "/tournaments/:id/prev_list" [id]
             (let [players (db/get-prev-list-players id)
                   tournament (db/get-tournament id)
                   regions (db/get-regions)
                   titles (db/get-titles)
                   titles-rus (db/get-titles-rus)]
               (v/tournament-prev-list players tournament regions "0" titles titles-rus)))

           ; страница стартового списка
           (GET "/tournaments/:id/start_list" [id]
             (let [players (db/get-start-list-players id)
                   tournament (db/get-tournament id)]
               (v/tournament-start-list players tournament "1")))

             ; обработчик регистрации на турнир
           (POST "/tournaments/register" [request]
             (-> c/tournament-register))

           ; обработчик отметки участника
           (GET "/tournaments/:idt/list_players/:idp/mark/:activ" [idt idp activ]
             (c/mark-player idt idp activ))

           ; обработчик редактирования данных участника
           (POST "/tournaments/:idt/list_players/:idp/update" [idt idp first_name, last_name, title, patro, date_born, region, adress, rating_rus, rating_fide]
             (c/update-player idt idp first_name last_name patro date_born region adress rating_rus rating_fide title))

           ; страница турнира
           (GET "/tournaments/info/:id" [id]
             (let [tournament (db/get-tournament id)
                   regions (db/get-regions)
                   sex (db/get-sex)
                   titles (db/get-titles)
                   titles-rus (db/get-titles-rus)]
             (v/tournaments-info tournament regions sex titles titles-rus)))

           ; регистрация на турнир
           (GET "/tournaments/registration/:id" [id]
             (let [tournament (db/get-tournament id)
                   regions (db/get-regions)
                   sex (db/get-sex)
                   titles (db/get-titles)
                   titles-rus (db/get-titles-rus)]
             (v/tournaments-registration tournament regions sex titles titles-rus)))

           (GET "/tournaments/:id/tours" [id]
             (let [tournament (db/get-tournament id)
                   tours (db/get-tours id)
                   games (db/get-rusult-tour (:id (first (db/get-tours id))))
                   result (db/get-results)]
               (v/tournaments-tours tournament tours games result (:id (first (db/get-tours id))) 1 true)))

           (GET "/tournaments/:id/tour/:id-tour" [id id-tour]
             (let [tournament (db/get-tournament id)
                   tours (db/get-tours id)
                   games (db/get-rusult-tour id-tour)
                   result (db/get-results)
                   number-tour (db/get-number-tour id-tour)
                   button (< (db/get-number-tour id-tour) (:count_tour (first (db/get-tournament id))))]
               (v/tournaments-tours tournament tours games result id-tour (:number (first number-tour)) button)))

           (GET  "/tournaments/:id/pairs" [id]
             (c/create-tour id 1))

           (GET  "/tournaments/:id/pairs/:number/tour" [id number]
             (c/create-tour id (+ (new Integer number) 1)))

           (POST "/game/update" [request]
             (-> c/update-game))

           ; ошибка 404
           (route/not-found "Ошибка 404. Страница не найдена"))
