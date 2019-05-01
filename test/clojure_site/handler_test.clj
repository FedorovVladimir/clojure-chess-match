(ns clojure-site.handler-test
  (:require [clojure.test :refer :all]
            [clojure-site.handler :refer :all]
            [clojure-site.db :as db])
  (:import [excel MyClass]
           (java.util ArrayList)))

(deftest test-calling-java-method
  (testing "create java class"
    (.displayText (new MyClass) "Hello, Java!")))

(deftest test-calling-java-method-display-db-sex
  (testing "send db"
    (.displayDataBase (new MyClass) (new ArrayList (db/get-sex)))))

(defn test-calling-java-method-display-db [data]
    (.displayDataBase (new MyClass) (new ArrayList data)))

(defn -main []
  (def data (db/get-start-list-players 1))
  (println (:last (first data)))
  (def ret (test-calling-java-method-display-db data))
  (def retmap (reduce (fn [x [y z]] (assoc x y z)) {} (first ret)))
  (println retmap)
  (println (get "last" retmap)))
