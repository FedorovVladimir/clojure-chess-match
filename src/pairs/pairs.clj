(ns pairs.pairs
  (:require [clojure-site.db :as db])
  (:import (pairs AdapterPairs)))

(defn db-pairs [pairs id-tour]
  (db/set-pair-tour (first pairs) (first (drop 1 pairs)) id-tour)
  (if (not-empty (drop 1 (drop 1 pairs)))
    (db-pairs (drop 1 (drop 1 pairs)) id-tour)))

(defn create-tour-pairs [id-tournament number-tour]
  (let [tour (AdapterPairs/run (db/get-start-list-players id-tournament) (:count_tour (db/get-tournament id-tournament)))
        id-tour (db/set-tour number-tour id-tournament)]
    (println (seq tour))
    (db-pairs (seq tour) id-tour)))

(defn -main []
  (create-tour-pairs 1 1))