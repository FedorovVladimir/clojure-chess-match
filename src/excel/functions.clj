(ns excel.functions
  (:require [clojure-site.db :as db])
  (:import (excel PrevList)
           (java.util ArrayList)))

(defn test-calling-java-method-display-db [data]
  (.EnterPrevListDataBase (new PrevList) (new ArrayList data)))


(defn -main []
  (doto (new PrevList)
    (.hi))

  ;(.enterPrevList prevList "resources/excel/prev.xls")
  (def data (db/get-start-list-players 1))
(println (test-calling-java-method-display-db data)))
