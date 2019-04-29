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
             (let [mailings (db/get-mailings)]
               (v/index mailings)))

           ; страница с формой создания рассылки
           (GET "/add" []
             (let [bases (db/get-bases)]
               (v/mailing-add-form bases nil)))

           ; обработчик создания рассылки
           (POST "/add" [request]
             (-> c/mailing-add))

           ; обработчик удаления рассылки
           (GET "/del/:id" [id]
             (c/mailing-del id))

           ; страница баз
           (GET "/bases" []
             (let [bases (db/get-bases)]
               (v/bases bases)))

           ; страница с формой создания базы
           (GET "/bases/add" []
               (v/base-add-form))

           ; обработчик создания базы
           (POST "/bases/add" [request]
             (-> c/base-add))

           ; страница с информацией о базе
           (GET "/bases/:id" [id]
             (let [base (db/get-base id)
                   emails (db/get-emails id)]
               (v/base-info base emails)))

           ; страница с формой создания email
           (GET "/emails/add" []
             (let [bases (db/get-bases)]
               (v/email-add-form bases nil)))

           ; страница с формой создания email с заданой базой
           (GET "/emails/add/:idBase" [idBase]
             (let [base (db/get-base idBase)]
               (v/email-add-form nil base)))

           ; обработчик создания email с заданой базой
           (POST "/emails/add" [request]
             (-> c/emails-add))

           ; страница шаблонов
           (GET "/templates" []
             (let [templates (db/get-templates)]
               (v/templates templates)))

           ; ошибка 404
           (route/not-found "Ничего не найдено"))