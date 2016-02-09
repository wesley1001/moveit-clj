(ns moveit-clj.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[moveit-clj started successfully]=-"))
   :middleware identity})
