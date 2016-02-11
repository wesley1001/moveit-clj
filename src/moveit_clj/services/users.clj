(ns moveit-clj.services.users
    (:require [schema.core :as s]
              [moveit-clj.db.core :as db]
              [clj-time.core :as t]
              [clj-time.coerce :as tc]))


(def email-regex #"(.+)@(.+)")

(defn- current-timestamp []
      (tc/to-timestamp (t/now)))

(defn- get-user-org [email]
      (if-let [match (re-matches email-regex email)]
              (first (db/get-organization-by-domain {:domain (match 2)}))))

(defn- create-user [user-request organization]
      (let [now (current-timestamp)]
           (db/create-user<! (merge (:user user-request) {:organization_id (:id organization) :created_at now :updated_at now}))))

(defn- update-user [user user-request organization]
      (let [now (current-timestamp)]
           (db/update-user<! {:id (:id user) :name (get-in user-request [:user :name]) :updated_at now})))

(defn create-or-update-user [user-request]
      (let [email (get-in user-request [:user :email])
            organization (get-user-org email)]
           (when-not (empty? organization)
                     (let [user-seq (db/get-user-by-email {:email email})]
                          (if (empty? user-seq)
                            (create-user user-request organization)
                            (update-user (first user-seq) user-request organization))))))



(s/defschema UserResponse {:user {:id Integer
                                  :name String
                                  :email email-regex
                                  :created_at java.util.Date
                                  :updated_at java.util.Date
                                  :organization_id Integer}})

(s/defschema UserRequest {:user {:email email-regex :name String}})