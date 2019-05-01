(ns excel.functions
  (:require [clojure-site.db :as db])
  (:import (excel PrevList)
           (java.util ArrayList)))

(defn test-calling-java-method-display-db [id]
  (def data (db/get-prev-list-players id))
  (.EnterPrevListDataBase (new PrevList) (new ArrayList data) "resources/excel/prev.xls" "prev.xls"))

(defn test-calling-java-method-display-db-start [id]
  (def data (db/get-start-list-players id))
  (.EnterPrevListDataBase (new PrevList) (new ArrayList data) "resources/excel/start.xls" "start.xls"))


(defn -main []
    (test-calling-java-method-display-db-start 1)
  )
