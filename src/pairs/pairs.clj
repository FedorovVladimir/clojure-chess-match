(ns pairs.pairs
  (:require [clojure-site.db :as db])
  (:import (pairs AdapterPairs)))


(defn -main []
  (.run (new AdapterPairs) (db/get-start-list-players 1)))
