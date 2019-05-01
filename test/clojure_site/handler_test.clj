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
  (test-calling-java-method-display-db (db/get-start-list-players 1)))
