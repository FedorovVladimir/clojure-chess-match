(ns pairs.pairs
  (:require [clojure-site.db :as db])
  (:import (pairs AdapterPairs)))

(defn db-pairs [pairs id-tour]
  (db/set-pair-tour (first pairs) (first (drop 1 pairs)) id-tour)
  (if (not-empty (drop 1 (drop 1 pairs)))
    (db-pairs (drop 1 (drop 1 pairs)) id-tour)))

(defn -main []
  (let [id-tournament 1
        number-tour 1
        tour (.run (new AdapterPairs) (db/get-start-list-players id-tournament) (:count_tour (db/get-tournament id-tournament)))
        id-tour (db/set-tour number-tour id-tournament)]
    (db-pairs (seq tour) id-tour)))
