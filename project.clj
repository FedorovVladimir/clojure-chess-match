(defproject clojure-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]

                  ; Маршруты для GET и POST запросов
                  [compojure "1.6.1"]

                  ; Обертка (middleware) для наших
                  ; маршрутов
                  [ring/ring-defaults "0.3.2"]

                  ; Шаблонизатор
                  [selmer "0.8.2"]

                  ; jdbc
                  [org.clojure/java.jdbc "0.7.9"]

                  ; connector
                  [mysql/mysql-connector-java "8.0.15"]]

  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler clojure-site.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
