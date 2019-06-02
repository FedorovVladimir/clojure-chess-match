(ns clojure-site.handler
  (:require
    [clojure-site.routes :refer [mail-routes]]
    [buddy.auth.backends.session :refer [session-backend]]
    [buddy.auth.middleware :refer [wrap-authentication]]))

(def backend (session-backend))

(def app (wrap-authentication mail-routes backend))
