(ns moveit-clj.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [moveit-clj.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[moveit-clj started successfully using the development profile]=-"))
   :middleware wrap-dev})
