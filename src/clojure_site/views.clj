(ns clojure-site.views
  (:require

    ; "Шаблонизатор"
    [selmer.parser :as parser]

    ; для HTTP заголовков
    [ring.util.response :refer [content-type response redirect]]

    ; для CSRF защиты
    [ring.util.anti-forgery :refer [anti-forgery-field]]))

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
  "Главная страница приложения. Список турниров"
  [tournaments]
  (render "index.html" {:tournaments (if (not-empty tournaments)
                                       tournaments false)}))

(defn tournament-add-form
  "Форма создания турнира"
  [regions systems-match types-competition indicators citys]
  (render "tournaments/add_form.html"
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

(defn tournaments-info [tournament regions sex titles titles-rus]
  "Страница турнира"
  (render "tournaments/info.html"
          {:tournament (if (not-empty tournament)
                      tournament false)
           :regions (if (not-empty regions)
                      regions false)
           :sex (if (not-empty sex)
                  sex false)
           :titles (if (not-empty titles)
                     titles false)
           :titles-rus (if (not-empty titles-rus)
                         titles-rus false)}))

(defn tournament-prev-list [players tournament regions text  titles titles-rus]
  "Страница предварительного листа"
  (render "tournaments/list.html"
          {:players (if (not-empty players)
                      players false)
           :tournament (if (not-empty tournament)
                         tournament false)
           :text (if (not-empty text)
                   text false)
           :regions (if (not-empty regions)
                      regions false)
           :titles (if (not-empty titles)
                     titles false)
           :titles-rus (if (not-empty titles-rus)
                         titles-rus false)}))

(defn tournament-start-list [players tournament text]
  "Страница стартового листа"
  (render "tournaments/start_list.html"
          {:players (if (not-empty players)
                      players false)
           :tournament (if (not-empty tournament)
                         tournament false)
           :text (if (not-empty text)
                   text false)}))

(defn tournaments-tours [tournament tours games results id-tour number-tour button]
  (render "tournaments/tours.html"
          {:tournament (if (not-empty tournament)
                         tournament false)
           :tours (if (not-empty tours)
                    tours false)
           :games (if (not-empty games)
                    games false)
           :results (if (not-empty results)
                      results false)
           :id-tour id-tour
           :number-tour number-tour
           :button button}))

(defn tournaments
  "Список турниров"
  [tournaments]
  (render "tournaments.html" {:tournaments (if (not-empty tournaments)
                                       tournaments false)})
  )

(defn tournaments-registration [tournament regions sex titles titles-rus]
  "Страница регистрации на турнир"
  (render "tournaments/registration.html"
          {:tournament (if (not-empty tournament)
                         tournament false)
           :regions (if (not-empty regions)
                      regions false)
           :sex (if (not-empty sex)
                  sex false)
           :titles (if (not-empty titles)
                     titles false)
           :titles-rus (if (not-empty titles-rus)
                         titles-rus false)})
  )

(def users {"admin" {:username "admin"
                     :hashed-password "adminpass"
                     :roles #{:user :admin}}
            "user"  {:username "user"
                     :hashed-password "userpass"
                     :roles #{:user}}})


(defn lookup-user [username password]
  (if-let [user (get users username)]  ; Use a database IRL
    (if (.equals password (get user :hashed-password))
      (dissoc user :hashed-password)))) ; Strip out user password

(defn do-login [{{username "username" password "password" next "next"} :params
                 session :session :as req}]
  (if-let [user (lookup-user username password)]    ; lookup-user defined elsewhere
    (assoc (redirect (or next "/"))                 ; Redirect to "next" or /
      :session (assoc session :identity user)) ; Add an :identity to the session
    (response "Login page goes here")))             ; If no user, show a login page


(defn do-logout [{session :session}]
  (-> (redirect "/login")                           ; Redirect to login
      (assoc :session (dissoc session :identity)))) ; Remove :identity from session

(defn login-go [username password]
  (println "-----")
  ;(println (do-login {{username password "/"}} ))
  )

(defn login
  "Страница для входа"
  [users]
  (render "login.html" {:users (if (not-empty users)
                                             users false)})
  )