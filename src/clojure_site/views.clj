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
  []
  (render "index.html"))

(defn tournament-add-form
  "Форма создания турнира"
  [regions systems-match types-competition indicators citys]
  (render "tournament_add_form.html"
          {:regions (if (not-empty regions)
                       regions false)
           :systems-match (if (not-empty systems-match)
                            systems-match false)
           :types-competition (if (not-empty types-competition)
                                types-competition false)
           :indicators (if (not-empty indicators)
                         indicators false)
           :citys (if (not-empty citys)
                    citys false)}))
