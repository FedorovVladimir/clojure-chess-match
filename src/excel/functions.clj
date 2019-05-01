(ns excel.functions
  (:require [clojure-site.db :as db])
  (:import (excel PrevList)
           (java.util ArrayList)))

(defn test-calling-java-method-display-db []
  (def data (db/get-prev-list-players 1))
  (.EnterPrevListDataBase (new PrevList) (new ArrayList data) "resources/excel/prev.xls"))

(defn test-calling-java-method-display-db-start []
  (def data (db/get-start-list-players 1))
  (.EnterPrevListDataBase (new PrevList) (new ArrayList data) "resources/excel/start.xls"))


(defn -main []
    (test-calling-java-method-display-db)
  )
