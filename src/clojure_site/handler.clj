(ns clojure-site.handler
  (:require
    [clojure-site.routes :refer [mail-routes]]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))


(def app (wrap-defaults mail-routes site-defaults))
