(ns clojure-site.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure-site.handler :refer :all]
            [clojure-site.db :as db])
  (:import [excel MyClass]))

(deftest test-calling-java-method
  (testing "create java class"
    (.displayText (new MyClass) "Hello, Java!")))

(deftest test-calling-java-method-display-db-sex
  (testing "send db"
    (.displayDataBase (new MyClass) (java.util.ArrayList. (db/get-sex)))))

(defn test-calling-java-method-display-db [data]
    (.displayDataBase (new MyClass) (java.util.ArrayList. data)))

;(test-calling-java-method)
;(test-calling-java-method-display-db-sex)
(test-calling-java-method-display-db (db/get-start-list-players 1))