(ns clojure-site.views
  (:require

    ; "Шаблонизатор"
    [selmer.parser :as parser]

    ; для HTTP заголовков
    [ring.util.response :refer [content-type response]]

    ; для CSRF защиты
    [ring.util.anti-forgery :refer [anti-forgery-field]]
    [clojure-site.db :as db]))

; подскажем Selmer где искать наши шаблоны
(parser/set-resource-path! (clojure.java.io/resource "templates"))

; Добавим тэг с полем для форм в нем будет находится
; автоматически созданное поле с anti-forgery ключом
(parser/add-tag! :csrf-field (fn [_ _] (anti-forgery-field)))

(defn render [template & [params]]
  "Эта функция будет отображать наши html шаблоны
  и передавать в них данные"
  (-> template
      (parser/render-file
        ; Добавим к получаемым данным постоянные
        ; значения которые хотели бы получать
        ; на любой странице
        (assoc params
          :title "Менеджер писем"
          :page (str template)))
      ; Из всего этого сделаем HTTP ответ
      response
      (content-type "text/html; charset=utf-8")))

(defn index
  "Главная страница приложения. Список писем"
  [mailing]
  (render "index.html"
          {:mailings (if (not-empty mailing)
                       mailing false)}))

(defn bases [bases]
  (render "bases.html"
          {:bases (if (not-empty bases)
                    bases false)}))

(defn base-add-form []
  (render "base_add_form.html"))

(defn base-info [base emails]
  (render "base_info.html"
          {:base (if (not-empty base)
                   base false)
           :emails (if (not-empty emails)
                     emails false)}))

(defn email-add-form [bases base]
  (render "email_add_form.html"
          {:bases (if (not-empty bases)
                    bases false)
           :base (if (not-empty base)
                     base false)}))

(defn templates [templates]
  (render "templates.html"
          {:templates (if (not-empty templates)
                        templates false)}))

(defn mailing-add-form [bases base]
  "/add"
  (render "mailing_add_form.html"
          {:bases (if (not-empty bases)
                    bases false)
           :base (if (not-empty base)
                   base false)}))