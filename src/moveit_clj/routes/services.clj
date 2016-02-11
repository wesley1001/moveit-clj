(ns moveit-clj.routes.services
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [moveit-clj.services.users :as user-service]))


(defapi service-routes
        (ring.swagger.ui/swagger-ui
          "/swagger-ui")
        ;JSON docs available at the /swagger.json route
        (swagger-docs
          {:info {:title "Moveit api"}})
        (context* "/users" []
                  :tags ["user"]

                  (POST* "/register" []
                         :return      user-service/UserResponse
                         :body [user-request user-service/UserRequest]
                         :summary     "Register or Login user"
                         (if-let [user (user-service/create-or-update-user user-request)]
                                 (ok {:user user})))))
