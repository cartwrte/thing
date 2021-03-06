(ns thing.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :refer [html]]
            [hiccup.def :refer [defhtml]]
            [garden.core :refer [css]]
            [korma.db :refer [defdb mysql]]
            [korma.core :as k]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defdb korma-db
  (mysql {:host "localhost" :port 3306 :db "thing"
          :user (System/getenv "DBUSER")
          :password (System/getenv "DBPASSWD")}))

(defhtml page
  [title content]
  [:html
   [:head
    [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css?family=Open+Sans"}]
    [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css?family=Raleway"}]
    [:link {:rel "stylesheet" :href "/css"}]]
   [:body
    [:h1 title]
    [:div {:class "content"} content]]])

(defroutes app-routes
  (GET "/" []
    (page "Main Title" "Content"))
  (GET "/css" []
    {:status 200
     :headers {"Content-Type" "text/css"}
     :body (css (clojure.edn/read-string (slurp "resources/css.edn")))})
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
