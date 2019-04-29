(ns clojure-site.controllers
  (:require

    ; функция редиректа
    [ring.util.response :refer [redirect]]

    ; функция для взаимодействия с БД
    [clojure-site.db :as db]))

(defn base-add
  "Создание базы"
  [request]
  (let [base {:name (get-in request [:form-params "name"])
              :description (get-in request [:form-params "description"])}]

    (if (and (not-empty (:name base))
             (not-empty (:description base)))

      (do
        (db/create-base base)
        (redirect "/bases"))

      "Проверьте правильность введенных данных")))

(defn emails-add
  "Создание email"
  [request]
  (let [email {:email (get-in request [:form-params "email"])
              :name (get-in request [:form-params "name"])
              :base (db/get-base (get-in request [:form-params "base"]))}]

    (if (and (not-empty (:email email))
             (not-empty (:name email))
             (not-empty (:base email)))

      (do
        (db/create-email email)
        (redirect "/bases"))

      "Проверьте правильность введенных данных")))

(defn mailing-add
  "Создание рассылки"
  [request]
  (let [mailing {:subject(get-in request [:form-params "subject"])
               :text (get-in request [:form-params "text"])
               :base (db/get-base (get-in request [:form-params "base"]))}]

    (if (and (not-empty (:subject mailing))
             (not-empty (:text mailing))
             (not-empty (:base mailing)))

      (do
        (db/create-mailing mailing)
        (redirect "/"))

      "Проверьте правильность введенных данных")))

(defn mailing-del
  "Удаление рассылки"
  [id]
  (println id)
  (do
    (db/remove-mailing id)
    (redirect "/")))
