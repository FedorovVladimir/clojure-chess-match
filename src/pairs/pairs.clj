(ns pairs.pairs
  (:require [clojure-site.db :as db])
  (:import (pairs AdapterPairs)))

(defn db-pairs [pairs id-tour]
  (db/set-pair-tour (first pairs) (first (drop 1 pairs)) id-tour)
  (if (not-empty (drop 1 (drop 1 pairs)))
    (db-pairs (drop 1 (drop 1 pairs)) id-tour)))

(defn create-tour-pairs [id-tournament number-tour]
  (let [tour (AdapterPairs/createTournament (db/get-start-list-players id-tournament)
                                            (db/get-result-all-games id-tournament)
                                            number-tour
                                            ; (:count_tour (db/get-tournament id-tournament))
                                            (:table_tournament (first (db/get-file-tournament id-tournament))))
        id-tour (db/set-tour number-tour id-tournament)]
    (db-pairs (drop 1 (seq tour)) id-tour)
    (db/update-file (first (seq tour)) id-tournament)))

(defn show-tour [id-tournament number-tour]
  (AdapterPairs/showTour (db/get-start-list-players id-tournament)
                                    (:table_tournament (first (db/get-file-tournament id-tournament)))
                                    number-tour))



(defn -main []
  ;(println (:id (first (db/get-tours 1))))

  ;(println (db/get-rusult-tour 29))
  ;(println (:table_tournament (first (db/get-file-tournament 1))))
  ;(println (:table_tournament (first (db/get-file-tournament 1))))
  (create-tour-pairs 1 1)
  ;(println (show-tour 1 3))
  ;(db/update-file (AdapterPairs/setResultTour (db/get-start-list-players 1)
  ;                                            (db/get-rusult-tour 37)
  ;                                            (:table_tournament (first (db/get-file-tournament 1)))) 1)
  ;(create-tour-pairs 1 3)
  )