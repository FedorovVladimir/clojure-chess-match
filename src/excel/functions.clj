(ns excel.functions)

(defn testing
  [x y]
  (println "Hello, Clojure!")
  (+ x y))

(defn -main []
  (println (str "(testing 5 3): " (testing 5 3)))
  (println (str "(testing 10042 111): " (testing 10042 111))))
